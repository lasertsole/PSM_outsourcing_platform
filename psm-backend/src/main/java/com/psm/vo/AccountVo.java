package com.psm.vo;

public class AccountVo {
    private String status;

    private String phoneNumber;

    private String userName;

    private String profile;

    private String token;

    public AccountVo(){};

    public AccountVo(String status, String phoneNumber, String userName, String profile, String token) {
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.profile = profile;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AccountVo{" +
                "status='" + status + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + userName + '\'' +
                ", profile='" + profile + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
