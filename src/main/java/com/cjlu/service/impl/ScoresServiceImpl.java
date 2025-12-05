package com.cjlu.service.impl;

import java.util.List;

import com.cjlu.entity.Scores;
import com.cjlu.service.ScoresService;

import com.cjlu.dao.impl.ScoresDaoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScoresServiceImpl implements ScoresService {

    //日志记录器
    private static final Logger logger = LoggerFactory.getLogger(ScoresServiceImpl.class);

    //Dao层对象
    private ScoresDaoImpl scoresDao = new ScoresDaoImpl();

    @Override
    public void createScoresTable() {
        try {
            scoresDao.createScoresTable();
            logger.info("调用创建成绩表服务成功");
        } catch (Exception e) {
            logger.error("调用创建成绩表服务失败", e);
        }
    }

    @Override
    public void addScore(Scores scores) {
        try {
            //首先检查表是否存在
            scoresDao.createScoresTable();
            scoresDao.addScore(scores);
            logger.info("调用添加成绩服务成功");
        } catch (Exception e) {
            logger.error("调用添加成绩服务失败", e);
        }
    }

    @Override
    public Scores getScoreByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        try {
            return scoresDao.getScoreByStudentIdAndCourseId(studentId, courseId);
        } catch (Exception e) {
            logger.error("调用根据学生ID和课程ID查询成绩服务失败", e);
            return null;
        }
    }

    @Override
    public void updateScore(Scores scores) {
        try {
            scoresDao.updateScore(scores);
            logger.info("调用更新成绩服务成功");
        } catch (Exception e) {
            logger.error("调用更新成绩服务失败", e);
        }
    }

    @Override
    public void deleteScore(Integer id) {
        try {
            scoresDao.deleteScore(id);
            logger.info("调用删除成绩服务成功");
        } catch (Exception e) {
            logger.error("调用删除成绩服务失败", e);
        }
    }

    @Override
    public List<Scores> getScoresByStudentId(Integer studentId) {
        try {
            return scoresDao.getScoresByStudentId(studentId);
        } catch (Exception e) {
            logger.error("调用根据学生ID查询所有成绩服务失败", e);
            return null;
        }
    }

    @Override
    public List<Scores> getScoresByCourseId(Integer courseId) {
        try {
            return scoresDao.getScoresByCourseId(courseId);
        } catch (Exception e) {
            logger.error("调用根据课程ID查询所有成绩服务失败", e);
            return null;
        }
    }

    @Override
    public List<Scores> getAllScores() {
        try {
            return scoresDao.getAllScores();
        } catch (Exception e) {
            logger.error("调用查询所有成绩服务失败", e);
            return null;
        }
    }

}
