package com.psm.vo;
import java.io.Serializable;

public class ShowcaseDetailVo implements Serializable{
    private String userName;

    private String honor;

    private String author_brief;

    private String works;

    private String modify_time;

    private String commentNum;

    private String primarySort;

    private String middleSort;

    private String lastSort;

    private String isIdle;

    private String canQuicky;

    public ShowcaseDetailVo(String userName, String honor, String author_brief, String works, String modify_time, String commentNum, String primarySort, String middleSort, String lastSort, String isIdle, String canQuicky) {
        this.userName = userName;
        this.honor = honor;
        this.author_brief = author_brief;
        this.works = works;
        this.modify_time = modify_time;
        this.commentNum = commentNum;
        this.primarySort = primarySort;
        this.middleSort = middleSort;
        this.lastSort = lastSort;
        this.isIdle = isIdle;
        this.canQuicky = canQuicky;
    }

    public ShowcaseDetailVo(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getAuthor_brief() {
        return author_brief;
    }

    public void setAuthor_brief(String author_brief) {
        this.author_brief = author_brief;
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

    public String getMiddleSort() {
        return middleSort;
    }

    public void setMiddleSort(String middleSort) {
        this.middleSort = middleSort;
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
        return "showcaseBoxVo{" +
                "userName='" + userName + '\'' +
                ", honor='" + honor + '\'' +
                ", author_brief='" + author_brief + '\'' +
                ", works='" + works + '\'' +
                ", modify_time='" + modify_time + '\'' +
                ", commentNum='" + commentNum + '\'' +
                ", primarySort='" + primarySort + '\'' +
                ", middleSort='" + middleSort + '\'' +
                ", lastSort='" + lastSort + '\'' +
                ", isIdle='" + isIdle + '\'' +
                ", canQuicky='" + canQuicky + '\'' +
                '}';
    }
}
