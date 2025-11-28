package com.cjlu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbcp.BasicDataSource;
//这个地方用的DBCP连接池来避免反复开关数据库
//怎么用自己去查资料吧

public class JDBCUtils {
    //数据库连接池对象
    //好吧其实是我嫌弃一直打开关闭数据库这一种操作
    private static BasicDataSource dataSource;

    //配珠置数据库连接池
    static{
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:derby:MyDB;create=true");
        //按理来说嵌入式不应该有密码
        //但为什么我设置了一个密码呢？
        //因为我疯啦！！！
        dataSource.setUsername("Admin"); 
        dataSource.setPassword("123456");

        //配置连接池参数
        //怎么依赖更新后把初始连接数量的设置去掉了？？？
        dataSource.setMaxActive(5);
        dataSource.setMaxIdle(5);
        dataSource.setMinIdle(2);
        dataSource.setMaxWait(5000);
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