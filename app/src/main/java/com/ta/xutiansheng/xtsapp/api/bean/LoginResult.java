package com.ta.xutiansheng.xtsapp.api.bean;

public class LoginResult {


    private String shopId;
    private String phonenum;
    private String uuid;
    private String token;
    private String userName;
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "shopId='" + shopId + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", uuid='" + uuid + '\'' +
                ", token='" + token + '\'' +
                ", username='" + userName + '\'' +
                '}';
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
