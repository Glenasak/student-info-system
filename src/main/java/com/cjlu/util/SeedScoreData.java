package com.cjlu.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * 快速向 Derby 数据库写入一批用于 UI 测试的学生和成绩数据。
 */
public final class SeedScoreData {

    private SeedScoreData() {
    }

    public static void main(String[] args) throws Exception {
        try (Connection conn = JDBCUtils.getConnection()) {
            conn.setAutoCommit(false);

                try (Statement stmt = conn.createStatement()) {
                executeIgnore(stmt, "DELETE FROM scores WHERE student_id BETWEEN 20001 AND 20005");
                executeIgnore(stmt, "DELETE FROM students WHERE student_id BETWEEN 20001 AND 20005");
                executeIgnore(stmt, "ALTER TABLE Students ALTER COLUMN student_id RESTART WITH 20001");
            }

                int liHuaId = insertStudent(conn, "Li Hua", 20, "M", "Computer Science", "CS-2201",
                    Date.valueOf("2023-09-01"), "13800000001", "lihua@example.com");
                int zhangWeiId = insertStudent(conn, "Zhang Wei", 21, "M", "Computer Science", "CS-2201",
                    Date.valueOf("2022-09-01"), "13800000002", "zhangwei@example.com");
                int chenJieId = insertStudent(conn, "Chen Jie", 20, "F", "Information Systems", "IS-2202",
                    Date.valueOf("2023-09-01"), "13800000003", "chenjie@example.com");
                int wangFangId = insertStudent(conn, "Wang Fang", 19, "F", "Information Systems", "IS-2202",
                    Date.valueOf("2024-09-01"), "13800000004", "wangfang@example.com");
                int liuYangId = insertStudent(conn, "Liu Yang", 22, "M", "Software Engineering", "SE-2101",
                    Date.valueOf("2021-09-01"), "13800000005", "liuyang@example.com");

                insertScore(conn, liHuaId, "1001", 95.5, Date.valueOf("2024-06-18"));
                insertScore(conn, liHuaId, "1002", 83.0, Date.valueOf("2024-05-30"));
                insertScore(conn, zhangWeiId, "1005", 88.0, Date.valueOf("2024-06-10"));
                insertScore(conn, chenJieId, "1007", 91.0, Date.valueOf("2024-06-05"));
                insertScore(conn, chenJieId, "1004", 76.5, Date.valueOf("2024-05-25"));
                insertScore(conn, wangFangId, "1007", 89.5, Date.valueOf("2024-06-05"));
                insertScore(conn, liuYangId, "1008", 72.0, null);
                insertScore(conn, liuYangId, "1003", 81.0, Date.valueOf("2024-04-22"));

            conn.commit();
                System.out.println("Seed data inserted successfully.");
                System.out.printf("Students created: Li Hua=%d, Zhang Wei=%d, Chen Jie=%d, Wang Fang=%d, Liu Yang=%d%n",
                    liHuaId, zhangWeiId, chenJieId, wangFangId, liuYangId);
        }
    }

    private static void executeIgnore(Statement stmt, String sql) {
        try {
            stmt.executeUpdate(sql);
        } catch (Exception ignore) {
            // 表不存在等情况时忽略
        }
    }

    private static int insertStudent(Connection conn, String name, int age, String gender,
            String major, String clazz, Date admissionDate, String phone, String email) throws Exception {
        String sql = "INSERT INTO Students (name, age, gender, major, class, admission_date, phone, email) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, major);
            ps.setString(5, clazz);
            ps.setDate(6, admissionDate);
            ps.setString(7, phone);
            ps.setString(8, email);
            ps.executeUpdate();
            try (var rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new IllegalStateException("无法获取学生主键");
    }

    private static void insertScore(Connection conn, int studentId, String courseCode, double score, Date examDate)
            throws Exception {
        String sql = "INSERT INTO scores (student_id, course_code, score, exam_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setString(2, courseCode);
            ps.setDouble(3, score);
            if (examDate == null) {
                ps.setNull(4, java.sql.Types.DATE);
            } else {
                ps.setDate(4, examDate);
            }
            ps.executeUpdate();
        }
    }
}
