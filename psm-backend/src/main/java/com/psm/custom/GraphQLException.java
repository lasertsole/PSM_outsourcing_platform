package com.psm.custom;

import lombok.Data;
import lombok.NoArgsConstructor;

//自定义异常，用于返回数据给前端时显示在error中的具体信息
@Data
@NoArgsConstructor
public class GraphQLException extends RuntimeException{
    private String state;
    public GraphQLException(String state, String message) {
        super(message);
        this.state = state;
    }
}
