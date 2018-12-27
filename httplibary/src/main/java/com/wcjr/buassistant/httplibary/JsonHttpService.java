package com.wcjr.buassistant.httplibary;

/**
 * @author wgw
 * @time 2018/12/27 11:45
 * @class describe
 */
public class JsonHttpService implements HttpService{
    private String mUrl;
    private byte[] mData;
    private HttpCallBack mHttpCallBack;
    private HttpConnection mHttpConnection;
    public JsonHttpService(){
        mHttpConnection = new HttpConnection();
    }
    @Override
    public void setUrl(String path) {
        this.mUrl = path;
    }

    @Override
    public void setData(byte[] data) {
        this.mData = data;
    }

    @Override
    public void setListener(HttpCallBack httpCallBack) {
        this.mHttpCallBack = httpCallBack;
        mHttpConnection.setCallBack(httpCallBack);
    }

    @Override
    public void execute() {
    }
}
