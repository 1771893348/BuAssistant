package com.wcjr.buassistant.data.remote;


import android.content.Context;

import com.wcjr.buassistant.executors.JobExecutor;
import com.wcjr.buassistant.executors.PostExecutor;
import com.wcjr.buassistant.executors.ThreadExecutor;
import com.wcjr.buassistant.executors.UIExecutor;
import com.wcjr.buassistant.usecase.LoginCase;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * used to access data from the net interface
 */
public class ApiWrapper {
    private PostExecutor mPostExecutor;
    private ThreadExecutor mThreadExecutor;
    private static ApiWrapper INSTANCE = new ApiWrapper();
    private static Context mContext;

    private ApiWrapper() {
        mThreadExecutor = JobExecutor.getInstance();
        mPostExecutor = UIExecutor.getInstance();
    }

    public static ApiWrapper getInstance(Context context) {
        mContext = context;
        return INSTANCE;
    }

    /**
     * 登录
     *
     * @param name
     * @param pwd
     * @param auth
     * @return
     */
    public Flowable<LoginCase.LoginEntity> login(String name, String pwd, boolean sysNoticeState, String auth) {
        return RetrofitUtil.getService(mContext).login(name, pwd, "", sysNoticeState, auth)
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutor.getScheduler())
                .map(Response::body);
    }


}
