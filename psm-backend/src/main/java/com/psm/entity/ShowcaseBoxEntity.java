package com.psm.entity;

import java.io.Serializable;

public class ShowcaseBoxEntity implements Serializable {
    private String id;

    private String userName;

    private String profile;

    private String honor;

    private String author_brief;

    private String author_id;

    private String works;

    private String modify_time;

    private String commentNum;

    private String primarySort;

    private String lastSort;

    private String isIdle;

    private String canQuicky;

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor_brief() {
        return author_brief;
    }

    public void setAuthor_brief(String author_brief) {
        this.author_brief = author_brief;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getPrimarySort() {
        return primarySort;
    }

    public void setPrimarySort(String primarySort) {
        this.primarySort = primarySort;
    }

    public String getLastSort() {
        return lastSort;
    }

    public void setLastSort(String lastSort) {
        this.lastSort = lastSort;
    }

    public String getIsIdle() {
        return isIdle;
    }

    public void setIsIdle(String isIdle) {
        this.isIdle = isIdle;
    }

    public String getCanQuicky() {
        return canQuicky;
    }

    public void setCanQuicky(String canQuicky) {
        this.canQuicky = canQuicky;
    }

    @Override
    public String toString() {
        return "ShowcaseBoxEntity{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", profile='" + profile + '\'' +
                ", honor='" + honor + '\'' +
                ", author_brief='" + author_brief + '\'' +
                ", author_id='" + author_id + '\'' +
                ", works='" + works + '\'' +
                ", modify_time='" + modify_time + '\'' +
                ", commentNum='" + commentNum + '\'' +
                ", primarySort='" + primarySort + '\'' +
                ", lastSort='" + lastSort + '\'' +
                ", isIdle='" + isIdle + '\'' +
                ", canQuicky='" + canQuicky + '\'' +
                '}';
    }
}
