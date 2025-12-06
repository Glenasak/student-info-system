package com.cjlu.service.impl;

import com.cjlu.service.UserService;
import org.slf4j.*;
import com.cjlu.dao.impl.UserDaoImpl;
import com.cjlu.entity.User;
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
    public void registerUser(String username, String password, String email) throws Exception {
        try{
            //检查用户表是否存在，若不存在则创建
            if(!userDao.isUserTableExists()){
                userDao.createUserTable();
                logger.info("用户表不存在，已创建新用户表");
            }
            //检查用户名是否已存在
            if(userDao.isUserNameExists(username)){
                logger.warn("用户注册失败，用户名已存在: {}", username);
                throw new Exception("用户名已存在");
            }
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

    //验证登录方法
    @Override
    public boolean validateLogin(String userName, String password) {
        try{
            boolean valid = userDao.validateCredentials(userName, password);
            logger.info("验证登录凭据成功: {}", userName);
            return valid;
        }catch(Exception e){
            logger.error("验证登录凭据失败: {}", userName, e);
            throw e;
        }
    }

    public void createUserTable() {
        try{
            userDao.createUserTable();
            logger.info("创建用户表成功");
        }catch(Exception e){
            logger.error("创建用户表失败", e);
            throw e;
        }
    }

    public String getUserRoleByName(String userName) {
        try{
            String role = userDao.getUserRoleByName(userName);
            logger.info("获取用户角色成功: {}", userName);
            return role;
        }catch(Exception e){
            logger.error("获取用户角色失败: {}", userName, e);
            throw e;
        }
    }

    //根据用户名获取用户ID
    public Integer getUserIdByName(String userName) {
        try{
            Integer userId = userDao.getUserIdByName(userName);
            logger.info("获取用户ID成功: {}", userName);
            return userId;
        }catch(Exception e){
            logger.error("获取用户ID失败: {}", userName, e);
            throw e;
        }
    }

    public void updateUser(User user) {
        try{
            userDao.updateUser(user.getUserId(), user.getUserName(), user.getPassword(), user.getRole());
            logger.info("更新用户信息成功: {}", user);
        }catch(Exception e){
            logger.error("更新用户信息失败: {}", user, e);
            throw e;
        }
    }

    //通过用户凭证获取用户信息
    public String getUserByCredentials() {
        try{
            LocalFileLoginManager loginManager = new LocalFileLoginManager();
            if(!loginManager.isLoggedIn()){
                logger.warn("获取当前用户信息失败，用户未登录");
                return null;
            }
            String currentUserName = loginManager.getCurrentUser().orElse(null);
            if(currentUserName == null){
                logger.warn("获取当前用户信息失败，用户名为空");
                return null;
            }
            return currentUserName;
        }catch(Exception e){
            logger.error("获取当前用户信息失败", e);
            return null;
        }
    }

    public User getUserByUsername(String username) {
        try{
            User user = userDao.getUserByUsername(username);
            logger.info("通过用户名获取用户信息成功: {}", username);
            return user;
        }catch(Exception e){
            logger.error("通过用户名获取用户信息失败: {}", username, e);
            throw e;
        }
    }


}
