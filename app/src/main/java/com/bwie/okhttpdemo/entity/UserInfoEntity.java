package com.bwie.okhttpdemo.entity;

public class UserInfoEntity {
    public String message;
    public String status;
    public Result result;

    public static class Result{
        public String nickName;
        public String phone;
        public String sessionId;
        public String sex;
        public String userId;

    }
}
