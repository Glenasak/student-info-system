package com.cjlu.service.impl;

import java.util.List;

import com.cjlu.entity.Scores;
import com.cjlu.service.ScoreService;
import org.slf4j.*;
import com.cjlu.dao.impl.ScoreDaoImpl;

public class ScoreServiceImpl implements ScoreService {

    //日志记录器
    private static final Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);

    //数据访问对象
    private final ScoreDaoImpl scoreDao;

    public ScoreServiceImpl() {
        this.scoreDao = new ScoreDaoImpl();
        this.scoreDao.ensureScoreTableSchema();
    }

    //添加成绩
    @Override
    public void addScore(int studentId, String courseCode, double score) {
        try {
            scoreDao.addScoreRecord(studentId, courseCode, score);
            logger.info("添加成绩成功: studentId={}, courseCode={}, score={}", studentId, courseCode, score);
        } catch (Exception e) {
            logger.error("添加成绩失败: studentId={}, courseCode={}, score={}", studentId, courseCode, score, e);
            throw new RuntimeException(e);
        }
    }

    //根据成绩ID删除成绩
    @Override
    public void deleteScoreById(Integer scoreId) {
        try {
            scoreDao.deleteScoreRecordById(scoreId);
            logger.info("删除成绩成功: scoreId={}", scoreId);
        } catch (Exception e) {
            logger.error("删除成绩失败: scoreId={}", scoreId, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateScoreById(Scores score) {
        try {
            scoreDao.updateScoreRecordById(score);
            logger.info("更新成绩成功: scoreId={}", score.getScoreId());
        } catch (Exception e) {
            logger.error("更新成绩失败: scoreId={}", score.getScoreId(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Scores getScoreById(Integer scoreId) {
        try {
            return scoreDao.getScoreById(scoreId);
        } catch (Exception e) {
            logger.error("根据ID获取成绩失败: scoreId={}", scoreId, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Scores> getAllScores() {
        try{
            return scoreDao.fetchAllScores();
        } catch (Exception e) {
            logger.error("获取所有成绩失败", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Scores> getScoresByStudentId(Integer studentId) {
        try {
            return scoreDao.getScoresByStudentId(studentId);
        } catch (Exception e) {
            logger.error("根据学生ID获取成绩失败: studentId={}", studentId, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Scores> getScoresByCourseName(String courseName) {
        try {
            return scoreDao.getScoresByCourseCode(courseName);
        } catch (Exception e) {
            logger.error("根据课程代码获取成绩失败: courseCode={}", courseName, e);
            throw new RuntimeException(e);
        }
    }

    //依据课程名称和分数范围查询成绩
    @Override
    public List<Scores> getScoresByCourseAndScoreRange(String courseName, double minScore, double maxScore) {
        try {
            return scoreDao.getScoresByCourseAndScoreRange(courseName, minScore, maxScore);
        } catch (Exception e) {
            logger.error("根据课程和分数范围获取成绩失败: courseCode={}", courseName, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Scores> findScoresByConditions(String major, String className, String courseCode) {
        try {
            return scoreDao.findScoresByConditions(major, className, courseCode);
        } catch (Exception e) {
            logger.error("根据条件获取成绩失败: major={}, className={}, courseCode={}", major, className, courseCode, e);
            throw new RuntimeException(e);
        }
    }

}
