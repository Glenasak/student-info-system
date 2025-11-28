package com.cjlu.entity;

import java.util.Date;

public class User {
    //定义字段
    private Integer userId;  //用户Id（主键）
    private String userName; //用户名
    private String password; //密码
    private String role;     //用户身份
    private Date creatTime;  //账户创建时间

    //无参数构造器
    public User(){}

    //含参数构造器
    public User(Integer userId,String userName,String password,String role,Date creatTime){
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.role = "visitor";
        this.creatTime = Date.from(null);
    }

    //getter方法
    public Integer getUserId(){
        return userId;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }

    public Date getCreatTime(){
        return creatTime;
    }

    //setter方法
    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRole(String role){
        this.role = role;
    }

    public void setCreatTime(Date creatTime){
        this.creatTime = creatTime;
    }

}
