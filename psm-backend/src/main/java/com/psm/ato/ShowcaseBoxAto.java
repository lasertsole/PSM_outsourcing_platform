package com.psm.ato;

import java.io.Serializable;

public class ShowcaseBoxAto implements Serializable {

    private String primarySort;

    private String lastSort;

    private String SortWay;

    private String isIdle;

    private String canQuicky;

    public ShowcaseBoxAto(){};

    public ShowcaseBoxAto(String primarySort, String lastSort, String sortWay, String isIdle, String canQuicky) {
        this.primarySort = primarySort;
        this.lastSort = lastSort;
        SortWay = sortWay;
        this.isIdle = isIdle;
        this.canQuicky = canQuicky;
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

    public String getSortWay() {
        return SortWay;
    }

    public void setSortWay(String sortWay) {
        SortWay = sortWay;
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
                ", lastSort='" + lastSort + '\'' +
                ", SortWay='" + SortWay + '\'' +
                ", isIdle='" + isIdle + '\'' +
                ", canQuicky='" + canQuicky + '\'' +
                '}';
    }
}
