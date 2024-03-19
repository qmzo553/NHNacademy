package com.nhnacademy;

public class User {
    
    String userId;
    String nickName;

    public User(String userId, String nickName) {
        this.userId = userId;
        this.nickName = nickName;
    }

    public void setUsserId(String userId) {
        this.userId = userId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
    }
}
