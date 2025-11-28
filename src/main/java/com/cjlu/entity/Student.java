package com.cjlu.entity;

import java.util.Date;

public class Student {
    private String studentId;   // 学号（主键）
    private String name;        // 姓名
    private String gender;      // 性别
    private Integer age;        // 年龄
    private String major;       // 专业
    private String className;   // 班级
    private Date admissionDate; // 入学日期
    private String phone;       // 手机号
    private String email;       // 邮箱

    //无参数构造器

    public Student(){}

    //带参数构造器

    public Student(String studentId,String name,String gender,Integer age,
        String major,String className,Date admissionDate,String phone,
        String email){
            this.studentId = studentId;
            this.name = name;
            this.gender = gender;
            this.age = age;
            this.major = major;
            this.className = className;
            this.admissionDate = admissionDate;
            this.phone = phone;
            this.email = email;
    }

    //getter方法
    public String getStudentId(){
        return studentId;
    }

    public String getName(){
        return name;
    }

    public String getGender(){
        return gender;
    }

    public Integer getAge(){
        return age;
    }

    public String getMajor(){
        return major;
    }

    public String getClassName(){
        return className;
    }

    public Date getAdmissionDate(){
        return admissionDate;
    }

    public String getPhone(){
        return phone;
    }

    public String getEmail(){
        return email;
    }

    //setter方法

    public void setStudentId(String studentId){
        this.studentId = studentId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public void setMajor(String major){
        this.major = major;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public void setAdmissionDate(Date admissionDate){
        this.admissionDate = admissionDate;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
