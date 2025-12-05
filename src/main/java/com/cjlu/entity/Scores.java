package com.cjlu.entity;

public class Scores {
    //字段
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Double score;

    //无参构造
    public Scores() {
    }

    //有参构造
    public Scores(Integer id, Integer studentId, Integer courseId, Double score) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
    }

    //getter和setter方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}