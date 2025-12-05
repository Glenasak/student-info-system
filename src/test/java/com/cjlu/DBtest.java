package com.cjlu;
import java.sql.Connection;

import com.cjlu.util.JDBCUtils;

public class DBtest {

    public static void main(String[] args) {
    try {
        // 打印运行时 classpath，帮助诊断是否包含 derby.jar
        System.out.println("runtime classpath: " + System.getProperty("java.class.path"));
        // 手动加载 Derby 驱动类，验证是否存在
        String resourcePath = "org/apache/derby/jdbc/EmbeddedDriver.class";
        System.out.println("System classloader: " + ClassLoader.getSystemClassLoader().getClass());
        System.out.println("Context classloader: " + Thread.currentThread().getContextClassLoader().getClass());
        System.out.println("SystemResource for driver: " + ClassLoader.getSystemResource(resourcePath));
        System.out.println("ContextResource for driver: " + Thread.currentThread().getContextClassLoader().getResource(resourcePath));

        // 尝试通过 Class.forName 加载（先试常见的 EmbeddedDriver，再试 jar 中实际注册的 AutoloadedDriver）
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println("Loaded org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e1) {
            try {
                Class.forName("org.apache.derby.iapi.jdbc.AutoloadedDriver");
                System.out.println("Loaded org.apache.derby.iapi.jdbc.AutoloadedDriver");
            } catch (ClassNotFoundException e2) {
                System.err.println("既找不到 EmbeddedDriver，也找不到 AutoloadedDriver");
                throw e2;
            }
        }
        
        // 再调用连接池获取连接
        Connection conn = JDBCUtils.getConnection();
        System.out.println("数据库连接获取成功：" + conn);
        System.out.println(System.getProperty("java.class.path"));
    } catch (ClassNotFoundException e) {
        System.err.println("驱动类找不到！请检查 derby.jar 是否引入");
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
