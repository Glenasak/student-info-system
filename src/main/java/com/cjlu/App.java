package com.cjlu;

import com.cjlu.ui.login;
import com.cjlu.util.JDBCUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JOptionPane;
import java.sql.Connection;


public final class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    private App() {
    }

    public static void main(String[] args) {
        try {
            ensureDependenciesAvailable();
            java.awt.EventQueue.invokeLater(() -> new login().setVisible(true));
        } catch (Exception ex) {
            log.error("Startup failure", ex);
            JOptionPane.showMessageDialog(
                    null,
                    "Application failed to start: " + ex.getMessage(),
                    "Startup Error",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }
    }

    
    private static void ensureDependenciesAvailable() throws Exception {
        // Force-load essential classes so missing libraries are reported immediately.
        Class.forName("org.apache.derby.iapi.jdbc.AutoloadedDriver");
        Class.forName("org.apache.commons.dbcp2.BasicDataSource");

        try (Connection connection = JDBCUtils.getConnection()) {
            log.info("Verified database connectivity with URL: {}", connection.getMetaData().getURL());
        }
    }
}
