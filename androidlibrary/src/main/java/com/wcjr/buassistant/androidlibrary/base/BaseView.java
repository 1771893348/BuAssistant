
package com.wcjr.buassistant.androidlibrary.base;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter,int witch);
    boolean isActive();
}
