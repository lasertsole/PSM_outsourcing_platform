package com.psm.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemBox {
    private String ID;
    private String authorID;
    private String price;
    private String type;
    private String imgPath;
    private String videoPath;
    private String abstractInfo;
}
