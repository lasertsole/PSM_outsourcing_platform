package com.psm.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowcaseBoxVo implements Serializable{
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
}