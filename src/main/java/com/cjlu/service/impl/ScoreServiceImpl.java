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
    private ScoreDaoImpl scoreDao = new ScoreDaoImpl();

    //添加成绩
    @Override
    public void addScore(int studentId, String courseCode, double score) {
        try {
            scoreDao.addScoreRecord(studentId, courseCode, score);
            logger.info("添加成绩成功: studentId={}, courseCode={}, score={}", studentId, courseCode, score);
        } catch (Exception e) {
            logger.error("添加成绩失败: studentId={}, courseCode={}, score={}", studentId, courseCode, score, e);
            throw e;
        }
    }

    //根据成绩ID删除成绩
    @Override
    public void deleteScoreById(Integer scoreId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteScoreById'");
    }

    @Override
    public void updateScoreById(Scores score) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateScoreById'");
    }

    @Override
    public Scores getScoreById(Integer scoreId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScoreById'");
    }

    @Override
    public List<Scores> getAllScores() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllScores'");
    }

    @Override
    public List<Scores> getScoresByStudentId(Integer studentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScoresByStudentId'");
    }

    @Override
    public List<Scores> getScoresByCourseName(String courseName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScoresByCourseName'");
    }

    //依据课程名称和分数范围查询成绩
    @Override
    public List<Scores> getScoresByCourseAndScoreRange(String courseName, double minScore, double maxScore) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScoresByCourseAndScoreRange'");
    }

}
