package com.cjlu.controller;

import java.util.List;

import com.cjlu.entity.Scores;
import com.cjlu.service.ScoreService;
import com.cjlu.service.impl.ScoreServiceImpl;

/**
 * 控制层：封装 UI 与服务层交互，做基本校验与错误包装。
 */
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController() {
        // 也可通过依赖注入或工厂获取，这里直接 new 简化
        this.scoreService = new ScoreServiceImpl();
    }

    public List<Scores> listAll() {
        return scoreService.getAllScores();
    }

    public Scores getById(Integer scoreId) {
        if (scoreId == null) throw new IllegalArgumentException("scoreId 不能为空");
        return scoreService.getScoreById(scoreId);
    }

    public List<Scores> listByStudentId(Integer studentId) {
        if (studentId == null) throw new IllegalArgumentException("studentId 不能为空");
        return scoreService.getScoresByStudentId(studentId);
    }

    public List<Scores> listByCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("courseName 不能为空");
        }
        return scoreService.getScoresByCourseName(courseName.trim());
    }

    public void add(Integer studentId, String courseCode, Double score) {
        if (studentId == null) throw new IllegalArgumentException("studentId 不能为空");
        if (courseCode == null || courseCode.trim().isEmpty()) throw new IllegalArgumentException("courseCode 不能为空");
        if (score == null) throw new IllegalArgumentException("score 不能为空");
        scoreService.addScore(studentId, courseCode.trim(), score);
    }

    public void update(Scores scores) {
        if (scores == null || scores.getScoreId() == null) {
            throw new IllegalArgumentException("更新需要有效的 Scores 与 scoreId");
        }
        scoreService.updateScoreById(scores);
    }

    public void delete(Integer scoreId) {
        if (scoreId == null) throw new IllegalArgumentException("scoreId 不能为空");
        scoreService.deleteScoreById(scoreId);
    }
}
