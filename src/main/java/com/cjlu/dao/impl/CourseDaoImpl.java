package com.cjlu.dao.impl;

import com.cjlu.dao.CourseDao;
import com.cjlu.entity.Course;
import com.cjlu.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.*;

public class CourseDaoImpl implements CourseDao {
    //链接数据库
    Connection conn = null;
    PreparedStatement pstmt = null;

    @Override
    //添加课程
    public int addCourse(Course course) {
        //进行添加
        try {
            conn = JDBCUtils.getConnection();
            //准备SQL语句
            String sql = "INSERT INTO course(course(course_id,course_name,credit,teacher,semester" + "VALUES(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, course.getCourseId());
            pstmt.setString(2, course.getCourseName());
            pstmt.setInt(3, course.getCredit());
            pstmt.setString(4, course.getTeacher());
            pstmt.setString(5, course.getSemester());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
            logger.error("Error add course name:", e);
            JDBCUtils.closeResources(conn, pstmt);
            return 0;
        }
    }

    @Override
    //根据课程号删除课程
    public int deleteCourseByCourseId(String courseId) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "DELETE FROM course WHERE CourseId = ?";
            pstmt =conn.prepareStatement(sql);
            pstmt.setString(1, courseId);
            pstmt.executeUpdate();
            return pstmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
            logger.error("Error delete course name:", e);
            JDBCUtils.closeResources(conn, pstmt);
            return 0;
        }
    }

    @Override
    //更新课程信息
    public int updateCourse(Course course) {
        try {
            conn = JDBCUtils.getConnection();
            //准备SQL语句
            String sql = "UPDATE INTO course(course(course_id,course_name,credit,teacher,semester" + "VALUES(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, course.getCourseId());
            pstmt.setString(2, course.getCourseName());
            pstmt.setInt(3, course.getCredit());
            pstmt.setString(4, course.getTeacher());
            pstmt.setString(5, course.getSemester());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
            logger.error("Error add course name:", e);
            JDBCUtils.closeResources(conn, pstmt);
            return 0;
        }
    }

    @Override
    //根据课程号查询课程名
    public String getCourseByCourseId(String courseId) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT course_name FROM course WHERE  course_id=?";
            //开始查询
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1, courseId);
            ResultSet resultSet = pstmt.executeQuery();
            String course_name = null;
            if (resultSet.next()) {
                course_name = resultSet.getString("course_name");
            }
            JDBCUtils.closeResources(conn, pstmt, resultSet);
            return course_name;
        } catch (Exception e) {
            e.printStackTrace();
            Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
            logger.error("Error retrieving course name for courseId:" + courseId, e);
            JDBCUtils.closeResources(conn, pstmt);
            return null;
        }
    }

    @Override
    //根据授课老师查询课程名
    public String getCourseByTeacher(String teacher) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT course_name FROM course WHERE  teacher=?";
            //开始查询
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,teacher);
            ResultSet resultSet = pstmt.executeQuery();
            String course_name = null;
            if (resultSet.next()) {
                course_name = resultSet.getString("course_name");
            }
            JDBCUtils.closeResources(conn, pstmt, resultSet);
            return course_name;
        } catch (Exception e) {
            e.printStackTrace();
            Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
            logger.error("Error retrieving course name for teacher:" + teacher, e);
            JDBCUtils.closeResources(conn, pstmt);
            return null;
        }
    }

    @Override
    //根据学分查询课程名
    public String getCourseByCredit(int credit) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT course_name FROM course WHERE  credit=?";
            //开始查询
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1,credit);
            ResultSet resultSet = pstmt.executeQuery();
            String course_name = null;
            if (resultSet.next()) {
                course_name = resultSet.getString("course_name");
            }
            JDBCUtils.closeResources(conn, pstmt, resultSet);
            return course_name;
        } catch (Exception e) {
            e.printStackTrace();
            Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
            logger.error("Error retrieving course name for credit:" + credit, e);
            JDBCUtils.closeResources(conn, pstmt);
            return null;
        }
    }

    @Override
    public String getCourseBySemester(String semester) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT course_name FROM course WHERE  semester=?";
            //开始查询
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,semester);
            ResultSet resultSet = pstmt.executeQuery();
            String course_name = null;
            if (resultSet.next()) {
                course_name = resultSet.getString("course_name");
            }
            JDBCUtils.closeResources(conn, pstmt, resultSet);
            return course_name;
        } catch (Exception e) {
            e.printStackTrace();
            Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
            logger.error("Error retrieving course name for semester:" + semester, e);
            JDBCUtils.closeResources(conn, pstmt);
            return null;
        }
    }

    @Override
    //查询所有课程
    public List<Map<String,Object>> getAllCourse() {
        try {
            conn=JDBCUtils.getConnection();
            String sql= "SELECT course_name FROM course";
            pstmt=(PreparedStatement) conn.prepareStatement(sql);
            ResultSet resultSet =pstmt.executeQuery();
            List<Map<String,Object>> courseList=new ArrayList<>();
            while(resultSet.next()){
                Map<String,Object> courseInfo = new java.util.HashMap<>();
                courseInfo.put("course_name",resultSet.getString("course_name"));
            }

            com.cjlu.util.JDBCUtils.closeResources(conn, pstmt, resultSet);

            return courseList;
        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error retrieving all users", e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(conn, pstmt);
            return null;
        }
    }

    @Override
    public void creatCourseTable() {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "CREATE TABLE Course(" +
                    "course_id VARCHAR(10)," +
                    "course_name VARCHAR(50) NOT NULL," +
                    "credit INT NOT NULL," +
                    "teacher VARCHAR(20) NOT NULL," +
                    "semester VARCHAR(10) NOT NULL" +
                    ")";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            JDBCUtils.closeResources(conn, pstmt, null);
        } catch (Exception e) {
            e.printStackTrace();
            Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
            logger.error("Error creating course Table:", e);
            JDBCUtils.closeResources(conn, pstmt);
        }
    }

}
