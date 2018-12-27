package com.wcjr.buassistant.httplibary;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadManager  {
    //把任务放到请求队列
    private LinkedBlockingQueue<Runnable> mLinkedBlockingQueue = new LinkedBlockingQueue<>();
    //add task
    public void addTask(Runnable runnable){
        if (null == runnable) return;
        try {
            mLinkedBlockingQueue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //把队列任务放入线程池
    private ThreadPoolExecutor mThreadPoolExecutor;
    private static ThreadManager mThreadManager = new ThreadManager();
    private ThreadManager(){
        mThreadPoolExecutor = new ThreadPoolExecutor(5,30,25,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(4),rejectedExecutionHandler);
        mThreadPoolExecutor.execute(mRunnable);
    }

    public static ThreadManager getInstanse(){
        return mThreadManager;
    }

    //拒绝策略
    private RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            try {
                mLinkedBlockingQueue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    //开始工作
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            while (true){
                try {
                    mThreadPoolExecutor.execute(mLinkedBlockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
