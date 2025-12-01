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

    //根据课程号查询课程
    String getCourseByCourseId(String courseId) throws Exception;

    //根据授课老师查询课程
    String getCourseByTeacher(String teacher);

    //根据学分查询课程
    String getCourseByCredit(int credit);

    //根据学分查询课程
    String getCourseBySemester(String semester);

    //查询所有课程
    List<Map<String,Object>>getAllCourse();

    //根据Course类的属性创建课程表
    void creatCourseTable();

}
