package com.cjlu.service;

import java.util.List;

import com.cjlu.entity.Student;

public interface StudentService {

    //添加学生
    public void addStudent(Student student);

    //根据学号删除学生
    public void deleteStudentById(Integer studentId);

    //根据学号更新学生信息
    public void updateStudentById(Student student);

    //根据学号查询学生信息
    public Student getStudentById(Integer studentId);

    //根据姓名查询学生信息
    public Student getStudentByName(String name);

    //查询所有学生信息
    public java.util.List<Student> getAllStudents();

    //根据专业查询学生信息
    public java.util.List<Student> getStudentsByMajor(String major);

    //根据班级查询学生信息
    public java.util.List<Student> getStudentsByClassName(String className);

    //根据年龄范围查询学生信息
    public java.util.List<Student> getStudentsByAgeRange(int minAge, int maxAge);

    //根据入学日期范围查询学生信息
    public java.util.List<Student> getStudentsByAdmissionDateRange(java.util.Date startDate,
            java.util.Date endDate);

    //根据手机号查询学生信息
    public Student getStudentByPhone(String phone);

    //根据邮箱查询学生信息
    public Student getStudentByEmail(String email);

    //统计学生总数
    public int getStudentCount();

    //分页查询学生信息
    public java.util.List<Student> getStudentsByPage(int pageNumber, int pageSize);

    //根据专业统计学生人数
    public java.util.Map<String, Integer> getStudentCountByMajor();

    //根据班级统计学生人数
    public java.util.Map<String, Integer> getStudentCountByClassName();

    //批量添加学生
    public void addStudentsBatch(java.util.List<Student> students);

    //批量删除学生
    public void deleteStudentsBatch(java.util.List<Integer> studentIds);

    //批量更新学生信息
    public void updateStudentsBatch(java.util.List<Student> students);

    //根据关键字搜索学生信息（姓名、专业、班级）
    public java.util.List<Student> searchStudentsByKeyword(String keyword);

    //根据学生信息生成报表（示例方法，具体实现可根据需求调整）
    public byte[] generateStudentReport();


    //根据专业和班级查询学生信息
    List<Student> findStudentsByMajorAndClass(String major, String className);
    
}
