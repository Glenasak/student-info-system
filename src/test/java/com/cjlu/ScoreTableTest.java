package com.cjlu;

import com.cjlu.dao.impl.ScoreDaoImpl;

public class ScoreTableTest {
    public static void main(String[] args) {
        ScoreDaoImpl dao = new ScoreDaoImpl();
        try {
            System.out.println("准备创建 scores 表...");
            dao.createScoreTable();
            System.out.println("scores 表创建逻辑执行结束，请查看 derby.log 或数据库验证。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}