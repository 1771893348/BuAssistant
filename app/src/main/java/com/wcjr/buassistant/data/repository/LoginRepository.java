package com.wcjr.buassistant.data.repository;


import com.wcjr.buassistant.BaseApplication;
import com.wcjr.buassistant.data.remote.wrapper.ApiWrapper;
import com.wcjr.buassistant.usecase.LoginCase;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * @author chenlong
 *
 * the data repository for login module
 */

public class LoginRepository {
    /**
     * 登录
     *
     * @param name
     * @param pwd
     * @return
     */
    public Flowable<LoginCase.LoginEntity> login(String name, String pwd, boolean sysNoticeState, String auth) {
        return ApiWrapper.getInstance(BaseApplication.getInstance()).login(name, pwd,  sysNoticeState, auth);
    }

    public void insetUser(){
        Observable.create(new ObservableOnSubscribe<LoginCase.LoginEntity>() {
            @Override
            public void subscribe(ObservableEmitter<LoginCase.LoginEntity> e) throws Exception {

            }
        }).subscribe(new Consumer<LoginCase.LoginEntity>() {
            @Override
            public void accept(LoginCase.LoginEntity loginEntity) throws Exception {

            }
        });
    }
}
