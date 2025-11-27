package com.cjlu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    // Derby嵌入式数据库URL（数据库文件存储在项目根目录的db文件夹下）
    private static final String URL = "jdbc:derby:db/sims_db;create=true";
    // 数据库用户名（嵌入式Derby默认无需用户名密码，可省略）
    private static final String USER = "";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Derby驱动加载失败！");
        }
    }

    //获取数据库链接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    //关闭数据库链接
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}