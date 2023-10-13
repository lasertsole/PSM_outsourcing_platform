package com.psm.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowcaseBox {
    private String ID;
    private String userName;
    private String profile;
    private String honor;
    private String authorBrief;
    private String authorID;
    private String works;
    private String commentNum;
    private String primarySort;
    private String lastSort;
    private String isIdle;
    private String canQuicky;
}
