package com.psm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class showcaseDetailEntity {
    private String ID;
    private String authorID;
    private String price;
    private String imgPath;
    private String videoPath;
    private String abstractInfo;
    private String modifyTime;
    private String mainInfo;
    private String profile;
    private String userName;
    private String commentNum;
}
