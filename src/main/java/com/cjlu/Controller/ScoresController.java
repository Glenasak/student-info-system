package com.cjlu.Controller;

import java.util.List;

import com.cjlu.entity.Scores;

public interface ScoresController {

    //添加成绩方法
    public boolean addScore(Scores scores);

    //根据学生ID和课程ID查询成绩方法
    public Scores getScoreByStudentIdAndCourseId(Integer studentId, Integer courseId);

    //更新成绩方法
    public boolean updateScore(Scores scores);

    //删除成绩方法
    public boolean deleteScore(Integer id);

    //根据学生ID查询所有成绩方法
    public List<Scores> getScoresByStudentId(Integer studentId);

    //根据课程ID查询所有成绩方法
    public List<Scores> getScoresByCourseId(Integer courseId);

    //查询所有成绩方法
    public List<Scores> getAllScores();
}
