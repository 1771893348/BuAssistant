package com.wcjr.buassistant.httplibary;

/**
 * @author wgw
 * @time 2018/12/28 13:39
 * @class describe
 */
public class WgwVolly {
    private String url;
    private String type;
    private HttpCallBack httpCallBack;
    private WgwVolly(Builder builder){
        this.url = builder.url;
        this.type = builder.type;
        this.httpCallBack = builder.httpCallBack;
    }
    public<T> void sentRequest(T requestData){
        HttpService httpService = new JsonHttpService();
        HttpTask<T> httpTask = new HttpTask<>(url,requestData,type,httpService,httpCallBack);
        ThreadManager.getInstanse().addTask(httpTask);
    }

    public static class Builder{
        private String url;
        private String type;
        private HttpCallBack httpCallBack;
        public Builder setUrl(String url){
            this.url = url;
            return  this;
        }
        public Builder setType(String type){
            this.type = type;
            return this;
        }

        public Builder setHttpCallBack(HttpCallBack httpCallBack){
            this.httpCallBack = httpCallBack;
            return this;
        }

        public WgwVolly Build(){
            return new WgwVolly(this);
        }
    }
}
