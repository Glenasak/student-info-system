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


//这是一个通过本地文件维持用户登录状态的工具类
//为什么使用本地文件呢？
//因为我疯啦！！！
//好吧其实是为了方便一键部署
public class LocalFileLoginManager {

    // 定义登录文件名（直接存放在程序根目录）
    private static final String LOGIN_FILE_NAME = "user_session.dat";
    
    // 用于格式化登录时间的日期格式
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final Path loginFilePath;

    UserDaoImpl userDao = new UserDaoImpl();

    //初始化登陆文件方法
    public LocalFileLoginManager() {
        // 获取程序运行的根目录（当前工作目录）
        String appRootDir = System.getProperty("user.dir");
        // 构建登录文件完整路径（根目录 + 文件名）
        this.loginFilePath = Path.of(appRootDir, LOGIN_FILE_NAME);
    }

    //用户登录方法
    public boolean login(String username, String password) {
        // 基础参数校验
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            System.err.println("用户名和密码不能为空！");
            return false;
        }
        
        System.out.println("正在验证用户凭据...");
        //验证登录
        boolean isCredentialValid = false; 
        if(userDao.getUserPassword(userDao.getUserIdByName(username))==password) {
        	isCredentialValid = true;
        }
        

        if (isCredentialValid) {
            try {
                // 直接在程序根目录创建文件
                try (BufferedWriter writer = Files.newBufferedWriter(loginFilePath, StandardCharsets.UTF_8)) {
                    writer.write(username);
                    writer.newLine(); 
                    writer.write(LocalDateTime.now().format(DATE_FORMATTER));
                }
                
                System.out.println("登录成功！登录文件已保存至: " + loginFilePath);
                return true;
            } catch (IOException e) {
                System.err.println("创建登录文件失败: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("用户名或密码错误，登录失败！");
            return false;
        }
    }

    //检测用户是否已经登录
    public boolean isLoggedIn() {
        return Files.exists(loginFilePath);
    }

    //获取当前用户名
    public Optional<String> getCurrentUser() {
        if (!isLoggedIn()) {
            return Optional.empty();
        }
        try {
            // 读取文件第一行（用户名）
            String username = Files.readAllLines(loginFilePath, StandardCharsets.UTF_8).getFirst();
            return Optional.ofNullable(username.trim());
        } catch (IOException e) {
            System.err.println("读取登录文件失败: " + e.getMessage());
            return Optional.empty();
        }
    }

    //退出登录
    public boolean logout() {
        if (isLoggedIn()) {
            try {
                Files.delete(loginFilePath);
                System.out.println("退出登录成功！登录文件已删除: " + loginFilePath);
                return true;
            } catch (IOException e) {
                System.err.println("删除登录文件失败: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("当前用户未登录，无需退出！");
            return true; // 未登录状态视为退出成功
        }
    }

    //获取登录文件路径（用于调试）
    public String getLoginFilePath() {
        return loginFilePath.toString();
    }
}