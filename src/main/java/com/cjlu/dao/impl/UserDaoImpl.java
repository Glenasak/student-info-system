package com.cjlu.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import org.slf4j.*;;


public class UserDaoImpl implements com.cjlu.dao.UserDao {

    //链接数据库
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    //通过用户ID获取用户信息
    @Override
    public Map<String,Object> getUserInfo(String userIdString) {
        try {
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "SELECT * FROM users WHERE user_id = ?";

            //执行查询
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, userIdString);
            ResultSet resultSet = preparedStatement.executeQuery();
            //将结果集放入Map中返回
            Map<String,Object> userInfo = null;

            if(resultSet.next()) {
                userInfo = new java.util.HashMap<>();
                userInfo.put("user_id", resultSet.getInt("user_id"));
                userInfo.put("user_name", resultSet.getString("user_name"));
                userInfo.put("password", resultSet.getString("password"));
                userInfo.put("role", resultSet.getString("role"));
                userInfo.put("create_time", resultSet.getTimestamp("create_time"));
            }

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);

            return userInfo;

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error retrieving user info for userId: " + userIdString, e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
            return null;
        }
    }

    //通过用户名获取用户ID
    @Override
    public Integer getUserId(String userName) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "SELECT user_id FROM users WHERE user_name = ?";

            //执行查询
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            Integer userId = null;
            if(resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);

            return userId;

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error retrieving user ID for userName: " + userName, e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
            return null;
        }
    }

    //通过用户ID获取用户名
    @Override
    public String getUserName(Integer userId) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "SELECT user_name FROM users WHERE user_id = ?";

            //执行查询
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            String userName = null;
            if(resultSet.next()) {
                userName = resultSet.getString("user_name");
            }

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);

            return userName;

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error retrieving user name for userId: " + userId, e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
            return null;
        }
    }

    //通过用户ID获取用户密码
    @Override
    public String getUserPassword(Integer userId) {
       try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "SELECT password FROM users WHERE user_id = ?";

            //执行查询
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            String password = null;
            if(resultSet.next()) {
                password = resultSet.getString("password");
            }

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);

            return password;

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error retrieving password for userId: " + userId, e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
            return null;
        }
    }

    //通过用户ID获取用户创建时间
    @Override
    public String getUserRole(Integer userId) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "SELECT role FROM users WHERE user_id = ?";

            //执行查询
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            String role = null;
            if(resultSet.next()) {
                role = resultSet.getString("role");
            }

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);

            return role;

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error retrieving role for userId: " + userId, e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
            return null;
        }
    }

    //通过用户ID获取用户创建时间
    @Override
    public String getUserCreateTime(Integer userId) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "SELECT create_time FROM users WHERE user_id = ?";

            //执行查询
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            String createTime = null;
            if(resultSet.next()) {
                createTime = resultSet.getString("create_time");
            }

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);

            return createTime;

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error retrieving create time for userId: " + userId, e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
            return null;
        }
    }

    //添加新用户
    @Override
    public void addUser(String userName, String password, String role) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "INSERT INTO users (user_name, password, role, create_time) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";

            //执行插入
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error adding new user: " + userName, e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
        }
    }

    //删除用户
    @Override
    public void deleteUser(Integer userId) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "DELETE FROM users WHERE user_id = ?";

            //执行删除
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error deleting user with userId: " + userId, e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
        }
    }

    //更新用户信息
    @Override
    public void updateUser(Integer userId, String userName, String password, String role) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "UPDATE users SET user_name = ?, password = ?, role = ? WHERE user_id = ?";

            //执行更新
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.setInt(4, userId);
            preparedStatement.executeUpdate();

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error updating user with userId: " + userId, e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
        }
    }

    //获取所有用户信息
    @Override
    public List<Map<String,Object>> getAllUsers() {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "SELECT * FROM users";

            //执行查询
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Map<String,Object>> usersList = new java.util.ArrayList<>();

            while(resultSet.next()) {
                Map<String,Object> userInfo = new java.util.HashMap<>();
                userInfo.put("user_id", resultSet.getInt("user_id"));
                userInfo.put("user_name", resultSet.getString("user_name"));
                userInfo.put("password", resultSet.getString("password"));
                userInfo.put("role", resultSet.getString("role"));
                userInfo.put("create_time", resultSet.getTimestamp("create_time"));
                usersList.add(userInfo);
            }

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);

            return usersList;

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error retrieving all users", e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
            return null;
        }
    }

    //验证用户登录
    @Override
    public boolean validateLogin(String userName, String password) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "SELECT * FROM users WHERE user_name = ? AND password = ?";

            //执行查询
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean isValid = resultSet.next();

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);

            return isValid;

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error validating login for userName: " + userName, e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
            return false;
        }
    }

    //检查用户名是否存在
    @Override
    public boolean isUserNameExists(String userName) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "SELECT * FROM users WHERE user_name = ?";

            //执行查询
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean exists = resultSet.next();

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);

            return exists;

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error checking existence of userName: " + userName, e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
            return false;
        }
    }

    //根据用户名获取用户角色
    @Override
    public String getUserRoleByName(String userName) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "SELECT role FROM users WHERE user_name = ?";

            //执行查询
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            String role = null;
            if(resultSet.next()) {
                role = resultSet.getString("role");
            }

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);

            return role;

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error retrieving role for userName: " + userName, e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
            return null;
        }
    }

    //根据用户名获取用户ID
    @Override
    public Integer getUserIdByName(String userName) {
        try{
            //获取数据库连接
            connection = com.cjlu.util.JDBCUtils.getConnection();

            //准备SQL语句
            String sql = "SELECT user_id FROM users WHERE user_name = ?";

            //执行查询
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();

            Integer userId = null;
            if(resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }

            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement, resultSet);

            return userId;

        } catch (Exception e) {
            e.printStackTrace();
            //日志
            Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
            logger.error("Error retrieving user ID for userName: " + userName, e);
            //关闭资源
            com.cjlu.util.JDBCUtils.closeResources(connection, preparedStatement);
            return null;
        }
    }

}
