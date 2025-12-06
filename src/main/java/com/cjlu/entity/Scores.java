package com.cjlu.entity;

import java.util.Date;

public class Scores {
    //字段
    private Integer scoreId;
    private Integer studentId;
    private String courseCode;
    private Double score;
    private Date examDate;

    //构造函数
    public Scores(){}

    public Scores(Integer scoreId,Integer studentId,String courseCode,Double score,Date examDate){
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.score = score;
        this.examDate = examDate;
    }

    //getter方法
    public Integer getScoreId(){
        return scoreId;
    }

    public Integer getStudentId(){
        return studentId;
    }

    public String getCourseCode(){
        return courseCode;
    }

    public Double getScore(){
        return score;
    }

    public Date getExamDate(){
        return examDate;
    }

    //setter方法
    public void setScoreId(Integer scoreId){
        this.scoreId = scoreId;
    }

    public void setStudnetId(Integer studentId){
        this.studentId = studentId;
    }

    public void setCourseCode(String courseCode){
        this.courseCode = courseCode;
    }

    public void setScore(Double score){
        this.score = score;
    }

    public void setExamDate(Date examDate){
        this.examDate = examDate;
    }
}
