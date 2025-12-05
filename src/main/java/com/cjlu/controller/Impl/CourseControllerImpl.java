package com.cjlu.controller.Impl;

import com.cjlu.controller.CourseController;
import com.cjlu.entity.Course;
import com.cjlu.service.CourseService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;


public class CourseControllerImpl implements CourseController {
    private static final Logger logger= (Logger) LoggerFactory.getLogger(CourseControllerImpl.class);
    private final CourseService courseService;

    //将依赖注入Service层
    public CourseControllerImpl(CourseService courseService){
        this.courseService=courseService;
    }

    //初始化课程表
    public void initCourseTable() {
        try {
            courseService.createCourseTable();
            logger.info("课程表初始化成功");
        } catch (Exception e) {
            logger.error("课程表初始化失败");
            throw new RuntimeException("课程表初始化失败：" + e.getMessage());
        }
    }

    //加载数据
    public List<Map<String,Object>> loadAllCourse(){
        try {
                List<Map<String,Object>>courses=courseService.getAllCourse();
                logger.info("加载课程数据成功,共{}条",courses.size());
                return courses;
        }catch (Exception e){
            logger.error("加载课程数据失败",e);
            throw new RuntimeException("加载数据失败"+e.getMessage());
        }
    }

    //添加课程
    public String addCourse(Course course){
        if (isEmpty(course.getCourseId()) || isEmpty(course.getCourseName())) {
            return "课程ID和名称不能为空";
        }
        if (isEmpty(course.getTeacher()) || isEmpty(course.getSemester())) {
            return "教师和学期不能为空";
        }
        if (course.getCredit() == null || course.getCredit() < 0) {
            return "学分必须为非负整数";
        }
        // 调用Service处理
        try {
            courseService.addCourse(course);
            return "添加成功";
        } catch (Exception e) {
            return "添加失败：" + e.getMessage();
        }

    }

    //修改课程
    public String updateCourse(Course course){
        try {
            courseService.updateCourse(course);
            logger.info("修改课程成功，课程号：{}",course.getCourseId());
            return "课程修改成功！";
        }catch (Exception e){
            logger.error("修改课程失败，课程号：{}",course.getCourseId());
            return "修改课程失败"+e.getMessage();
        }
    }

    //删除课程
    public String deleteCourse(Course course){
        try {
            courseService.deleteCourseByCourseId(course.getCourseId());
            logger.info("删除课程成功，课程号：{}",course.getCourseId());
            return "课程删除成功！";
        }catch (Exception e){
            logger.error("删除课程失败，课程号：{}",course.getCourseId());
            return "删除课程失败"+e.getMessage();
        }
    }

    //查询课程
    public List<Map<String, Object>> searchCourse(String keyword) {
        try {
            // 根据关键字查询逻辑（例如模糊查询课程名）
            return courseService.getCourseByCourseName(keyword);
        } catch (Exception e) {
            logger.error("按关键字查询课程失败: {}", keyword, e);
            throw new RuntimeException("查询失败: " + e.getMessage());
        }
    }

    public boolean isEmpty(String str){
        return str==null||str.trim().isEmpty();
    }


    public boolean isNumber(String str){
        try {
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    // 实现getCourseByCourseName方法
    public List<Map<String, Object>> getCourseByCourseName(String keyword) {
        return courseService.getCourseByCourseName(keyword);
    }

    // 实现deleteCourseByCourseId方法
    public List<Map<String, Object>> deleteCourseByCourseId(String courseId) {
        courseService.deleteCourseByCourseId(courseId);
        // 删除后返回剩余课程列表
        return loadAllCourse();
    }

}
