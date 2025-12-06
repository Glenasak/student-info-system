package com.cjlu.service;

import java.util.List;

import com.cjlu.entity.Scores;

public interface ScoreService {

    //添加成绩
    public void addScore(int studentId, String courseCode, double score);

    //根据成绩ID删除成绩
    public void deleteScoreById(Integer scoreId);

    //根据成绩ID更新成绩信息
    public void updateScoreById(Scores score);

    //根据成绩ID查询成绩信息
    public Scores getScoreById(Integer scoreId);

    //查询所有成绩信息
    public List<Scores> getAllScores();

    //根据学生ID查询成绩信息
    public List<Scores> getScoresByStudentId(Integer studentId);

    //根据课程名称查询成绩信息
    public List<Scores> getScoresByCourseName(String courseName);

    List<Scores> getScoresByCourseAndScoreRange(String courseName, double minScore, double maxScore);

    List<Scores> findScoresByConditions(String major, String className, String courseCode);
}
