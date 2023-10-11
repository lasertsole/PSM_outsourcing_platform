package com.psm.custom;

//自定义异常，用于返回数据给前端时显示在error中的具体信息
public class DgsException extends Exception{
    public DgsException(String message) {
        super(message);
    }
}
