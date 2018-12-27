package com.wcjr.buassistant.httplibary;


import com.google.gson.Gson;

public class HttpTask<T> implements Runnable {
    private HttpCallBack mHttpCallBack;
    private HttpService mHttpService;
    public HttpTask(String url,T requestBean,HttpService httpService,HttpCallBack httpCallBack){
        this.mHttpCallBack = httpCallBack;
        this.mHttpService = httpService;
        mHttpService.setUrl(url);
        mHttpService.setListener(mHttpCallBack);
        Gson gson = new Gson();
        byte[] bytes = gson.toJson(requestBean).getBytes();
        mHttpService.setData(bytes);
    }
    @Override
    public void run() {
        mHttpService.execute();
    }
}
