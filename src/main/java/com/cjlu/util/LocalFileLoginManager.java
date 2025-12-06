package com.cjlu.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.cjlu.dao.impl.UserDaoImpl;


// Utility class that maintains the user login state via a local file
// Why use a local file?
// Because I was losing my mind!!!
// Truthfully, it keeps one-click deployment simple
public class LocalFileLoginManager {

    // Define the login file name that resides in the application root directory
    private static final String LOGIN_FILE_NAME = "user_session.dat";
    
    // Date format used to format the login timestamp
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Path loginFilePath;

    UserDaoImpl userDao = new UserDaoImpl();

    // Initialize the login file helper
    public LocalFileLoginManager() {
        // Retrieve the application root directory (current working directory)
        String appRootDir = System.getProperty("user.dir");
        // Build the full login file path (root directory + file name)
        this.loginFilePath = Path.of(appRootDir, LOGIN_FILE_NAME);
    }

    // User login method
    public boolean login(String username, String password) {
        // Basic parameter validation
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            System.err.println("Username and password must not be empty!");
            return false;
        }

        System.out.println("Validating user credentials...");

        // Ensure the user table exists to avoid exceptions on first launch
        if (!userDao.isUserTableExists()) {
            userDao.createUserTable();
        }

        // Securely retrieve user data and validate credentials
        Integer userId = userDao.getUserIdByName(username);
        String storedPassword = (userId != null) ? userDao.getUserPassword(userId) : null;
        boolean isCredentialValid = storedPassword != null && storedPassword.equals(password);

        if (isCredentialValid) {
            try {
                // Create the file directly under the application root directory
                try (BufferedWriter writer = Files.newBufferedWriter(loginFilePath, StandardCharsets.UTF_8)) {
                    writer.write(username);
                    writer.newLine(); 
                    writer.write(LocalDateTime.now().format(DATE_FORMATTER));
                }
                
                System.out.println("Login succeeded. The session file is saved at: " + loginFilePath);
                return true;
            } catch (IOException e) {
                System.err.println("Failed to create the login file: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Incorrect username or password. Login failed.");
            return false;
        }
    }

    // Check whether the user is already logged in
    public boolean isLoggedIn() {
        return Files.exists(loginFilePath);
    }

    // Retrieve the current username
    public Optional<String> getCurrentUser() {
        if (!isLoggedIn()) {
            return Optional.empty();
        }
        try {
            // Read the first line of the file (the username)
            String username = Files.readAllLines(loginFilePath, StandardCharsets.UTF_8).get(0);
            return Optional.ofNullable(username.trim());
        } catch (IOException e) {
            System.err.println("Failed to read the login file: " + e.getMessage());
            return Optional.empty();
        }
    }

    // Log out the current user
    public boolean logout() {
        if (isLoggedIn()) {
            try {
                Files.delete(loginFilePath);
                System.out.println("Logout succeeded. The session file has been removed: " + loginFilePath);
                return true;
            } catch (IOException e) {
                System.err.println("Failed to delete the login file: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("No user is currently logged in, so logout is not required.");
            // Treat the not-logged-in state as a successful logout
            return true;
        }
    }

    // Retrieve the login file path (useful for debugging)
    public String getLoginFilePath() {
        return loginFilePath.toString();
    }
}