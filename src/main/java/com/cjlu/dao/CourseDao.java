package com.cjlu.dao;

import com.cjlu.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseDao{
    //添加课程
    int addCourse(Course course);

    //根据课程号删除课程
    int deleteCourseByCourseId(String courseId);

    //更新课程信息
    int updateCourse(Course course);

    //根据课程号查询课程名
    String getCourseNameByCourseId(String courseId);

    //根据授课老师查询课程名
    String getCourseNameByTeacher(String teacher);

    //根据学分查询课程名
    String getCourseNameByCredit(int credit);

    //根据学期查询课程名
    String getCourseNameBySemester(String semester);

    //根据课程号查询课程
    List<Map<String,Object>> getCourseByCourseId(String courseId);

    //根据课程名模糊查询课程
    List<Map<String,Object>>getCourseByCourseName(String courseName);

    //根据学分查询课程
    List<Map<String,Object>>getCourseByCredit(int credit);

    //根据授课老师查询课程
    List<Map<String,Object>>getCourseByTeacher(String teacher);

    //根据学期查询课程
    List<Map<String,Object>>getCourseBySemester(String semester);

    //查询所有课程
    List<Map<String,Object>> getAllCourse();

    //根据学分范围查询课程
    List<Map<String,Object>>getCourseByCreditRange(int minaCredit,int maxCredit);

    //查询所有课程名
    List<Map<String,Object>> getAllCourseName();

    //根据Course类的属性创建课程表
    void createCourseTable();

}
