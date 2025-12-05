package com.cjlu.service;

import java.util.List;

import com.cjlu.entity.Scores;

public interface ScoresService {

    //创建成绩表
    public void createScoresTable();

    //添加成绩
    public void addScore(Scores scores);

    //根据学生ID和课程ID查询成绩
    public Scores getScoreByStudentIdAndCourseId(Integer studentId, Integer courseId);

    //更新成绩
    public void updateScore(Scores scores);

    //删除成绩
    public void deleteScore(Integer id);
    
    //根据学生ID查询所有成绩
    public List<Scores> getScoresByStudentId(Integer studentId);

    //根据课程ID查询所有成绩
    public List<Scores> getScoresByCourseId(Integer courseId);

    //查询所有成绩
    public List<Scores> getAllScores();


}
