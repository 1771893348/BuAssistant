package com.wcjr.buassistant.androidlibrary.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseMvpActivity extends Activity {
//    protected BasePresenter presenter;
//    private int typePresenter=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutViewId());
//        presenter = getPresenter(typePresenter);
        init();
    }

    protected abstract void init();
//    protected abstract BasePresenter getPresenter(int type);

    protected abstract int getLayoutViewId();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
