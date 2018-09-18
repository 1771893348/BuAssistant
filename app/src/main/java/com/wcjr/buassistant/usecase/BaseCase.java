package com.wcjr.buassistant.usecase;

import com.wcjr.buassistant.interfaces.InteractorCallBack;

import io.reactivex.Flowable;

/**
 * @author wgw
 * @time 2018/9/7 16:12
 * @class describe
 */
public abstract class BaseCase<T> {

    public void executeHttp(InteractorCallBack<T> interactorCallBack){
        buildObservable(interactorCallBack);
    }

    public abstract void buildObservable(InteractorCallBack<T> interactorCallBack);
}
