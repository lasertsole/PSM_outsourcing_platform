package com.psm.ato;

import java.io.Serializable;

public class ShowcaseBoxAto implements Serializable {

    private String primarySort;

    private String middleSort;

    private String lastSort;

    private String isIdle;

    private String canQuicky;

    public ShowcaseBoxAto(){};

    public ShowcaseBoxAto(String primarySort, String middleSort, String lastSort, String isIdle, String canQuicky){
        this.primarySort = primarySort;
        this.middleSort = middleSort;
        this.lastSort = lastSort;
        this.isIdle = isIdle;
        this.canQuicky = canQuicky;
    };

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
        return "ShowcaseBoxAto{" +
                "primarySort='" + primarySort + '\'' +
                ", middleSort='" + middleSort + '\'' +
                ", lastSort='" + lastSort + '\'' +
                ", isIdle='" + isIdle + '\'' +
                ", canQuicky='" + canQuicky + '\'' +
                '}';
    }
}
