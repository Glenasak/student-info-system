package com.cjlu.controller;

import java.util.List;

import com.cjlu.entity.Scores;

/**
 * 成绩管理控制层接口：定义 UI 可以调用的业务入口。
 */
public interface ScoreController {

    List<Scores> listAll();

    Scores getById(Integer scoreId);

    List<Scores> listByStudentId(Integer studentId);

    List<Scores> listByCourseName(String courseName);

    List<Scores> searchByConditions(String major, String className, String courseCode);

    void add(Integer studentId, String courseCode, Double score);

    void update(Scores scores);

    void delete(Integer scoreId);
}

