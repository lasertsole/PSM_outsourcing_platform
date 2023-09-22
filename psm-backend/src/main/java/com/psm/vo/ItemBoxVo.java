package com.psm.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemBoxVo implements Serializable {
    private String ID;
    private String authorID;
    private String price;
    private String type;
    private String imgPath;
    private String videoPath;
    private String abstractInfo;
}
