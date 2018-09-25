package com.wcjr.buassistant.usecase.base;

import com.wcjr.buassistant.interfaces.InteractorCallBack;

/**
 * @author wgw
 * @time 2018/9/25 15:07
 * @class describe
 */
public interface  CaseInteface<T> {
    void executeHttp(InteractorCallBack<T> interactorCallBack);
}
