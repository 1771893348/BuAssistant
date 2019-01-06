package com.wcjr.buassistant.androidlibrary.base;

import java.lang.ref.SoftReference;

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {

    protected SoftReference<V> mView;

    public BasePresenterImpl(V view){
        mView = new SoftReference<>(view);
        mView.get().setPresenter(this,0);
    }

    protected boolean isViewActive(){
        return mView != null && mView.get().isActive();
    }

    protected void detachView(){
        if (mView != null){
            mView.clear();
            mView = null;
        }
    }
}
