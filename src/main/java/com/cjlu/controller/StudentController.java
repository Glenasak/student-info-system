package com.cjlu.controller;
import com.cjlu.entity.Student;
import java.util.List;

public interface StudentController {

    // 添加学生
    boolean addStudent(Student student);

    // 删除学生
    boolean deleteStudent(int studentId);

    // 更新学生信息
    boolean updateStudent(Student student);

    // 根据ID查询学生
    Student getStudentById(int studentId);

    // 查询所有学生
    List<Student> getAllStudents();

    // 根据姓名模糊查询学生
    List<Student> searchStudentsByName(String name);

    // 根据班级查询学生
    List<Student> getStudentsByClass(String className);

    // 根据年龄范围查询学生
    List<Student> getStudentsByAgeRange(int minAge, int maxAge);

    List<Student> getStudentsByMajorAndClass(String major, String className);
}
