package com.wcjr.buassistant.usecase;

import io.reactivex.Flowable;

/**
 * @author wgw
 * @time 2018/9/7 16:12
 * @class describe
 */
public abstract class BaseCase<T,Params> {

    public void executeHttp(Params params){
        buildObservable(params);
    }

    public abstract Flowable<T> buildObservable(Params params);
}
