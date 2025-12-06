package com.cjlu.controller.Impl;

import com.cjlu.controller.UserController;
import com.cjlu.service.impl.UserServiceImpl;
import org.slf4j.*;

public class UserControllerImpl implements UserController {

    //日志对象
    private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);
    //用户服务对象
    private UserServiceImpl userService = new UserServiceImpl();

    //用户登录方法
    @Override
    public boolean login(String userName, String password) {
        try {
            return userService.validateLogin(userName, password);
        } catch (Exception e) {
            logger.error("用户登录失败，用户名：{}，错误信息：{}", userName, e.getMessage());
            return false;
        }
    }

    //用户注册方法
    @Override
    public void register(String userName, String password, String role) {
        try {
            //如果用户表不存在，则创建用户表
            userService.createUserTable();
            userService.registerUser(userName, password, role);
            logger.info("用户注册成功，用户名：{}", userName);
        } catch (Exception e) {
            logger.error("用户注册失败，用户名：{}，错误信息：{}", userName, e.getMessage());
        }
    }

    @Override
    public String getUserInfo(String userIdString) {
        try {
            Integer userId = Integer.parseInt(userIdString);
            return userService.getUserInfo(userId);
        } catch (NumberFormatException e) {
            logger.error("获取用户信息失败，用户ID格式错误：{}", userIdString, e);
            return null;
        } catch (Exception e) {
            logger.error("获取用户信息失败，用户ID：{}，错误信息：{}", userIdString, e.getMessage());
            return null;
        }
    }

    //用户信息更新方法
    @Override
    public void updateUserInfo(Integer userId, String userName, String password, String role) {
        try {
            userService.updateUserInfo(userId, userName, password);
            logger.info("用户信息更新成功，用户ID：{}", userId);
        } catch (Exception e) {
            logger.error("用户信息更新失败，用户ID：{}，错误信息：{}", userId, e.getMessage());
        }  
    }

    //用户删除方法
    @Override
    public void deleteUser(Integer userId) {
        try {
            userService.deleteUser(userId);
            logger.info("用户删除成功，用户ID：{}", userId);
        } catch (Exception e) {
            logger.error("用户删除失败，用户ID：{}，错误信息：{}", userId, e.getMessage());
        }
    }

    //列出所有用户方法
    @Override
    public String getAllUsers() {
        try {
            return userService.listAllUsers();
        } catch (Exception e) {
            logger.error("获取所有用户信息失败，错误信息：{}", e.getMessage());
            return null;
        }
    }

    //重置用户密码方法
    @Override
    public boolean isUserNameExists(String userName) {
        try {
            return userService.isUsernameExists(userName);
        } catch (Exception e) {
            logger.error("检查用户名是否存在失败，用户名：{}，错误信息：{}", userName, e.getMessage());
            return false;
        }
    }

    //验证登录凭据方法
    @Override
    public String getUserRoleByName(String userName) {
        try {
            return userService.getUserRoleByName(userName);
        } catch (Exception e) {
            logger.error("获取用户角色失败，用户名：{}，错误信息：{}", userName, e.getMessage());
            return null;
        }
    }

    //根据用户名获取用户ID
    @Override
    public Integer getUserIdByName(String userName) {
        try {
            return userService.getUserIdByName(userName);
        } catch (Exception e) {
            logger.error("获取用户ID失败，用户名：{}，错误信息：{}", userName, e.getMessage());
            return null;
        }
    }

    //创建用户表方法
    //这个直接写再UserService里了，这里留一个接口先
    @Override
    public void createUserTable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUserTable'");
    }

}
