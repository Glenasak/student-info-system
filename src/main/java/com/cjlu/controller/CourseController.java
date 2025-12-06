package com.cjlu.controller;

import com.cjlu.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseController {
    //初始化课程表
    void initCourseTable();

    //启动时加载所有课程数据到表格
    List<Map<String,Object>> loadAllCourse();

    //添加课程
    String addCourse(Course course);

    //更新课程
    String updateCourse(Course course);

    //删除课程
    String deleteCourse(Course course);

    //查询课程
    List<Map<String,Object>>searchCourse(String keyword);

    //数据校验：非空检查
    boolean isEmpty(String str);

    //数据校验：数字检查
    boolean isNumber(String str);


    List<Map<String,Object>> getCourseByCourseName(String keyword);

    List<Map<String,Object>> deleteCourseByCourseId(String courseId);

}
