package com.cjlu.service.impl;

import com.cjlu.dao.CourseDao;
import com.cjlu.entity.Course;
import com.cjlu.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class CourseServiceImpl implements CourseService {
    //日志记录器
    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    //数据访问对象
    private CourseDao courseDao;

    

    public void addCourse(Course course){
        try {
                courseDao.addCourse(course);
                logger.info("添加课程成功：{}",course);
        }catch(Exception e){
            logger.error("添加课程失败：{}",course,e );
            throw e;
        }
    }

    @Override
    public void deleteCourseByCourseId(String courseId) {
        try {
            courseDao.deleteCourseByCourseId(courseId);
            logger.info("删除课程成功：{}",courseId);
        }catch(Exception e){
            logger.error("删除课程失败：{}",courseId,e );
            throw e;
        }
    }

    @Override
    public void updateCourse(Course course) {
        try {
            courseDao.updateCourse(course);
            logger.info("更新课程成功：{}",course);
        }catch(Exception e){
            logger.error("更新课程失败：{}",course,e );
            throw e;
        }
    }

    @Override
    public String getCourseNameByCourseId(String courseId) {
        try {
            String course = courseDao.getCourseNameByCourseId(courseId);
            logger.info("通过课程号查询课程名成功: {}", courseId);
            return course;
        } catch (Exception e) {
            logger.error("通过课程号查询课程名失败: {}", courseId, e);
            throw e;
        }
    }

    @Override
    public String getCourseNameByTeacher(String teacher) {
        try {
            String course = courseDao.getCourseNameByTeacher(teacher);
            logger.info("通过授课老师查询课程名成功: {}", teacher);
            return course;
        } catch (Exception e) {
            logger.error("通过授课老师查询课程名失败: {}", teacher, e);
            throw e;
        }
    }

    @Override
    public String getCourseNameByCredit(int credit) {
        try {
            String course = courseDao.getCourseNameByCredit(credit);
            logger.info("通过学分查询课程名成功: {}", credit);
            return course;
        } catch (Exception e) {
            logger.error("通过学分查询课程名失败: {}", credit, e);
            throw e;
        }
    }

    @Override
    public String getCourseNameBySemester(String semester) {
        try {
            String course = courseDao.getCourseNameBySemester(semester);
            logger.info("通过学期查询课程名成功: {}", semester);
            return course;
        } catch (Exception e) {
            logger.error("通过学期查询课程名失败: {}", semester, e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByCourseId(String courseId) {
        try {
            List<Map<String, Object>> course = courseDao.getCourseByCourseId(courseId);
            logger.info("查询课程成功: {}", courseId);
            return course;
        } catch (Exception e) {
            logger.error("查询课程失败: {}", courseId, e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByCourseName(String courseName) {
        try {
            List<Map<String, Object>> course = courseDao.getCourseByCourseName(courseName);
            logger.info("通过课程模糊查询课程成功: {}", courseName);
            return course;
        } catch (Exception e) {
            logger.error("通过课程模糊查询课程失败: {}", courseName, e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByCredit(int credit) {
        try {
            List<Map<String, Object>> course = courseDao.getCourseByCredit(credit);
            logger.info("通过学分查询课程成功: {}", credit);
            return course;
        } catch (Exception e) {
            logger.error("通过学分查询课程失败: {}", credit, e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByCreditRange(int minCredit, int maxCredit) {
        try {
            List<Map<String, Object>> course = courseDao.getCourseByCreditRange(minCredit,maxCredit);
            logger.info("通过学分范围查询课程成功: {},{}", minCredit,maxCredit);
            return course;
        } catch (Exception e) {
            logger.error("通过学分范围查询课程失败: {},{}", minCredit,maxCredit, e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseBySemester(String semester) {
        try {
            List<Map<String, Object>> course = courseDao.getCourseBySemester(semester);
            logger.info("通过学期查询课程成功: {}", semester);
            return course;
        } catch (Exception e) {
            logger.error("通过学期查询课程失败: {}", semester, e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getCourseByTeacher(String teacher) {
        try {
            List<Map<String, Object>> course = courseDao.getCourseByTeacher(teacher);
            logger.info("通过授课老师查询课程成功: {}", teacher);
            return course;
        } catch (Exception e) {
            logger.error("通过授课老师查询课程失败: {}", teacher, e);
            throw e;
        }
    }

    @Override
    public List<Map<String, Object>> getAllCourse() {
        try {
            List<Map<String, Object>> course = courseDao.getAllCourse();
            logger.info("查询所有课程成功");
            return course;
        } catch (Exception e) {
            logger.error("查询所有课程失败", e);
            throw e;
        }
    }

    @Override
    public void createCourseTable() {
        try {
            courseDao.createCourseTable();
            logger.info("创建课程表成功！");
        } catch (Exception e) {
            logger.error("创建课程表失败", e);
            throw new RuntimeException("创建课程表失败，请联系管理员", e);
        }
    }
}
