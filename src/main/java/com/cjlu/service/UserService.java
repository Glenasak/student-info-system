package com.cjlu.service;

public interface UserService {
    // Register a new user
    void registerUser(String username, String password, String role) throws Exception;

    // Log a user in
    boolean loginUser(String username, String password);

    // Update user information
    void updateUserInfo(int userId, String newEmail, String newPassword);

    // Delete a user
    void deleteUser(int userId);

    // Retrieve user details
    String getUserInfo(int userId);

    // List all users
    String listAllUsers();

    // Reset a user password
    void resetUserPassword(int userId, String newPassword);

    // Verify a user email address
    boolean verifyUserEmail(int userId, String email);

    // Log a user out
    void logoutUser(int userId);

    // Check whether a username already exists
    boolean isUsernameExists(String username);

    // Validate login credentials
    boolean validateLogin(String userName, String password);

    // Create the user table when needed
    void createUserTable();
}
