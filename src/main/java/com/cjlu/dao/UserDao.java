package com.cjlu.dao;

import java.util.List;
import java.util.Map;

public interface UserDao {
    //根据用户ID获取用户信息
    public Map<String,Object> getUserInfo(String userIdString);

    //根据用户名获取用户ID
    public Integer getUserId(String userName);

    //根据ID获取用户名
    public String getUserName(Integer userId);

    //根据ID获取用户密码
    public String getUserPassword(Integer userId);

    //根据ID获取用户身份
    public String getUserRole(Integer userId);

    //根据ID获取账户创建时间
    public String getUserCreateTime(Integer userId);

    //添加新用户
    public void addUser(String userName,String password,String role);

    //删除用户
    public void deleteUser(Integer userId);

    //更新用户信息
    public void updateUser(Integer userId,String userName,String password,String role);

    //获取所有用户信息
    public List<Map<String,Object>> getAllUsers();

    //验证用户登录
    public boolean validateLogin(String userName,String password);

    //检查用户名是否存在
    public boolean isUserNameExists(String userName);

    //根据用户名获取用户角色
    public String getUserRoleByName(String userName);

    //根据用户名获取用户ID
    public Integer getUserIdByName(String userName);

    //创建用户表
    public void createUserTable();

}
