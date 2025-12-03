package com.cjlu.dao.impl;

import com.cjlu.dao.CourseDao;
import com.cjlu.entity.Course;
import com.cjlu.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
            Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
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
            Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
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
    public String getCourseNameBySemester(String semester) {
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
    public List<Map<String, Object>> getCourseByCourseId(String courseId) {
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM Course WHERE course_id=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,courseId);
            var resultSet=pstmt.executeQuery();
            List<Map<String,Object>>courses=new ArrayList<>();

            while (resultSet.next()){
                Course course=new Course();
                course.setCourseId(resultSet.getString("course_id"));
                course.setCourseName(resultSet.getString("course_name"));
                course.setCredit(String.valueOf(resultSet.getInt("credit")));
                course.setTeacher(resultSet.getString("teacher"));
                course.setSemester(resultSet.getString("semseter"));
                courses.add((Map<String, Object>) course);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("根据课程号查询课程信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByCourseName(String courseName) {
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM Course WHERE courseName=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,courseName);
            var resultSet=pstmt.executeQuery();
            List<Map<String,Object>>courses=new ArrayList<>();

            while (resultSet.next()){
                Course course=new Course();
                course.setCourseId(resultSet.getString("course_id"));
                course.setCourseName(resultSet.getString("course_name"));
                course.setCredit(String.valueOf(resultSet.getInt("credit")));
                course.setTeacher(resultSet.getString("teacher"));
                course.setSemester(resultSet.getString("semseter"));
                courses.add((Map<String, Object>) course);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("根据课程名模糊查询课程信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByCredit(int credit) {
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM Course WHERE credit=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,credit);
            var resultSet=pstmt.executeQuery();
            List<Map<String,Object>>courses=new ArrayList<>();

            while (resultSet.next()){
                Course course=new Course();
                course.setCourseId(resultSet.getString("course_id"));
                course.setCourseName(resultSet.getString("course_name"));
                course.setCredit(String.valueOf(resultSet.getInt("credit")));
                course.setTeacher(resultSet.getString("teacher"));
                course.setSemester(resultSet.getString("semseter"));
                courses.add((Map<String, Object>) course);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("根据学分查询课程信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByTeacher(String teacher) {
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM Course WHERE teacher=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,teacher);
            var resultSet=pstmt.executeQuery();
            List<Map<String,Object>>courses=new ArrayList<>();

            while (resultSet.next()){
                Course course=new Course();
                course.setCourseId(resultSet.getString("course_id"));
                course.setCourseName(resultSet.getString("course_name"));
                course.setCredit(String.valueOf(resultSet.getInt("credit")));
                course.setTeacher(resultSet.getString("teacher"));
                course.setSemester(resultSet.getString("semseter"));
                courses.add((Map<String, Object>) course);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("根据授课老师查询课程信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseBySemester(String semester) {
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM Course WHERE semester=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,semester);
            var resultSet=pstmt.executeQuery();
            List<Map<String,Object>>courses=new ArrayList<>();

            while (resultSet.next()){
                Course course=new Course();
                course.setCourseId(resultSet.getString("course_id"));
                course.setCourseName(resultSet.getString("course_name"));
                course.setCredit(String.valueOf(resultSet.getInt("credit")));
                course.setTeacher(resultSet.getString("teacher"));
                course.setSemester(resultSet.getString("semseter"));
                courses.add((Map<String, Object>) course);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("根据学期查询课程信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getAllCourse() {
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM Course WHERE course";
            pstmt=conn.prepareStatement(sql);
            var resultSet=pstmt.executeQuery();
            List<Map<String,Object>>courses=new ArrayList<>();

            while (resultSet.next()){
                Course course=new Course();
                course.setCourseId(resultSet.getString("course_id"));
                course.setCourseName(resultSet.getString("course_name"));
                course.setCredit(String.valueOf(resultSet.getInt("credit")));
                course.setTeacher(resultSet.getString("teacher"));
                course.setSemester(resultSet.getString("semseter"));
                courses.add((Map<String, Object>) course);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("根据学期查询课程信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByCreditRange(int minaCredit, int maxCredit) {
        try {
            conn=JDBCUtils.getConnection();
            String sql="SELECT*FROM Course WHERE credit BETWEEN ? AND ?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,minaCredit);
            pstmt.setInt(1,maxCredit);
            var resultSet=pstmt.executeQuery();
            List<Map<String,Object>>courses=new ArrayList<>();

            while (resultSet.next()){
                Course course=new Course();
                course.setCourseId(resultSet.getString("course_id"));
                course.setCourseName(resultSet.getString("course_name"));
                course.setCredit(String.valueOf(resultSet.getInt("credit")));
                course.setTeacher(resultSet.getString("teacher"));
                course.setSemester(resultSet.getString("semseter"));
                courses.add((Map<String, Object>) course);
            }
            JDBCUtils.closeResources(conn,pstmt,resultSet);
            return courses;
        }catch (Exception e) {
            logger.error("根据学分范围查询课程信息时出错：", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    //查询所有课程名
    public List<Map<String,Object>> getAllCourseName() {
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

            JDBCUtils.closeResources(conn, pstmt, resultSet);

            return courseList;
        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
            logger.error("Error retrieving all course", e);
            //关闭资源
            JDBCUtils.closeResources(conn, pstmt);
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
