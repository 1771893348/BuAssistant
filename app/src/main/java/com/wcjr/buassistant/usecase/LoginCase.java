package com.wcjr.buassistant.usecase;

import com.wcjr.buassistant.androidlibrary.base.BaseEntity;
import com.wcjr.buassistant.data.repository.LoginRepository;

import io.reactivex.Flowable;

/**
 * @author wgw
 * @time 2018/9/7 16:06
 * @class describe
 */
public class LoginCase extends BaseCase<LoginCase.LoginEntity,LoginCase.RequestValues>{

    private LoginRepository mLoginRepository;
    public LoginCase(){
        mLoginRepository = new LoginRepository();
    }
    @Override
    public Flowable<LoginEntity> buildObservable(RequestValues requestValues) {
        return mLoginRepository.login("","",false,"");
    }

    public static class LoginEntity extends BaseEntity {

    }
    public static class RequestValues{

    }

}
