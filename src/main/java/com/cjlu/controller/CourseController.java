package com.cjlu.Controller;

import java.util.List;

import com.cjlu.entity.Course;

public interface CourseController {

    //添加课程方法
    public boolean addCourse(Course course);

    //根据ID删除课程方法
    public boolean deleteCourse(Integer id);

    //更新课程方法
    public boolean updateCourse(Course course);

    //根据ID查询课程方法
    public Course getCourseById(Integer id);
    
    //查询所有课程方法
    public List<Course> getAllCourses();

    //根据课程名称查询课程方法
    public Course getCourseByName(String name);

    //根据学分范围查询课程方法
    public List<Course> getCoursesByCreditRange(Integer minCredit, Integer maxCredit);

    //根据教师查询课程方法
    public List<Course> getCoursesByInstructor(String instructor);
}
