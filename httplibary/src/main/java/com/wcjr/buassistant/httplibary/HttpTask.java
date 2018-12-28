package com.wcjr.buassistant.httplibary;


import com.google.gson.Gson;

public class HttpTask<T> implements Runnable {
    private HttpService mHttpService;
    private String mType = "POST";
    public HttpTask(String url,T requestBean,String type,HttpService httpService,HttpCallBack httpCallBack){
        if (null !=type && !type.equals("")){
            mType = type;
        }
        this.mHttpService = httpService;
        mHttpService.setUrl(url);
        mHttpService.setListener(httpCallBack);
        mHttpService.setType(mType);
        Gson gson = new Gson();
        byte[] bytes = gson.toJson(requestBean).getBytes();
        mHttpService.setData(bytes);
    }
    @Override
    public void run() {
        mHttpService.execute();
    }
}
