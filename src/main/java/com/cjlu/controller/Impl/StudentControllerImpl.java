package com.cjlu.Controller.Impl;

import java.util.List;

import com.cjlu.Controller.StudentController;
import com.cjlu.entity.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjlu.service.impl.StudentServiceImpl;

public class StudentControllerImpl implements StudentController{

    //日志记录器
    private static final Logger logger = LoggerFactory.getLogger(StudentControllerImpl.class);

    //服务层对象
    private StudentServiceImpl studentService = new StudentServiceImpl();


    //添加学生方法
    @Override
    public boolean addStudent(Student student) {
        try {
            studentService.addStudent(student);
            logger.info("调用添加学生服务成功");
            return true;
        } catch (Exception e) {
            logger.error("调用添加学生服务失败", e);
            return false;
        }
    }


    //根据ID删除学生方法
    @Override
    public boolean deleteStudent(Integer id) {
        try {
            studentService.deleteStudentById(id);
            logger.info("调用删除学生服务成功");
            return true;
        } catch (Exception e) {
            logger.error("调用删除学生服务失败", e);
            return false;
        }
    }

    //更新学生方法
    @Override
    public boolean updateStudent(Student student) {
        try {
            studentService.updateStudentById(student);
            logger.info("调用更新学生服务成功");
            return true;
        } catch (Exception e) {
            logger.error("调用更新学生服务失败", e);
            return false;
        }
    }

    //根据ID查询学生方法
    @Override
    public Student getStudentById(Integer id) {
        try {
            return studentService.getStudentById(id);
        } catch (Exception e) {
            logger.error("调用根据ID查询学生服务失败", e);
            return null;
        }
    }

    //查询所有学生方法
    @Override
    public List<Student> getAllStudents() {
        try {
            return studentService.getAllStudents();
        } catch (Exception e) {
            logger.error("调用查询所有学生服务失败", e);
            return null;
        }
    }

    //根据姓名查询学生方法
    @Override
    public Student getStudentsByName(String name) {
        try {
            return studentService.getStudentByName(name);
        } catch (Exception e) {
            logger.error("调用根据姓名查询学生服务失败", e);
            return null;
        }
    }

    //根据年龄范围查询学生方法
    @Override
    public List<Student> getStudentsByAgeRange(Integer minAge, Integer maxAge) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentsByAgeRange'");
    }

    //根据专业查询学生方法
    @Override
    public List<Student> getStudentsByMajor(String major) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentsByMajor'");
    }

    //根据班级查询学生方法
    @Override
    public List<Student> getStudentsByClass(String className) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentsByClass'");
    }

    //根据性别查询学生方法
    @Override
    public List<Student> getStudentsByGender(String gender) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStudentsByGender'");
    }

}
