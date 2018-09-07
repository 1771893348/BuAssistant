package com.wcjr.buassistant.executors;

import io.reactivex.Scheduler;

public interface PostExecutor {
    Scheduler getScheduler();
}
