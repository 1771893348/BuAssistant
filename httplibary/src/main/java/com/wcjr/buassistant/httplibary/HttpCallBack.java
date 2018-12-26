package com.wcjr.buassistant.httplibary;

/**
 * @author wgw
 * @time 2018/12/21 14:31
 * @class describe
 */
public interface HttpCallBack {
    void success(String response);
    void fail(String error);
}
