package com.wcjr.buassistant.androidlibrary.utils;



/**
 * Created by chenlong on 2017/7/12.
 *
 * 验证码倒计时
 */

public class IdentifyCountUtil {
    private static int countTime = 59;
    private static int nowTime = 59;

    /**
     * 开始倒计时
     */
    public static void startCount() {
//        Observable.interval(0, 1, TimeUnit.SECONDS)
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(aLong -> {
//                    return countTime - aLong.intValue();
//                })
//                .take(countTime + 1)
//                .subscribe(integer -> {
//                            nowTime = integer;
//                        },
//                        throwable -> {
//                            nowTime = 59;
//                        },
//                        () -> {
//                            nowTime = 59;
//                        });
    }

    /**
     * 获取当前倒计时的时间
     * @return
     */
    public static int getNowTime() {
        return nowTime;
    }

    /**
     * 是否可以开始倒计时
     * @return      true 可以开始倒计时
     */
    public static boolean canCountdown() {
        return nowTime == 59;
    }
}
