package com.cjlu.service;

public interface UserService {
    //用户注册方法
    void registerUser(String username, String password, String role);

    //用户登录方法
    boolean loginUser(String username, String password);

    //用户信息更新方法
    void updateUserInfo(int userId, String newEmail, String newPassword);

    //用户删除方法
    void deleteUser(int userId);

    //获取用户信息方法
    String getUserInfo(int userId);

    //列出所有用户方法
    String listAllUsers();

    //重置用户密码方法
    void resetUserPassword(int userId, String newPassword);

    //验证用户邮箱方法
    boolean verifyUserEmail(int userId, String email);

    //用户登出方法
    void logoutUser(int userId);

    //检查用户名是否存在方法
    boolean isUsernameExists(String username);
}
