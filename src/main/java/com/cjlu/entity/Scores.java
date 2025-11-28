package com.cjlu.entity;

import java.util.Date;

public class Scores {
    //字段
    private Integer scoreId;
    private Integer studentId;
    private Integer courseId;
    private Integer score;
    private Date examDate;

    //构造函数
    public Scores(){}

    public Scores(Integer scoreId,Integer studentId,Integer courseId,Integer score,Date examDate){
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.courseId = courseId;
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

    public Integer getCourseId(){
        return courseId;
    }

    public Integer getScore(){
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

    public void setCourseId(Integer courseId){
        this.courseId = courseId;
    }

    public void setScore(Integer score){
        this.score = score;
    }

    public void setExamDate(Date examDate){
        this.examDate = examDate;
    }
}
