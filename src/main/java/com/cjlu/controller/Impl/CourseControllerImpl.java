package com.cjlu.Controller.Impl;

import java.util.List;

import com.cjlu.Controller.CourseController;
import com.cjlu.entity.Course;

import com.cjlu.service.impl.CourseServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseControllerImpl implements CourseController {

    //日志记录器
    private static final Logger logger = LoggerFactory.getLogger(CourseControllerImpl.class);

    //服务层对象
    private CourseServiceImpl courseService = new CourseServiceImpl();

    //添加课程方法
    @Override
    public boolean addCourse(Course course) {
        try {
            courseService.addCourse(course);
            logger.info("调用添加课程服务成功");
            return true;
        } catch (Exception e) {
            logger.error("调用添加课程服务失败", e);
            return false;
        }
    }

    @Override
    public boolean deleteCourse(Integer id) {
        try {
            courseService.deleteCourseByCourseId(id.toString());
            logger.info("调用删除课程服务成功");
            return true;
        } catch (Exception e) {
            logger.error("调用删除课程服务失败", e);
            return false;
        }
    }

    @Override
    public boolean updateCourse(Course course) {
        try {
            courseService.updateCourse(course);
            logger.info("调用更新课程服务成功");
            return true;
        } catch (Exception e) {
            logger.error("调用更新课程服务失败", e);
            return false;
        }
    }

    @Override
    public Course getCourseById(Integer id) {
       // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCourseById'");
    }

    @Override
    public List<Course> getAllCourses() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCourses'");
    }

    @Override
    public Course getCourseByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCourseByName'");
    }

    @Override
    public List<Course> getCoursesByCreditRange(Integer minCredit, Integer maxCredit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoursesByCreditRange'");
    }

    @Override
    public List<Course> getCoursesByInstructor(String instructor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoursesByInstructor'");
    }

}
