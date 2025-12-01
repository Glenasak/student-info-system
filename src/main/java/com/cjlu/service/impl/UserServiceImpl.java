package com.cjlu.service.impl;

import com.cjlu.service.UserService;
import org.slf4j.*;
import com.cjlu.dao.impl.UserDaoImpl;
import com.cjlu.util.LocalFileLoginManager;
public class UserServiceImpl implements UserService {

    //定义日志记录器
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    //创建UserDao实例
    private UserDaoImpl userDao = new UserDaoImpl();


    //创建LocalFileLoginManager实例
    private LocalFileLoginManager loginManager = new LocalFileLoginManager();

    //用户注册方法
    @Override
    public void registerUser(String username, String password, String email) {
        try{
            userDao.addUser(username, password, email);
            logger.info("用户注册成功: {}", username);
        }catch(Exception e){
            logger.error("用户注册失败: {}", username, e);
            throw e;
        }
    }

    //用户登录方法
    @Override
    public boolean loginUser(String username, String password) {
        try{
            if(loginManager.login(username, password)){
                logger.info("用户登录成功: {}", username);
                return true;
            }
            else{
                logger.warn("用户登录失败（无效凭据）: {}", username);
                return false;
            }
        }
        catch(Exception e){
            logger.error("用户登录失败: {}", username, e);
            throw e;
        }
    }

    //用户信息更新方法
    @Override
    public void updateUserInfo(int userId, String newEmail, String newPassword) {
        try{
            userDao.updateUser(userId, null, newPassword, newEmail);
            logger.info("用户信息更新成功: 用户ID {}", userId);
        }catch(Exception e){
            logger.error("用户信息更新失败: 用户ID {}", userId, e);
            throw e;
        }
    }

    //用户删除方法
    @Override
    public void deleteUser(int userId) {
        try{
            userDao.deleteUser(userId);
            logger.info("用户删除成功: 用户ID {}", userId);
        }catch(Exception e){
            logger.error("用户删除失败: 用户ID {}", userId, e);
            throw e;
        }
    }

    //获取用户信息方法
    @Override
    public String getUserInfo(int userId) {
        try{
            String userInfo = userDao.getUserInfo(String.valueOf(userId)).toString();
            logger.info("获取用户信息成功: 用户ID {}", userId);
            return userInfo;
        }catch(Exception e){
            logger.error("获取用户信息失败: 用户ID {}", userId, e);
            throw e;
        }
    }

    //列出所有用户方法
    @Override
    public String listAllUsers() {
        try{
            String allUsers = userDao.getAllUsers().toString();
            logger.info("获取所有用户信息成功");
            return allUsers;
        }catch(Exception e){
            logger.error("获取所有用户信息失败", e);
            throw e;
        }
    }

    //重置用户密码方法
    @Override
    public void resetUserPassword(int userId, String newPassword) {
        try{
            userDao.updateUser(userId, null, newPassword, null);
            logger.info("用户密码重置成功: 用户ID {}", userId);
        }catch(Exception e){
            logger.error("用户密码重置失败: 用户ID {}", userId, e);
            throw e;
        }
    }

    //验证用户邮箱方法
    @Override
    public boolean verifyUserEmail(int userId, String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifyUserEmail'");
    }

    //用户登出方法
    @Override
    public void logoutUser(int userId) {
        try{
            loginManager.logout();
            logger.info("用户登出成功: 用户ID {}", userId);
        }catch(Exception e){
            logger.error("用户登出失败: 用户ID {}", userId, e);
            throw e;
        }
    }


    //检查用户名是否存在方法
    @Override
    public boolean isUsernameExists(String username) {
        try{
            boolean exists = userDao.isUserNameExists(username);
            logger.info("检查用户名是否存在成功: {}", username);
            return exists;
        }catch(Exception e){
            logger.error("检查用户名是否存在失败: {}", username, e);
            throw e;
        }
    }

}
