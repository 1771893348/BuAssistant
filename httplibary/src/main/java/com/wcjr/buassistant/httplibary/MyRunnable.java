package com.wcjr.buassistant.httplibary;

/**
 * @author wgw
 * @time 2018/12/27 10:33
 * @class describe
 */
public class MyRunnable implements Runnable {
    private String threadName = "task:";
    private int rejectNum = 0;
    public MyRunnable(){
        this.threadName = threadName+System.currentTimeMillis();
    }
    public MyRunnable(String name){
        this.threadName = name;
    }

    public String getThreadName() {
        return threadName;
    }

    public void addRejectNum(){
        this.rejectNum++;
    }

    public void setRejectNum(int num){
        this.rejectNum = num;
    }

    public int getRejectNum(){
        return rejectNum;
    }
    @Override
    public void run() {

    }
}
