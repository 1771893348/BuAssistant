package com.wcjr.buassistant.androidlibrary.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.wcjr.buassistant.androidlibrary.R;

/**
 * Created by chenlong on 2017/7/5.
 * <p>
 * 自定义等待框
 */

public class AppProgressDialog extends Dialog {
    ImageView logo;
    ImageView progress;

    private Context context;
    private static AppProgressDialog appProgressDialog;


    public AppProgressDialog(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public AppProgressDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    protected AppProgressDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    private void initView(@NonNull Context context) {
        this.context = context;
        setContentView(R.layout.layout_progress_dialog);
        logo = findViewById(R.id.logo);
        progress = findViewById(R.id.progress);
//        setCancelable(false);
        Window window = getWindow();
        setHideVirtualKey(window);
        window.getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> setHideVirtualKey(window));
    }

    /**
     * 隐藏虚拟按键
     * @param window
     */
    public void setHideVirtualKey(Window window){
        //保持布局状态
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                //隐藏导航栏
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (Build.VERSION.SDK_INT>=19){
            uiOptions |= 0x00001000;
        }else{
            uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }
        window.getDecorView().setSystemUiVisibility(uiOptions);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            // 加载动画
            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                    context, R.anim.loading_animation);
            // 使用ImageView显示动画
            progress.startAnimation(hyperspaceJumpAnimation);
        }
    }

    //    public static AppProgressDialog createDialog(@NonNull Context context) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(R.layout.layout_progress_dialog, null);
//        appProgressDialog = new AppProgressDialog(context, R.style.CustomProgressDialog);
//        appProgressDialog.setContentView(v);
//        appProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//        return appProgressDialog;
//    }

    @Override
    public void dismiss() {
        super.dismiss();
        progress.clearAnimation();
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
