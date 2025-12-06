package com.cjlu.dao;

import java.util.Map;

public interface ScoreDao {

    //创建成绩表
    void createScoreTable();

    //添加成绩记录
    void addScoreRecord(int studentId, String courseCode, double score);

    //删除成绩记录
    void deleteScoreRecord(int studentId, String courseCode);

    //更新成绩记录
    void updateScoreRecord(int studentId, String courseCode, double newScore);

    //根据学生ID和课程代码获取成绩记录
    Integer getScoreRecord(int studentId, String courseCode);

    //查询某学生的所有成绩记录
    Map<String, Double> getAllScoresByStudentId(int studentId);

    //查询某课程的所有学生成绩记录
    Map<Integer, Double> getAllScoresByCourseCode(String courseCode);

    Map<String, Double> getScoreStatisticsByCourse(String courseCode);

    Map<String, Double> getScoreStatisticsByCourse(String courseCode, Integer minScore, Integer maxScore,
            Double avgScore);
}
