package com.cjlu.controller;

public interface UserController {
    //用户登录
    public boolean login(String userName,String password);
    //用户注册
    public void register(String userName,String password,String role);
    //获取用户信息
    public String getUserInfo(String userIdString);
    //更新用户信息
    public void updateUserInfo(Integer userId,String userName,String password,String role);
    //删除用户
    public void deleteUser(Integer userId);
    //获取所有用户信息
    public String getAllUsers();
    //检查用户名是否存在
    public boolean isUserNameExists(String userName);
    //根据用户名获取用户角色
    public String getUserRoleByName(String userName);
    //根据用户名获取用户ID
    public Integer getUserIdByName(String userName);
    //创建用户表
    public void createUserTable();
    
}
