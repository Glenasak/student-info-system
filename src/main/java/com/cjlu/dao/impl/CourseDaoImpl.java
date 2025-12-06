package com.cjlu.dao.impl;

import com.cjlu.dao.CourseDao;
import com.cjlu.entity.Course;
import com.cjlu.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.*;

public class CourseDaoImpl implements CourseDao {
    //日志记录器
    private static final Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
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
            String sql = "INSERT INTO course(course_id,course_name,credit,teacher,semester)" + "VALUES(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, course.getCourseId());
            pstmt.setString(2, course.getCourseName());
            pstmt.setInt(3, course.getCredit());
            pstmt.setString(4, course.getTeacher());
            pstmt.setString(5, course.getSemester());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            if (e.getMessage().contains("NOT NULL")) {
                logger.error("Error add course name：it is null", e);
            } else {
                logger.error("Error add course name:", e);
            }
            return 0;
        }
    }

    @Override
    //根据课程号删除课程
    public int deleteCourseByCourseId(String courseId) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "DELETE FROM course WHERE course_id = ?";
            pstmt =conn.prepareStatement(sql);
            pstmt.setString(1, courseId);
            int result = pstmt.executeUpdate();
            logger.info("删除课程{}，影响行数：{}", courseId, result);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
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
            String sql = "UPDATE course SET course_name=?, credit=?, teacher=?, semester=? WHERE course_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, course.getCourseName());
            pstmt.setInt(2, course.getCredit());
            pstmt.setString(3, course.getTeacher());
            pstmt.setString(4, course.getSemester());
            pstmt.setString(5, course.getCourseId());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error add course name:", e);
            JDBCUtils.closeResources(conn, pstmt);
            return 0;
        }
    }

    @Override
    //根据课程号查询课程名
    public String getCourseNameByCourseId(String courseId) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT course_name FROM course WHERE  course_id=?";
            //开始查询
            pstmt = conn.prepareStatement(sql);
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
            logger.error("Error retrieving course name for courseId:" + courseId, e);
            JDBCUtils.closeResources(conn, pstmt);
            return null;
        }
    }

    @Override
    //根据授课老师查询课程名
    public String getCourseNameByTeacher(String teacher) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT course_name FROM course WHERE  teacher=?";
            //开始查询
            pstmt = conn.prepareStatement(sql);
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
            logger.error("Error retrieving course name for teacher:" + teacher, e);
            JDBCUtils.closeResources(conn, pstmt);
            return null;
        }
    }

    @Override
    //根据学分查询课程名
    public String getCourseNameByCredit(int credit) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT course_name FROM course WHERE  credit=?";
            //开始查询
            pstmt =conn.prepareStatement(sql);
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
            logger.error("Error retrieving course name for credit:" + credit, e);
            JDBCUtils.closeResources(conn, pstmt);
            return null;
        }
    }

    @Override
    public String getCourseNameBySemester(String semester) {
        try {
            conn = JDBCUtils.getConnection();
            String sql = "SELECT course_name FROM course WHERE  semester=?";
            //开始查询
            pstmt =conn.prepareStatement(sql);
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
            logger.error("Error retrieving course name for semester:" + semester, e);
            JDBCUtils.closeResources(conn, pstmt);
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByCourseId(String courseId) {
        List<Map<String,Object>>courses=new ArrayList<>();
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM course WHERE course_id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,courseId);
            ResultSet resultSet=pstmt.executeQuery();
            while (resultSet.next()){
                Map<String, Object> courseMap = new HashMap<>();
                courseMap.put("course_id", resultSet.getString("course_id"));
                courseMap.put("course_name", resultSet.getString("course_name"));
                courseMap.put("credit", resultSet.getInt("credit"));
                courseMap.put("teacher", resultSet.getString("teacher"));
                courseMap.put("semester", resultSet.getString("semester"));
                courses.add(courseMap);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("根据课程号查询课程信息时出错：", e);
            e.printStackTrace();
            return courses;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByCourseName(String courseName) {
        List<Map<String,Object>>courses=new ArrayList<>();
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM Course WHERE course_name LIKE ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%"+courseName+"%");
            var resultSet=pstmt.executeQuery();
            while (resultSet.next()){
                Map<String, Object> courseMap = new HashMap<>();
                courseMap.put("course_id", resultSet.getString("course_id"));
                courseMap.put("course_name", resultSet.getString("course_name"));
                courseMap.put("credit", resultSet.getInt("credit"));
                courseMap.put("teacher", resultSet.getString("teacher"));
                courseMap.put("semester", resultSet.getString("semester"));
                courses.add(courseMap);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("根据课程名模糊查询课程信息时出错：", e);
            e.printStackTrace();
            return courses;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByCredit(int credit) {
        List<Map<String,Object>>courses=new ArrayList<>();
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM course WHERE credit=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,credit);
            var resultSet=pstmt.executeQuery();
            while (resultSet.next()){
                Map<String, Object> courseMap = new HashMap<>();
                courseMap.put("course_id", resultSet.getString("course_id"));
                courseMap.put("course_name", resultSet.getString("course_name"));
                courseMap.put("credit", resultSet.getInt("credit"));
                courseMap.put("teacher", resultSet.getString("teacher"));
                courseMap.put("semester", resultSet.getString("semester"));
                courses.add(courseMap);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("根据学分查询课程信息时出错：", e);
            e.printStackTrace();
            return courses;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByTeacher(String teacher) {
        List<Map<String,Object>>courses=new ArrayList<>();
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM course WHERE teacher=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,teacher);
            var resultSet=pstmt.executeQuery();


            while (resultSet.next()){
                Map<String, Object> courseMap = new HashMap<>();
                courseMap.put("course_id", resultSet.getString("course_id"));
                courseMap.put("course_name", resultSet.getString("course_name"));
                courseMap.put("credit", resultSet.getInt("credit"));
                courseMap.put("teacher", resultSet.getString("teacher"));
                courseMap.put("semester", resultSet.getString("semester"));
                courses.add(courseMap);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("根据授课老师查询课程信息时出错：", e);
            e.printStackTrace();
            return courses;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseBySemester(String semester) {
        List<Map<String,Object>>courses=new ArrayList<>();
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM course WHERE semester=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,semester);
            var resultSet=pstmt.executeQuery();
            while (resultSet.next()){
                Map<String, Object> courseMap = new HashMap<>();
                courseMap.put("course_id", resultSet.getString("course_id"));
                courseMap.put("course_name", resultSet.getString("course_name"));
                courseMap.put("credit", resultSet.getInt("credit"));
                courseMap.put("teacher", resultSet.getString("teacher"));
                courseMap.put("semester", resultSet.getString("semester"));
                courses.add(courseMap);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("根据学期查询课程信息时出错：", e);
            e.printStackTrace();
            return courses;
        }
    }

    @Override
    public List<Map<String, Object>> getAllCourse() {
        List<Map<String,Object>>courses=new ArrayList<>();
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM course";
            pstmt=conn.prepareStatement(sql);
            var resultSet=pstmt.executeQuery();
            while (resultSet.next()){
                Map<String, Object> courseMap = new HashMap<>();
                courseMap.put("course_id", resultSet.getString("course_id"));
                courseMap.put("course_name", resultSet.getString("course_name"));
                courseMap.put("credit", resultSet.getInt("credit"));
                courseMap.put("teacher", resultSet.getString("teacher"));
                courseMap.put("semester", resultSet.getString("semester"));
                courses.add(courseMap);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("查询所有课程信息时出错：", e);
            e.printStackTrace();
            return courses;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByCreditRange(int minCredit, int maxCredit) {
        List<Map<String,Object>>courses=new ArrayList<>();
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM Course WHERE credit BETWEEN ? AND ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,minCredit);
            pstmt.setInt(2,maxCredit);
            var resultSet=pstmt.executeQuery();

            while (resultSet.next()){
                Map<String, Object> courseMap = new HashMap<>();
                courseMap.put("course_id", resultSet.getString("course_id"));
                courseMap.put("course_name", resultSet.getString("course_name"));
                courseMap.put("credit", resultSet.getInt("credit"));
                courseMap.put("teacher", resultSet.getString("teacher"));
                courseMap.put("semester", resultSet.getString("semester"));
                courses.add(courseMap);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("根据学分范围查询课程信息时出错：", e);
            e.printStackTrace();
            return courses;
        }
    }

    @Override
    //查询所有课程名
    public List<Map<String,Object>> getAllCourseName() {
        List<Map<String,Object>> courseList=new ArrayList<>();
        try {
            conn=JDBCUtils.getConnection();
            String sql= "SELECT course_name FROM course";
            pstmt=conn.prepareStatement(sql);
            ResultSet resultSet =pstmt.executeQuery();

            while(resultSet.next()){
                Map<String,Object> courseInfo = new HashMap<>();
                courseInfo.put("course_name",resultSet.getString("course_name"));
                courseList.add(courseInfo);
            }

            JDBCUtils.closeResources(conn, pstmt, resultSet);
            return courseList;
        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
            logger.error("Error retrieving all course", e);
            //关闭资源
            JDBCUtils.closeResources(conn, pstmt);
            return courseList;
        }

    }


    @Override
    public void createCourseTable() {
        try {
            conn = JDBCUtils.getConnection();
            String sql =  "CREATE TABLE course(" +
                    "course_id VARCHAR(10) PRIMARY KEY NOT NULL," +
                    "course_name VARCHAR(50) NOT NULL," +
                    "credit INT NOT NULL," +
                    "teacher VARCHAR(20) NOT NULL," +
                    "semester VARCHAR(10) NOT NULL" +
                    ")";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            JDBCUtils.closeResources(conn, pstmt);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error creating course Table:", e);
            JDBCUtils.closeResources(conn, pstmt);
        }
    }

}
