package com.cjlu;

import com.cjlu.dao.impl.ScoreDaoImpl;

public class ScoreTableTest {
    public static void main(String[] args) {
        ScoreDaoImpl dao = new ScoreDaoImpl();
        try {
            System.out.println("Preparing to create the scores table...");
            dao.createScoreTable();
            System.out.println("Finished executing the scores table creation logic. Please check derby.log or verify in the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}