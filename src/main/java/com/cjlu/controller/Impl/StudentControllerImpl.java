package com.cjlu.controller.Impl;

import java.util.List;

import com.cjlu.controller.StudentController;
import com.cjlu.entity.Student;
import com.cjlu.service.impl.StudentServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentControllerImpl implements StudentController{

    //创建日志对象
    private static final Logger logger = LoggerFactory.getLogger(StudentControllerImpl.class);

    //初始化Service对象
    private StudentServiceImpl studentService = new StudentServiceImpl();

    //这个方法的功能是添加学生
    @Override
    public boolean addStudent(Student student) {
        try {
            studentService.addStudent(student);
            return true;
        } catch (Exception e) {
            logger.error("添加学生失败: {}", e.getMessage());
            return false;
        }
    }

    //这个方法的功能是删除学生
    @Override
    public boolean deleteStudent(int studentId) {
        try {
            studentService.deleteStudentById(studentId);
            return true;
        } catch (Exception e) {
            logger.error("删除学生失败: {}", e.getMessage());
            return false;
        }
    }


    //这个方法的功能是更新学生信息
    @Override
    public boolean updateStudent(Student student) {
        try {
            studentService.updateStudentById(student);
            return true;
        } catch (Exception e) {
            logger.error("更新学生信息失败: {}", e.getMessage());
            return false;
        }
    }


    //这个方法的功能是根据ID查询学生
    @Override
    public Student getStudentById(int studentId) {
        try {
            return studentService.getStudentById(studentId);
        } catch (Exception e) {
            logger.error("根据ID查询学生失败: {}", e.getMessage());
            return null;
        }
    }

    //这个方法的功能是查询所有学生
    @Override
    public List<Student> getAllStudents() {
        try {
            return studentService.getAllStudents();
        } catch (Exception e) {
            logger.error("查询所有学生失败: {}", e.getMessage());
            return null;
        }
    }


    //这个方法的功能是根据姓名模糊查询学生
    @Override
    public List<Student> searchStudentsByName(String name) {
        try {
            return studentService.searchStudentsByName(name);
        } catch (Exception e) {
            logger.error("根据姓名模糊查询学生失败: {}", e.getMessage());
            return null;
        }
    }

    //这个方法的功能是根据班级查询学生
    @Override
    public List<Student> getStudentsByClass(String className) {
        try {
            return studentService.getStudentsByClass(className);
        } catch (Exception e) {
            logger.error("根据班级查询学生失败: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Student> getStudentsByAgeRange(int minAge, int maxAge) {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentsByAgeRange'");
    }

    //依据专业和班级查询学生
    @Override
    public List<Student> getStudentsByMajorAndClass(String major, String className) {
        try {
            return studentService.findStudentsByMajorAndClass(major, className);
        } catch (Exception e) {
            logger.error("依据专业和班级查询学生失败: {}", e.getMessage());
            return null;
        }
    }

}
