package com.cjlu.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjlu.dao.ScoreDao;
import com.cjlu.entity.Scores;

public class ScoreDaoImpl implements ScoreDao{

    //链接数据库
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    //日志记录器
    private static final Logger logger = LoggerFactory.getLogger(ScoreDaoImpl.class);

    //创建成绩表
    @Override
    public void createScoreTable() {
        //依据Scores类中的数据创建成绩表
        //获取数据库链接
        try{
            connection = com.cjlu.util.JDBCUtils.getConnection();
            //创建成绩表的SQL语句
            String createTableSQL =
                    "CREATE TABLE scores ("
                  + "score_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                  + "student_id INT NOT NULL,"
                  + "course_code VARCHAR(50) NOT NULL,"
                  + "score DOUBLE NOT NULL,"
                  + "exam_date DATE,"
                  + "FOREIGN KEY (student_id) REFERENCES Students(student_id)"
                  + ")";
            //创建预编译语句对象
            preparedStatement = connection.prepareStatement(createTableSQL);
            //执行创建表操作
            preparedStatement.executeUpdate();
            logger.info("成绩表创建成功");
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
            throw new RuntimeException("添加成绩记录失败", e);
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
    public Double getScoreRecord(int studentId, String courseCode) {
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
                double score = resultSet.getDouble("score");
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

    //确保成绩表存在且列类型正确
    public void ensureScoreTableSchema() {
        try {
            connection = com.cjlu.util.JDBCUtils.getConnection();
            var metaData = connection.getMetaData();

            // 如果表不存在则直接创建后返回
            try (ResultSet tables = metaData.getTables(null, null, "SCORES", new String[] { "TABLE" })) {
                if (!tables.next()) {
                    com.cjlu.util.JDBCUtils.closeResources(connection, null, null);
                    connection = null;
                    createScoreTable();
                    return;
                }
            }

            boolean needScoreUpgrade = false;
            boolean hasExamDate = false;
            try (ResultSet columns = metaData.getColumns(null, null, "SCORES", null)) {
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    if ("SCORE".equalsIgnoreCase(columnName)) {
                        int dataType = columns.getInt("DATA_TYPE");
                        if (dataType == Types.INTEGER || dataType == Types.SMALLINT || dataType == Types.TINYINT
                                || dataType == Types.BIGINT) {
                            needScoreUpgrade = true;
                        }
                    } else if ("EXAM_DATE".equalsIgnoreCase(columnName)) {
                        hasExamDate = true;
                    }
                }
            }

            if (needScoreUpgrade) {
                try (Statement stmt = connection.createStatement()) {
                    stmt.executeUpdate("ALTER TABLE scores ALTER COLUMN score SET DATA TYPE DOUBLE");
                    logger.info("已将成绩表 score 列调整为 DOUBLE 类型");
                } catch (SQLException ex) {
                    logger.warn("无法直接修改成绩列类型，将重建成绩表", ex);
                    rebuildScoresTable();
                    return;
                }
            }

            if (!hasExamDate) {
                try (Statement stmt = connection.createStatement()) {
                    stmt.executeUpdate("ALTER TABLE scores ADD COLUMN exam_date DATE");
                    logger.info("已为成绩表补充 exam_date 列");
                } catch (SQLException ex) {
                    logger.warn("无法为成绩表添加 exam_date 列，将重建成绩表", ex);
                    rebuildScoresTable();
                    return;
                }
            }
        } catch (Exception e) {
            logger.warn("确保成绩表结构时出错", e);
        } finally {
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, null);
        }
    }

    private void rebuildScoresTable() {
        try (Connection conn = com.cjlu.util.JDBCUtils.getConnection();
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE scores");
        } catch (SQLException dropEx) {
            logger.error("重建成绩表时删除旧表失败", dropEx);
        } catch (Exception ex) {
            logger.error("重建成绩表时出错", ex);
        }
        createScoreTable();
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
            throw new RuntimeException("查询课程成绩记录失败", e);
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
            throw new RuntimeException("获取课程成绩统计失败", e);
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

    //====== 扩展 DAO 方法，配合 Service / Controller 使用 ======

    //根据成绩ID删除
    public void deleteScoreRecordById(Integer scoreId) throws Exception {
        try {
            connection = com.cjlu.util.JDBCUtils.getConnection();
            String sql = "DELETE FROM scores WHERE score_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, scoreId);
            preparedStatement.executeUpdate();
        } finally {
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, null);
        }
    }

    //根据成绩ID更新
    public void updateScoreRecordById(Scores score) throws Exception {
        try {
            connection = com.cjlu.util.JDBCUtils.getConnection();
            String sql = "UPDATE scores SET student_id = ?, course_code = ?, score = ?, exam_date = ? WHERE score_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, score.getStudentId());
            preparedStatement.setString(2, score.getCourseCode());
            preparedStatement.setDouble(3, score.getScore());
            if (score.getExamDate() != null) {
                preparedStatement.setDate(4, new java.sql.Date(score.getExamDate().getTime()));
            } else {
                preparedStatement.setDate(4, null);
            }
            preparedStatement.setInt(5, score.getScoreId());
            preparedStatement.executeUpdate();
        } finally {
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, null);
        }
    }

    //根据成绩ID查询
    public Scores getScoreById(Integer scoreId) throws Exception {
        java.sql.ResultSet resultSet = null;
        try {
            connection = com.cjlu.util.JDBCUtils.getConnection();
            String sql = "SELECT score_id, student_id, course_code, score, exam_date FROM scores WHERE score_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, scoreId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int sid = resultSet.getInt("score_id");
                int studentId = resultSet.getInt("student_id");
                String courseCode = resultSet.getString("course_code");
                double scoreVal = resultSet.getDouble("score");
                Date examDate = resultSet.getDate("exam_date");
                return new Scores(sid, studentId, courseCode, scoreVal, examDate);
            }
            return null;
        } finally {
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);
        }
    }

    //根据学生ID查询所有成绩
    public List<Scores> getScoresByStudentId(Integer studentId) throws Exception {
        java.sql.ResultSet resultSet = null;
        try {
            connection = com.cjlu.util.JDBCUtils.getConnection();
            String sql = "SELECT score_id, student_id, course_code, score, exam_date FROM scores WHERE student_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();
            List<Scores> list = new java.util.ArrayList<>();
            while (resultSet.next()) {
                int sid = resultSet.getInt("score_id");
                int stuId = resultSet.getInt("student_id");
                String courseCode = resultSet.getString("course_code");
                double scoreVal = resultSet.getDouble("score");
                Date examDate = resultSet.getDate("exam_date");
                list.add(new Scores(sid, stuId, courseCode, scoreVal, examDate));
            }
            return list;
        } finally {
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);
        }
    }

    //根据课程代码查询成绩
    public List<Scores> getScoresByCourseCode(String courseCode) throws Exception {
        java.sql.ResultSet resultSet = null;
        try {
            connection = com.cjlu.util.JDBCUtils.getConnection();
            String sql = "SELECT score_id, student_id, course_code, score, exam_date FROM scores WHERE course_code = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, courseCode);
            resultSet = preparedStatement.executeQuery();
            List<Scores> list = new java.util.ArrayList<>();
            while (resultSet.next()) {
                int sid = resultSet.getInt("score_id");
                int stuId = resultSet.getInt("student_id");
                String code = resultSet.getString("course_code");
                double scoreVal = resultSet.getDouble("score");
                Date examDate = resultSet.getDate("exam_date");
                list.add(new Scores(sid, stuId, code, scoreVal, examDate));
            }
            return list;
        } finally {
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);
        }
    }

    //根据课程和分数范围查询
    public List<Scores> getScoresByCourseAndScoreRange(String courseCode, double minScore, double maxScore)
            throws Exception {
        java.sql.ResultSet resultSet = null;
        try {
            connection = com.cjlu.util.JDBCUtils.getConnection();
            String sql = "SELECT score_id, student_id, course_code, score, exam_date FROM scores " +
                         "WHERE course_code = ? AND score BETWEEN ? AND ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, courseCode);
            preparedStatement.setDouble(2, minScore);
            preparedStatement.setDouble(3, maxScore);
            resultSet = preparedStatement.executeQuery();
            List<Scores> list = new java.util.ArrayList<>();
            while (resultSet.next()) {
                int sid = resultSet.getInt("score_id");
                int stuId = resultSet.getInt("student_id");
                String code = resultSet.getString("course_code");
                double scoreVal = resultSet.getDouble("score");
                Date examDate = resultSet.getDate("exam_date");
                list.add(new Scores(sid, stuId, code, scoreVal, examDate));
            }
            return list;
        } finally {
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);
        }
    }

    //根据可选条件模糊查询成绩记录
    public List<Scores> findScoresByConditions(String major, String className, String courseCode) throws Exception {
        java.sql.ResultSet resultSet = null;
        try {
            connection = com.cjlu.util.JDBCUtils.getConnection();
            StringBuilder sql = new StringBuilder(
                    "SELECT s.score_id, s.student_id, s.course_code, s.score, s.exam_date " +
                    "FROM scores s LEFT JOIN students st ON s.student_id = st.student_id WHERE 1=1");

            java.util.List<String> params = new java.util.ArrayList<>();

            if (courseCode != null && !courseCode.trim().isEmpty()) {
                sql.append(" AND LOWER(s.course_code) LIKE ?");
                params.add('%' + courseCode.trim().toLowerCase() + '%');
            }
            if (major != null && !major.trim().isEmpty()) {
                sql.append(" AND LOWER(st.major) LIKE ?");
                params.add('%' + major.trim().toLowerCase() + '%');
            }
            if (className != null && !className.trim().isEmpty()) {
                sql.append(" AND LOWER(st.class) LIKE ?");
                params.add('%' + className.trim().toLowerCase() + '%');
            }

            preparedStatement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setString(i + 1, params.get(i));
            }

            resultSet = preparedStatement.executeQuery();
            java.util.List<Scores> list = new java.util.ArrayList<>();
            while (resultSet.next()) {
                int sid = resultSet.getInt("score_id");
                int stuId = resultSet.getInt("student_id");
                String code = resultSet.getString("course_code");
                double scoreVal = resultSet.getDouble("score");
                Date examDate = resultSet.getDate("exam_date");
                list.add(new Scores(sid, stuId, code, scoreVal, examDate));
            }
            return list;
        } finally {
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);
        }
    }

    public List<Scores> fetchAllScores() {
       try{
        //建立数据库链接
        connection = com.cjlu.util.JDBCUtils.getConnection();
        //创建SQL查询语句
        String querySQL = "SELECT score_id, student_id, course_code, score, exam_date FROM scores";
        //创建预编译语句对象
        preparedStatement = connection.prepareStatement(querySQL);
        //执行查询操作
        var resultSet = preparedStatement.executeQuery();
        List<Scores> scoresList = new java.util.ArrayList<>();
        while(resultSet.next()){
            int scoreId = resultSet.getInt("score_id");
            int studentId = resultSet.getInt("student_id");
            String courseCode = resultSet.getString("course_code");
            double score = resultSet.getDouble("score");
            Date examDate = resultSet.getDate("exam_date");
            Scores scores = new Scores(scoreId,studentId,courseCode,score,examDate);
            scoresList.add(scores);
        }
        logger.info("获取所有成绩记录成功。");
        return scoresList;
         } catch(Exception e){
            logger.error("获取所有成绩记录时出错：", e);
            e.printStackTrace();
            logger.error("获取所有成绩记录时出错：{}", e.getMessage());
            return null;
        }finally{
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, null);    
       }
    }

    //检查表是否存在方法
    public boolean checkTableExists(String tableName) {
        try {
            // 获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();
            // 获取数据库元数据
            var metaData = connection.getMetaData();
            // 查询表是否存在
            var resultSet = metaData.getTables(null, null, tableName, new String[] { "TABLE" });
            boolean exists = resultSet.next();
            resultSet.close();
            return exists;
        } catch (Exception e) {
            logger.error("检查表是否存在时出错：", e);
            e.printStackTrace();
            logger.error("检查表是否存在时出错：{}", e.getMessage());
            return false;
        } finally {
            // 关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, null);
        }
    }
}