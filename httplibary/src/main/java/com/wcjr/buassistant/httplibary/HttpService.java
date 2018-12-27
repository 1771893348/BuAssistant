package com.wcjr.buassistant.httplibary;

/**
 * @author wgw
 * @time 2018/12/27 11:16
 * @class describe
 */
public interface HttpService {
     void setUrl(String path);
     void  setData(byte[] data);
     void setListener(HttpCallBack httpCallBack);
     void execute();
}
