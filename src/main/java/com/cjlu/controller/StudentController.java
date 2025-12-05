package com.cjlu.Controller;

import java.util.List;

import com.cjlu.entity.Student;

public interface StudentController {
    //添加学生方法
    public boolean addStudent(Student student);

    //根据ID删除学生方法
    public boolean deleteStudent(Integer id);

    //更新学生方法
    public boolean updateStudent(Student student);

    //根据ID查询学生方法
    public Student getStudentById(Integer id);

    //查询所有学生方法
    public List<Student> getAllStudents();

    //根据姓名查询学生方法
    public Student getStudentsByName(String name);

    //根据年龄范围查询学生方法
    public List<Student> getStudentsByAgeRange(Integer minAge, Integer maxAge);

    //根据专业查询学生方法
    public List<Student> getStudentsByMajor(String major);

    //根据班级查询学生方法
    public List<Student> getStudentsByClass(String className);

    //根据性别查询学生方法
    public List<Student> getStudentsByGender(String gender);
}
