package com.wcjr.buassistant.androidlibrary.base;

/**
 * Created by chenlong on 2017/8/7.
 *
 */

public class BaseEntity {

    private String errorCode;

    protected String msg;

    public String getCode() {
        return errorCode;
    }

    public void setCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
