package com.wcjr.buassistant.data.remote;


import com.wcjr.buassistant.androidlibrary.common.CommonUrls;
import com.wcjr.buassistant.usecase.LoginCase;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * retrofit接口定义
 */
public interface APIService {
    @FormUrlEncoded
    @POST(CommonUrls.LOGIN_URL)
    Flowable<Response<LoginCase.LoginEntity>> login(@Field("userName") String userName,
                                                    @Field("password") String userPwd,
                                                    @Field("source") String source,
                                                    @Field("sysNoticeState") boolean sysNoticeState,
                                                    @Field("auth") String auth);

}
