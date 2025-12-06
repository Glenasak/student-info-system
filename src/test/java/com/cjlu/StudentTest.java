package com.cjlu;

import com.cjlu.dao.impl.StudentDaoImpl;
import com.cjlu.entity.Student;
import com.cjlu.entity.User;
import com.cjlu.service.UserService;
import com.cjlu.service.impl.UserServiceImpl;

public class StudentTest {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        try {
            
            User user = userService.getUserByUsername("BeiMingXiaoYu");
            System.out.println("用户信息: " + user);
            //输出用户字段信息
            System.out.println("用户名: " + user.getUsername());
            System.out.println("密码: " + user.getPassword());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}