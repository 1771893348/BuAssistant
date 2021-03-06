package com.wcjr.buassistant.usecase.base;

import com.wcjr.buassistant.interfaces.InteractorCallBack;

/**
 * @author wgw
 * @time 2018/9/12 9:43
 * @class describe
 */
public class CaseInteractor{

    public static <T extends CaseInteface,B>void exeCase(T t, InteractorCallBack<B> interactorCallBack){
        t.executeHttp(interactorCallBack);
    }
}
