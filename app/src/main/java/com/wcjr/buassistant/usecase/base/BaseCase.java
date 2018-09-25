package com.wcjr.buassistant.usecase.base;

import android.annotation.SuppressLint;

import com.wcjr.buassistant.interfaces.InteractorCallBack;

import io.reactivex.Flowable;

/**
 * @author wgw
 * @time 2018/9/7 16:12
 * @class describe
 */
public abstract class BaseCase<T> implements CaseInteface<T>{

    @SuppressLint("CheckResult")
    public final void executeHttp(InteractorCallBack<T> interactorCallBack){
        buildObservable().subscribe(interactorCallBack::accept);
//        buildObservable().subscribe(new Consumer<T>() {
//            @Override
//            public void accept(T t) throws Exception {
//                interactorCallBack.accept(t);
//            }
//        });
    }

    protected abstract Flowable<T> buildObservable();
}
