package com.cjlu;

import com.cjlu.dao.impl.ScoreDaoImpl;


public class StudentTest {

    public static void main(String[] args) {
    ScoreDaoImpl scoreDao = new ScoreDaoImpl();
    // Check whether the table exists
    boolean exists = scoreDao.checkTableExists("SCORES");
    System.out.println("Does the SCORES table exist: " + exists);
    }
}