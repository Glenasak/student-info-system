package com.cjlu.controller.Impl;

import java.util.List;

import com.cjlu.entity.Scores;
import com.cjlu.controller.ScoreController;
import com.cjlu.service.ScoreService;
import com.cjlu.service.impl.ScoreServiceImpl;

/**
 * 控制层：封装 UI 与服务层交互，做基本校验与错误包装。
 */
public class ScoreControllerImpl implements ScoreController {

    private final ScoreService scoreService;

    public ScoreControllerImpl() {
        // 也可通过依赖注入或工厂获取，这里直接 new 简化
        this.scoreService = new ScoreServiceImpl();
    }

    @Override
    public List<Scores> listAll() {
        return scoreService.getAllScores();
    }

    @Override
    public Scores getByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        if (studentId == null) throw new IllegalArgumentException("studentId 不能为空");
        if (courseId == null) throw new IllegalArgumentException("courseId 不能为空");
        return scoreService.getScoreByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public List<Scores> listByStudentId(Integer studentId) {
        if (studentId == null) throw new IllegalArgumentException("studentId 不能为空");
        return scoreService.getScoresByStudentId(studentId);
    }

    @Override
    public List<Scores> listByCourseId(Integer courseId) {
        if (courseId == null) throw new IllegalArgumentException("courseId 不能为空");
        return scoreService.getScoresByCourseId(courseId);
    }

    // 兼容旧调用：根据课程名称查询（若无名称到ID映射，此处退化为返回全部或抛出异常）
    public List<Scores> listByCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("courseName 不能为空");
        }
        // 项目当前服务层无课程名称查询接口；为了兼容编译与运行，先返回全部成绩。
        // 如需准确实现，请在服务层增加名称->ID映射，并调用 getScoresByCourseId。
        return scoreService.getAllScores();
    }

    @Override
    public void add(Scores scores) {
        if (scores == null) throw new IllegalArgumentException("scores 不能为空");
        if (scores.getStudentId() == null) throw new IllegalArgumentException("studentId 不能为空");
        if (scores.getCourseId() == null) throw new IllegalArgumentException("courseId 不能为空");
        if (scores.getScore() == null) throw new IllegalArgumentException("score 不能为空");
        scoreService.addScore(scores);
    }

    // 兼容旧调用：使用 studentId + courseCode + score 形式
    public void add(Integer studentId, String courseCode, Double score) {
        if (studentId == null) throw new IllegalArgumentException("studentId 不能为空");
        if (courseCode == null || courseCode.trim().isEmpty()) throw new IllegalArgumentException("courseCode 不能为空");
        if (score == null) throw new IllegalArgumentException("score 不能为空");
        Integer courseId;
        try {
            courseId = Integer.valueOf(courseCode.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("courseCode 必须为可解析的课程ID", e);
        }
        Scores s = new Scores();
        s.setStudentId(studentId);
        s.setCourseId(courseId);
        s.setScore(score);
        scoreService.addScore(s);
    }

    @Override
    public void update(Scores scores) {
        if (scores == null || scores.getId() == null) {
            throw new IllegalArgumentException("更新需要有效的 Scores 与主键ID");
        }
        scoreService.updateScore(scores);
    }

    @Override
    public void delete(Integer id) {
        if (id == null) throw new IllegalArgumentException("id 不能为空");
        scoreService.deleteScore(id);
    }
}
