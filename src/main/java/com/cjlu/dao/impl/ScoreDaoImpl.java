package com.cjlu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjlu.dao.ScoreDao;

public class ScoreDaoImpl implements ScoreDao{

    //链接数据库
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    //日志记录器
    private static final Logger logger = LoggerFactory.getLogger(ScoreDaoImpl.class);

    //创建成绩表
    @Override
    public void createScoreTable() {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //创建成绩表的SQL语句
            String createTableSQL = "CREATE TABLE IF NOT EXISTS scores (" +
                                    "student_id INT NOT NULL," +
                                    "course_code VARCHAR(20) NOT NULL," +
                                    "score DOUBLE," +
                                    "PRIMARY KEY (student_id, course_code)" +
                                    ")";

            //创建预编译语句对象
            preparedStatement = connection.prepareStatement(createTableSQL);

            //执行创建表操作
            preparedStatement.executeUpdate();
            logger.info("成绩表创建成功或已存在。");

        }catch(Exception e){
            logger.error("创建成绩表时出错：", e);
            e.printStackTrace();
            logger.error("创建成绩表时出错：{}", e.getMessage());

        }finally{
            //关闭资源 
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, null);
        }

    }

    //添加成绩记录
    @Override
    public void addScoreRecord(int studentId, String courseCode, double score) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //添加成绩记录的SQL语句
            String insertSQL = "INSERT INTO scores (student_id, course_code, score) VALUES (?, ?, ?)";

            //创建预编译语句对象
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setString(2, courseCode);
            preparedStatement.setDouble(3, score);

            //执行插入操作
            preparedStatement.executeUpdate();
            logger.info("成绩记录添加成功：学生ID={}, 课程代码={}, 成绩={}", studentId, courseCode, score);
        }catch(Exception e){
            logger.error("添加成绩记录时出错：", e);
            e.printStackTrace();
            logger.error("添加成绩记录时出错：{}", e.getMessage());
        }finally{
            //关闭资源 
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, null);
        }
    }

    //删除成绩记录
    @Override
    public void deleteScoreRecord(int studentId, String courseCode) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //删除成绩记录的SQL语句
            String deleteSQL = "DELETE FROM scores WHERE student_id = ? AND course_code = ?";

            //创建预编译语句对象
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setString(2, courseCode);

            //执行删除操作
            preparedStatement.executeUpdate();
            logger.info("成绩记录删除成功：学生ID={}, 课程代码={}", studentId, courseCode);
        }catch(Exception e){
            logger.error("删除成绩记录时出错：", e);
            e.printStackTrace();
            logger.error("删除成绩记录时出错：{}", e.getMessage());
        }finally{
            //关闭资源 
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, null);
        }
    }

    //更新成绩记录
    @Override
    public void updateScoreRecord(int studentId, String courseCode, double newScore) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //更新成绩记录的SQL语句
            String updateSQL = "UPDATE scores SET score = ? WHERE student_id = ? AND course_code = ?";

            //创建预编译语句对象
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setDouble(1, newScore);
            preparedStatement.setInt(2, studentId);
            preparedStatement.setString(3, courseCode);

            //执行更新操作
            preparedStatement.executeUpdate();
            logger.info("成绩记录更新成功：学生ID={}, 课程代码={}, 新成绩={}", studentId, courseCode, newScore);
        }catch(Exception e){
            logger.error("更新成绩记录时出错：", e);
            e.printStackTrace();
            logger.error("更新成绩记录时出错：{}", e.getMessage());
        }finally{
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, null);
        }
    }

    //根据学生ID和课程代码获取成绩记录
    @Override
    public Integer getScoreRecord(int studentId, String courseCode) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //查询成绩记录的SQL语句
            String querySQL = "SELECT score FROM scores WHERE student_id = ? AND course_code = ?";

            //创建预编译语句对象
            preparedStatement = connection.prepareStatement(querySQL);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setString(2, courseCode);

            //执行查询操作
            var resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int score = resultSet.getInt("score");
                logger.info("获取成绩记录成功：学生ID={}, 课程代码={}, 成绩={}", studentId, courseCode, score);
                return score;
            }else{
                logger.info("未找到成绩记录：学生ID={}, 课程代码={}", studentId, courseCode);
                return null;
            }
        }catch(Exception e){
            logger.error("获取成绩记录时出错：", e);
            e.printStackTrace();
            logger.error("获取成绩记录时出错：{}", e.getMessage());
            return null;
        }finally{
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, null);
        }   
    }

    //查询某学生的所有成绩记录
    @Override
    public Map<String, Double> getAllScoresByStudentId(int studentId) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //查询某学生所有成绩记录的SQL语句
            String querySQL = "SELECT course_code, score FROM scores WHERE student_id = ?";

            //创建预编译语句对象
            preparedStatement = connection.prepareStatement(querySQL);
            preparedStatement.setInt(1, studentId);

            //执行查询操作
            var resultSet = preparedStatement.executeQuery();
            Map<String, Double> scoresMap = new java.util.HashMap<>();
            while(resultSet.next()){
                String courseCode = resultSet.getString("course_code");
                double score = resultSet.getDouble("score");
                scoresMap.put(courseCode, score);
            }
            logger.info("获取学生ID={}的所有成绩记录成功。", studentId);
            return scoresMap;
        }catch(Exception e){
            logger.error("获取学生所有成绩记录时出错：", e);
            e.printStackTrace();
            logger.error("获取学生所有成绩记录时出错：{}", e.getMessage());
            return null;
        }finally{
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, null);
        }
    }

    //查询某课程的所有学生成绩记录
    @Override
    public Map<Integer, Double> getAllScoresByCourseCode(String courseCode) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //查询某课程所有学生成绩记录的SQL语句
            String querySQL = "SELECT student_id, score FROM scores WHERE course_code = ?";

            //创建预编译语句对象
            preparedStatement = connection.prepareStatement(querySQL);
            preparedStatement.setString(1, courseCode);

            //执行查询操作
            var resultSet = preparedStatement.executeQuery();
            Map<Integer, Double> scoresMap = new java.util.HashMap<>();
            while(resultSet.next()){
                int studentId = resultSet.getInt("student_id");
                double score = resultSet.getDouble("score");
                scoresMap.put(studentId, score);
            }
            logger.info("获取课程代码={}的所有学生成绩记录成功。", courseCode);
            return scoresMap;
        }catch(Exception e){
            logger.error("获取课程所有学生成绩记录时出错：", e);
            e.printStackTrace();
            logger.error("获取课程所有学生成绩记录时出错：{}", e.getMessage());
            return null;
        }finally{
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, null);
        }
    }

    //依据课程名称和最高分和最低分和平均分模糊查询
    @Override
    public Map<String, Double> getScoreStatisticsByCourse(String courseCode) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();
            //查询某课程成绩统计的SQL语句
            String querySQL = "SELECT MAX(score) AS max_score, MIN(score) AS min_score, AVG(score) AS avg_score " +
                              "FROM scores WHERE course_code = ?";
            //创建预编译语句对象
            preparedStatement = connection.prepareStatement(querySQL);
            preparedStatement.setString(1, courseCode);
            //执行查询操作
            var resultSet = preparedStatement.executeQuery();
            Map<String, Double> statisticsMap = new java.util.HashMap<>();
            if(resultSet.next()){
                double maxScore = resultSet.getDouble("max_score");
                double minScore = resultSet.getDouble("min_score");
                double avgScore = resultSet.getDouble("avg_score");
                statisticsMap.put("max_score", maxScore);
                statisticsMap.put("min_score", minScore);
                statisticsMap.put("avg_score", avgScore);
                return statisticsMap;
            }else{
                logger.info("未找到课程代码={}的成绩记录。", courseCode);
                return null;
            }
        }catch(Exception e){
            logger.error("获取课程成绩统计时出错：", e);
            e.printStackTrace();
            logger.error("获取课程成绩统计时出错：{}", e.getMessage());
            return null;
        }finally{
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, null);
        }
    }

    @Override
    public Map<String, Double> getScoreStatisticsByCourse(String courseCode, Integer minScore, Integer maxScore,
            Double avgScore) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getScoreStatisticsByCourse'");
    }
}