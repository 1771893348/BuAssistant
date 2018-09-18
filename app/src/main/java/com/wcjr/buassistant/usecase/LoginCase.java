package com.wcjr.buassistant.usecase;

import android.content.ContentValues;

import com.wcjr.buassistant.androidlibrary.base.BaseEntity;
import com.wcjr.buassistant.data.repository.LoginRepository;
import com.wcjr.buassistant.interfaces.InteractorCallBack;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * @author wgw
 * @time 2018/9/7 16:06
 * @class describe
 */
public class LoginCase extends BaseCase<LoginCase.LoginEntity>{

    private LoginRepository mLoginRepository;
    public RequestValues requestValues;
    public LoginCase(){
        mLoginRepository = new LoginRepository();
        requestValues = new RequestValues();
    }
    @Override
    public void buildObservable(InteractorCallBack<LoginEntity> interactorCallBack) {
        mLoginRepository.login(requestValues.name,requestValues.pwd,false,"").subscribe(new Consumer<LoginEntity>() {
            @Override
            public void accept(LoginEntity loginEntity) throws Exception {
                interactorCallBack.accept(loginEntity);
            }

        });
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
