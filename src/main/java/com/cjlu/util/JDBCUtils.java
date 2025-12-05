package com.cjlu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbcp2.BasicDataSource;

public class JDBCUtils {
    //数据库连接池对象
    //好吧其实是我嫌弃一直打开关闭数据库这一种操作
    private static BasicDataSource dataSource;

    //配珠置数据库连接池
    static{
    dataSource = new BasicDataSource();
        // 1. 补充驱动类名（核心修复）
        // 新版 Derby 的 jar 中通过 ServiceLoader 注册驱动，实际实现类为 AutoloadedDriver
        dataSource.setDriverClassName("org.apache.derby.iapi.jdbc.AutoloadedDriver");
    // 2. 原有 URL/用户名/密码配置
    dataSource.setUrl("jdbc:derby:MyDB;create=true");
    dataSource.setUsername("Admin"); 
    dataSource.setPassword("123456");
    // 3. 连接池参数（注意：DBCP 1.x 中 maxActive 对应最大连接数，无需修改）
    dataSource.setMaxIdle(5);
    dataSource.setMaxIdle(5);
    dataSource.setMinIdle(2);
    dataSource.setMaxWaitMillis(5000);
    }

    //获取数据库连接的方法
    public static Connection getConnection() throws Exception{
        return dataSource.getConnection();
    }

    //将资源归还到连接池
    public static void closeResources(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        //关闭结果集
        try{
            if(resultSet != null){
                resultSet.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //关闭预编译语句对象
        try{
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //关闭连接对象
        try{
            if(connection != null){
                connection.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //在没有结果集的时候的关闭语句
    public static void closeResources(Connection conn, PreparedStatement pstmt) {
        closeResources(conn, pstmt, null);
    }
}