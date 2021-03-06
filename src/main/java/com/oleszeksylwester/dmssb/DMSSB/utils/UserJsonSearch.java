package com.oleszeksylwester.dmssb.DMSSB.utils;

public class UserJsonSearch {
    private long userId;
    private String username;

    public UserJsonSearch(long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
