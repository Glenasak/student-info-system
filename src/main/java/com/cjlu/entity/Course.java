package com.cjlu.entity;

public class Course {
    private String courseId; //课程号
    private String courseName; //课程名
    private Integer credit;  //学分
    private String teacher;  //授课教师
    private String semester; //学期

    //无参构造
    public Course(){};

    //有参构造
    public Course(String courseId, String courseName, Integer credit, String teacher, String semester){
        this.courseId=courseId;
        this.courseName=courseName;
        this.credit=credit;
        this.teacher=teacher;
        this.semester=semester;
    }

    public String getCourseId(){
        return courseId;
    }

    public String getCourseName(){
        return courseName;
    }

    public Integer getCredit(){
        return credit;
    }

    public String getTeacher(){
        return teacher;
    }

    public String getSemester(){
        return semester;
    }

    public void setCourseId(String courseId){
        this.courseId= this.courseId;
    }

    public void setCourseName(String courseName){
        this.courseName= this.courseName;
    }

    public void setCredit(String credit){
        this.credit= this.credit;
    }

    public void setTeacher(String teacher){
        this.teacher= this.teacher;
    }

    public void setSemester(String semseter){
        this.semester=semester;
    }
}
