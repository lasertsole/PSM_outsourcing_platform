package com.psm.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowcaseBoxParamsInput {
    private String primarySort;
    private String lastSort;
    private String sortWay;
    private Boolean isIdle;
    private Boolean canQuicky;
}
