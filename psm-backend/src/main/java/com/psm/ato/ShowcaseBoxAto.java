package com.psm.ato;

import java.io.Serializable;

public class ShowcaseBoxAto implements Serializable {

    private String primarySort;

    private String lastSort;

    private String SortWay;

    private Boolean isIdle;

    private Boolean canQuicky;

    public ShowcaseBoxAto(){};

    public ShowcaseBoxAto(String primarySort, String lastSort, String sortWay, Boolean isIdle, Boolean canQuicky) {
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

    public Boolean getIsIdle() {
        return isIdle;
    }

    public void setIsIdle(Boolean isIdle) {
        this.isIdle = isIdle;
    }

    public Boolean getCanQuicky() {
        return canQuicky;
    }

    public void setCanQuicky(Boolean canQuicky) {
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
