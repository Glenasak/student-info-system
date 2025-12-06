package com.cjlu.controller.Impl;

import java.util.List;

import com.cjlu.controller.ScoreController;
import com.cjlu.entity.Scores;
import com.cjlu.service.ScoreService;
import com.cjlu.service.impl.ScoreServiceImpl;

/**
 * ScoreController 实现类：封装 UI 与服务层交互，做基本校验与错误包装。
 */
public class ScoreControllerImpl implements ScoreController {

    private final ScoreService scoreService;

    public ScoreControllerImpl() {
        // 简单直接的依赖创建方式，后续可替换为依赖注入
        this.scoreService = new ScoreServiceImpl();
    }

    @Override
    public List<Scores> listAll() {
        return scoreService.getAllScores();
    }

    @Override
    public Scores getById(Integer scoreId) {
        if (scoreId == null) {
            throw new IllegalArgumentException("scoreId 不能为空");
        }
        return scoreService.getScoreById(scoreId);
    }

    @Override
    public List<Scores> listByStudentId(Integer studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("studentId 不能为空");
        }
        return scoreService.getScoresByStudentId(studentId);
    }

    @Override
    public List<Scores> listByCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("courseName 不能为空");
        }
        return scoreService.getScoresByCourseName(courseName.trim());
    }

    @Override
    public List<Scores> searchByConditions(String major, String className, String courseCode) {
        return scoreService.findScoresByConditions(major, className, courseCode);
    }

    @Override
    public void add(Integer studentId, String courseCode, Double score) {
        if (studentId == null) {
            throw new IllegalArgumentException("studentId 不能为空");
        }
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("courseCode 不能为空");
        }
        if (score == null) {
            throw new IllegalArgumentException("score 不能为空");
        }
        scoreService.addScore(studentId, courseCode.trim(), score);
    }

    @Override
    public void update(Scores scores) {
        if (scores == null || scores.getScoreId() == null) {
            throw new IllegalArgumentException("更新需要有效的 Scores 与 scoreId");
        }
        scoreService.updateScoreById(scores);
    }

    @Override
    public void delete(Integer scoreId) {
        if (scoreId == null) {
            throw new IllegalArgumentException("scoreId 不能为空");
        }
        scoreService.deleteScoreById(scoreId);
    }
}
