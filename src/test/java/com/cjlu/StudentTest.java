package com.cjlu;

import com.cjlu.dao.impl.ScoreDaoImpl;
import com.cjlu.dao.impl.StudentDaoImpl;
import com.cjlu.entity.Student;
import com.cjlu.entity.User;
import com.cjlu.service.UserService;
import com.cjlu.service.impl.UserServiceImpl;

public class StudentTest {

    public static void main(String[] args) {
       ScoreDaoImpl scoreDao = new ScoreDaoImpl();
       //检查表是否存在
       boolean exists = scoreDao.checkTableExists("SCORES");
       System.out.println("表SCORES是否存在: " + exists);
    }
}