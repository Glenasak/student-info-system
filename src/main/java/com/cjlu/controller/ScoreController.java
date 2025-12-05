package com.cjlu.controller;

import com.cjlu.entity.Scores;

import java.util.List;

public interface ScoreController {
    List<Scores> listAll(); // 获取所有成绩记录

    List<Scores> listByStudentId(Integer studentId); // 根据学生ID获取成绩记录

    List<Scores> listByCourseId(Integer courseId); // 根据课程ID获取成绩记录

    Scores getByStudentIdAndCourseId(Integer studentId, Integer courseId); // 根据学生ID和课程ID获取单条成绩记录

    void add(Scores scores); // 添加新的成绩记录（使用实体）

    void update(Scores scores); // 更新已有的成绩记录（使用实体）

    void delete(Integer id); // 删除成绩记录（按主键ID）

}
