package com.wcjr.buassistant.data.repository;


import com.wcjr.buassistant.BaseApplication;
import com.wcjr.buassistant.data.remote.ApiWrapper;
import com.wcjr.buassistant.usecase.LoginCase;

import io.reactivex.Flowable;

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
}
