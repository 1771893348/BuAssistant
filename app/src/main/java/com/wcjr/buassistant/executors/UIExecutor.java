package com.wcjr.buassistant.executors;


import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UIExecutor implements PostExecutor {
    private static UIExecutor instance = new UIExecutor();

    private  UIExecutor(){

    }

    public static UIExecutor getInstance() {
        return instance;
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
