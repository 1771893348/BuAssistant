package com.wcjr.buassistant.usecase;

import android.content.ContentValues;

import com.wcjr.buassistant.androidlibrary.base.BaseEntity;
import com.wcjr.buassistant.data.repository.LoginRepository;
import com.wcjr.buassistant.usecase.base.BaseCase;

import io.reactivex.Flowable;

/**
 * @author wgw
 * @time 2018/9/7 16:06
 * @class describe
 */
public class LoginCase extends BaseCase<LoginCase.LoginEntity> {

    private LoginRepository mLoginRepository;
    public RequestValues requestValues;
    public LoginCase(){
        mLoginRepository = new LoginRepository();
        requestValues = new RequestValues();
    }
    @Override
    protected Flowable<LoginEntity> buildObservable() {
       return mLoginRepository.login(requestValues.name,requestValues.pwd,false,"");
    }

    public static class LoginEntity extends BaseEntity {

        public ContentValues toValues() {
            ContentValues values = new ContentValues();

            return values;
        }
    }
    public static class RequestValues{
        String name;
        String pwd;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }

}
