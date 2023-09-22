package com.psm.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowcaseBoxEntity implements Serializable {
    private String ID;
    private String userName;
    private String profile;
    private String honor;
    private String authorBrief;
    private String authorID;
    private String works;
    private String modify_time;
    private String commentNum;
    private String primarySort;
    private String lastSort;
    private String isIdle;
    private String canQuicky;
    private String price;
    private String type;
    private String imgPath;
    private String videoPath;
    private String abstractInfo;
}
