package com.cjlu.dao.impl;

import com.cjlu.dao.ScoresDao;
import com.cjlu.entity.Scores;
import com.cjlu.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScoresDaoImpl implements ScoresDao {

    //日志记录器
    private static final Logger logger = LoggerFactory.getLogger(ScoresDaoImpl.class);

    //数据库连接工具
    Connection conn = null;
    PreparedStatement pstmt = null;

    //创建成绩表
    public void createScoresTable() {
        String sql = "CREATE TABLE IF NOT EXISTS scores (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "student_id INT NOT NULL," +
                "course_id INT NOT NULL," +
                "score DOUBLE," +
                "FOREIGN KEY (student_id) REFERENCES students(id)," +
                "FOREIGN KEY (course_id) REFERENCES courses(id)" +
                ");";
        try {
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            logger.info("成绩表创建成功或已存在");
        } catch (Exception e) {
            logger.error("创建成绩表失败", e);
        } finally {
            JDBCUtils.closeResources(conn, pstmt, null);
        }
    }

    @Override
    public void addScore(Scores scores) {
        try{
            String sql = "INSERT INTO scores (student_id, course_id, score) VALUES (?, ?, ?)";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, scores.getStudentId());
            pstmt.setInt(2, scores.getCourseId());
            pstmt.setDouble(3, scores.getScore());
            pstmt.executeUpdate();
            logger.info("成绩添加成功");
        } catch (Exception e) {
            logger.error("添加成绩失败", e);
        } finally {
            JDBCUtils.closeResources(conn, pstmt, null);
        }
    }

    @Override
    public Scores getScoreByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        try{
            String sql = "SELECT * FROM scores WHERE student_id = ? AND course_id = ?";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            var rs = pstmt.executeQuery();
            if (rs.next()) {
                Scores scores = new Scores();
                scores.setId(rs.getInt("id"));
                scores.setStudentId(rs.getInt("student_id"));
                scores.setCourseId(rs.getInt("course_id"));
                scores.setScore(rs.getDouble("score"));
                return scores;
            }
        } catch (Exception e) {
            logger.error("查询成绩失败", e);
        } finally {
            JDBCUtils.closeResources(conn, pstmt, null);
        }
        return null;
    }

    @Override
    public void updateScore(Scores scores) {
        try{
            String sql = "UPDATE scores SET score = ? WHERE student_id = ? AND course_id = ?";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, scores.getScore());
            pstmt.setInt(2, scores.getStudentId());
            pstmt.setInt(3, scores.getCourseId());
            pstmt.executeUpdate();
            logger.info("成绩更新成功");
        } catch (Exception e) {
            logger.error("更新成绩失败", e);
        } finally {
            JDBCUtils.closeResources(conn, pstmt, null);
        }
    }

    @Override
    public void deleteScore(Integer id) {
        try{
            String sql = "DELETE FROM scores WHERE id = ?";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            logger.info("成绩删除成功");
        } catch (Exception e) {
            logger.error("删除成绩失败", e);
        } finally {
            JDBCUtils.closeResources(conn, pstmt, null);
        }
    }

    @Override
    public List<Scores> getScoresByStudentId(Integer studentId) {
        try{
            String sql = "SELECT * FROM scores WHERE student_id = ?";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            var rs = pstmt.executeQuery();
            List<Scores> scoresList = new ArrayList<>();
            while (rs.next()) {
                Scores scores = new Scores();
                scores.setId(rs.getInt("id"));
                scores.setStudentId(rs.getInt("student_id"));
                scores.setCourseId(rs.getInt("course_id"));
                scores.setScore(rs.getDouble("score"));
                scoresList.add(scores);
            }
            return scoresList;
        } catch (Exception e) {
            logger.error("查询学生成绩失败", e);
        } finally {
            JDBCUtils.closeResources(conn, pstmt, null);
        }
        return null;
    }

    @Override
    public List<Scores> getScoresByCourseId(Integer courseId) {
       try{
            String sql = "SELECT * FROM scores WHERE course_id = ?";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, courseId);
            var rs = pstmt.executeQuery();
            List<Scores> scoresList = new ArrayList<>();
            while (rs.next()) {
                Scores scores = new Scores();
                scores.setId(rs.getInt("id"));
                scores.setStudentId(rs.getInt("student_id"));
                scores.setCourseId(rs.getInt("course_id"));
                scores.setScore(rs.getDouble("score"));
                scoresList.add(scores);
            }
            return scoresList;
        } catch (Exception e) {
            logger.error("查询课程成绩失败", e);
        } finally {
            JDBCUtils.closeResources(conn, pstmt, null);
        }
        return null;
    }

    @Override
    public List<Scores> getAllScores() {
        try{
            String sql = "SELECT * FROM scores";
            conn = JDBCUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            var rs = pstmt.executeQuery();
            List<Scores> scoresList = new ArrayList<>();
            while (rs.next()) {
                Scores scores = new Scores();
                scores.setId(rs.getInt("id"));
                scores.setStudentId(rs.getInt("student_id"));
                scores.setCourseId(rs.getInt("course_id"));
                scores.setScore(rs.getDouble("score"));
                scoresList.add(scores);
            }
            return scoresList;
        } catch (Exception e) {
            logger.error("查询所有成绩失败", e);
        } finally {
            JDBCUtils.closeResources(conn, pstmt, null);
        }
        return null;
    }


}
