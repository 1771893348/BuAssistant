package com.wcjr.buassistant.interfaces;

/**
 * @author wgw
 * @time 2018/8/23 9:45
 * @class describe
 */
public interface InteractorCallBack<T> {
     void onNext(T t);
     void accept(T t);
     void onError(Throwable t);
     void onComplete();
}
