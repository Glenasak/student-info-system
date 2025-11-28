package com.cjlu.dao;

import java.util.List;

import com.cjlu.entity.Student;

public interface StudentDao {
    //添加学生
    public void addStudent(Student student);

    //删除学生
    public void deleteStudent(int studentId);

    //更新学生信息
    public void updateStudent(Student student);

    //根据学生ID获取学生信息
    public Student getStudentById(int studentId);

    //获取所有学生信息
    public List<Student> getAllStudents();

    //根据姓名模糊查询学生信息
    public List<Student> findStudentsByName(String name);

    //根据专业查询学生信息
    public List<Student> findStudentsByMajor(String major);

    //根据年龄范围查询学生信息
    public List<Student> findStudentsByAgeRange(int minAge, int maxAge);

    //根据性别查询学生信息
    public List<Student> findStudentsByGender(String gender);

    //根据班级查询学生信息
    public List<Student> findStudentsByClass(String className);

    //根据学院查询学生信息
    public List<Student> findStudentsByCollege(String collegeName);

}
