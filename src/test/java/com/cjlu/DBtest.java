package com.cjlu;
import java.sql.Connection;

import com.cjlu.util.JDBCUtils;

public class DBtest {

    public static void main(String[] args) {
    try {
        // Print the runtime classpath to help diagnose whether derby.jar is included
        System.out.println("runtime classpath: " + System.getProperty("java.class.path"));
        // Manually load the Derby driver class to verify its presence
        String resourcePath = "org/apache/derby/jdbc/EmbeddedDriver.class";
        System.out.println("System classloader: " + ClassLoader.getSystemClassLoader().getClass());
        System.out.println("Context classloader: " + Thread.currentThread().getContextClassLoader().getClass());
        System.out.println("SystemResource for driver: " + ClassLoader.getSystemResource(resourcePath));
        System.out.println("ContextResource for driver: " + Thread.currentThread().getContextClassLoader().getResource(resourcePath));

        // Attempt to load via Class.forName (try EmbeddedDriver first, then AutoloadedDriver registered in the JAR)
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println("Loaded org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e1) {
            try {
                Class.forName("org.apache.derby.iapi.jdbc.AutoloadedDriver");
                System.out.println("Loaded org.apache.derby.iapi.jdbc.AutoloadedDriver");
            } catch (ClassNotFoundException e2) {
                System.err.println("Neither EmbeddedDriver nor AutoloadedDriver could be found");
                throw e2;
            }
        }
        
        // Obtain a connection from the pool afterward
        Connection conn = JDBCUtils.getConnection();
        System.out.println("Successfully obtained a database connection: " + conn);
        System.out.println(System.getProperty("java.class.path"));
    } catch (ClassNotFoundException e) {
        System.err.println("Driver class not found. Please check whether derby.jar is included");
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
