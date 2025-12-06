package com.cjlu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility entry point that clears the student, course, and scores tables
 * and inserts a fixed batch of demo records for UI or integration testing.
 */
public final class DatabaseSeeder {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);
    private static final int ROW_COUNT = 50;

    private DatabaseSeeder() {
    }

    public static void main(String[] args) {
        try (Connection conn = JDBCUtils.getConnection()) {
            conn.setAutoCommit(false);
            clearTables(conn);
            List<Integer> studentIds = insertStudents(conn);
            List<String> courseIds = insertCourses(conn);
            insertScores(conn, studentIds, courseIds);
            conn.commit();
            logger.info("Seeding completed: {} students, {} courses, {} scores", ROW_COUNT, ROW_COUNT, ROW_COUNT);
        } catch (Exception ex) {
            logger.error("Failed to seed database", ex);
            System.exit(1);
        }
    }

    private static void clearTables(Connection conn) throws Exception {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM scores");
            stmt.executeUpdate("DELETE FROM Students");
            stmt.executeUpdate("DELETE FROM course");
            resetIdentity(stmt, "Students", "student_id");
            resetIdentity(stmt, "scores", "score_id");
            logger.info("Existing data removed and identity columns reset");
        }
    }

    private static void resetIdentity(Statement stmt, String tableName, String columnName) {
        try {
            stmt.executeUpdate("ALTER TABLE " + tableName + " ALTER COLUMN " + columnName + " RESTART WITH 1");
        } catch (Exception ex) {
            logger.warn("Could not reset identity for {}.{}", tableName, columnName, ex);
        }
    }

    private static List<Integer> insertStudents(Connection conn) throws Exception {
        String sql = "INSERT INTO Students (name, age, gender, major, class, admission_date, phone, email) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        List<Integer> ids = new ArrayList<>(ROW_COUNT);
        LocalDate baseDate = LocalDate.of(2021, 9, 1);
        String[] majors = {"Computer Science", "Information Systems", "Software Engineering", "Data Science", "Cyber Security"};
        String[] classes = {"CS-01", "CS-02", "IS-01", "SE-01", "DS-01"};
        String[] genders = {"M", "F"};

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < ROW_COUNT; i++) {
                int age = 18 + (i % 8);
                ps.setString(1, "Student" + String.format("%02d", i + 1));
                ps.setInt(2, age);
                ps.setString(3, genders[i % genders.length]);
                ps.setString(4, majors[i % majors.length]);
                ps.setString(5, classes[i % classes.length]);
                ps.setDate(6, Date.valueOf(baseDate.plusDays(i)));
                ps.setString(7, String.format("555-01%04d", i));
                ps.setString(8, String.format("student%02d@example.com", i + 1));
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        ids.add(rs.getInt(1));
                    }
                }
            }
        }
        logger.info("Inserted {} students", ids.size());
        return ids;
    }

    private static List<String> insertCourses(Connection conn) throws Exception {
        String sql = "INSERT INTO course (course_id, course_name, credit, teacher, semester) VALUES (?, ?, ?, ?, ?)";
        List<String> courseIds = new ArrayList<>(ROW_COUNT);
        String[] semesters = {"2024S1", "2024F1", "2025S1", "2025F1"};

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < ROW_COUNT; i++) {
                String courseId = String.format("C%03d", i + 1);
                ps.setString(1, courseId);
                ps.setString(2, "Course " + String.format("%02d", i + 1));
                ps.setInt(3, 2 + (i % 4));
                ps.setString(4, "Instructor" + String.format("%02d", i + 1));
                ps.setString(5, semesters[i % semesters.length]);
                ps.executeUpdate();
                courseIds.add(courseId);
            }
        }
        logger.info("Inserted {} courses", courseIds.size());
        return courseIds;
    }

    private static void insertScores(Connection conn, List<Integer> studentIds, List<String> courseIds) throws Exception {
        String sql = "INSERT INTO scores (student_id, course_code, score, exam_date) VALUES (?, ?, ?, ?)";
        LocalDate baseDate = LocalDate.of(2024, 6, 1);
        ThreadLocalRandom random = ThreadLocalRandom.current();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < ROW_COUNT; i++) {
                double rawScore = random.nextDouble(65.0, 100.0);
                double roundedScore = Math.round(rawScore * 10.0) / 10.0;
                ps.setInt(1, studentIds.get(i % studentIds.size()));
                ps.setString(2, courseIds.get(i % courseIds.size()));
                ps.setDouble(3, roundedScore);
                ps.setDate(4, Date.valueOf(baseDate.plusDays(i)));
                ps.addBatch();
            }
            ps.executeBatch();
        }
        logger.info("Inserted {} scores", ROW_COUNT);
    }
}
