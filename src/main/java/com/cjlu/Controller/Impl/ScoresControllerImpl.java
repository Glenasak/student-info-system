package com.cjlu.Controller.Impl;

import java.util.List;

import org.slf4j.Logger;

import com.cjlu.Controller.ScoresController;
import com.cjlu.entity.Scores;
import com.cjlu.service.impl.ScoresServiceImpl;

public class ScoresControllerImpl implements ScoresController {

    //日志记录器
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ScoresControllerImpl.class);

    //服务层对象
    private ScoresServiceImpl scoresService = new ScoresServiceImpl();

    //添加成绩方法
    @Override
    public boolean addScore(Scores scores) {
        try {
            scoresService.addScore(scores);
            logger.info("调用添加成绩服务成功");
            return true;
        } catch (Exception e) {
            logger.error("调用添加成绩服务失败", e);
            return false;
        }
    }

    //根据学生ID和课程ID查询成绩方法
    @Override
    public Scores getScoreByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        try {
            return scoresService.getScoreByStudentIdAndCourseId(studentId, courseId);
        } catch (Exception e) {
            logger.error("调用根据学生ID和课程ID查询成绩服务失败", e);
            return null;
        }
    }

    //更新成绩方法
    @Override
    public boolean updateScore(Scores scores) {
        try {
            scoresService.updateScore(scores);
            logger.info("调用更新成绩服务成功");
            return true;
        } catch (Exception e) {
            logger.error("调用更新成绩服务失败", e);
            return false;
        }
    }

    //删除成绩方法
    @Override
    public boolean deleteScore(Integer id) {
        try {
            scoresService.deleteScore(id);
            logger.info("调用删除成绩服务成功");
            return true;
        } catch (Exception e) {
            logger.error("调用删除成绩服务失败", e);
            return false;
        }
    }

    //根据学生ID查询所有成绩方法
    @Override
    public List<Scores> getScoresByStudentId(Integer studentId) {
       try {
           return scoresService.getScoresByStudentId(studentId);
       } catch (Exception e) {
           logger.error("调用根据学生ID查询所有成绩服务失败", e);
           return null;
       }
    }

    //根据课程ID查询所有成绩方法
    @Override
    public List<Scores> getScoresByCourseId(Integer courseId) {
        try {
            return scoresService.getScoresByCourseId(courseId);
        } catch (Exception e) {
            logger.error("调用根据课程ID查询所有成绩服务失败", e);
            return null;
        }
    }

    //查询所有成绩方法
    @Override
    public List<Scores> getAllScores() {
        try {
            return scoresService.getAllScores();
        } catch (Exception e) {
            logger.error("调用查询所有成绩服务失败", e);
            return null;
        }
    }

}
