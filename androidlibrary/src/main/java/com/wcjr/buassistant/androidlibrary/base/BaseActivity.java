package com.wcjr.buassistant.androidlibrary.base;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wcjr.buassistant.androidlibrary.R;
import com.wcjr.buassistant.androidlibrary.utils.Funs;
import com.wcjr.buassistant.androidlibrary.view.AppProgressDialog;

import static com.wcjr.buassistant.androidlibrary.base.BaseLazyFragment.ERROR_MSG_NO_GIFT;
import static com.wcjr.buassistant.androidlibrary.base.BaseLazyFragment.ERROR_MSG_NO_NET;
import static com.wcjr.buassistant.androidlibrary.base.BaseLazyFragment.ERROR_MSG_NO_RECORD;


/**
 * <p>
 * activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected View view;
    protected AppProgressDialog appProgressDialog;

    /**
     * 错误提示
     */
    protected View errorView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFlags();
        super.onCreate(savedInstanceState);


        //设置布局内容
        view = LayoutInflater.from(this).inflate(getLayoutId(), null);
        setContentView(view);

        appProgressDialog = new AppProgressDialog(this, R.style.CustomProgressDialog);
        appProgressDialog.setCancelable(false);
        Window windowapp = appProgressDialog.getWindow();
        if (null != windowapp)
            windowapp.getAttributes().gravity = Gravity.CENTER;


        //初始化控件
        initViews(savedInstanceState);
        //初始化ToolBar
        initToolBar();

        Window window = getWindow();
        setHideVirtualKey(window);
        window.getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> setHideVirtualKey(window));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void setFlags() {

    }

    /**
     * 添加一个错误提示, 需要重写refreshError()
     *
     * @param parentView        错误信息加入的父容器
     * @param dataView              数据列表
     * @param msgType
     * @return
     */
    @SuppressLint("InflateParams")
    public void addErrorMsg(ViewGroup parentView, int msgType, View dataView) {
        dataView.setVisibility(View.GONE);
        if (null == errorView) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            errorView = LayoutInflater.from(this).inflate(R.layout.layout_error_msg, null);
            parentView.addView(errorView, 0, layoutParams);
        }
        ImageView imageView = (ImageView) errorView.findViewById(R.id.img);
        TextView textView = (TextView) errorView.findViewById(R.id.msg);

        int imgId = R.drawable.default_norecord;
        String msg = getString(R.string.no_net_error);
        switch (msgType) {
            case ERROR_MSG_NO_NET:
                imgId = R.drawable.default_nonetwork;
                msg = getString(R.string.no_net_error);
                break;
            case ERROR_MSG_NO_RECORD:
                imgId = R.drawable.default_nodetail;
                msg = getString(R.string.no_record_error);
                break;
            case ERROR_MSG_NO_GIFT:
                imgId = R.drawable.default_nocoupon;
                msg = getString(R.string.no_gift_error);
                break;
            default:
                break;
        }

        textView.setText(msg);
        imageView.setImageResource(imgId);
        errorView.setOnClickListener(v -> refreshError());
    }
    /**
     * 添加一个错误提示, 需要重写refreshError()
     *
     * @param parentView        错误信息加入的父容器
     * @param dataView              数据列表
     * @param msgType
     * @return
     */
    @SuppressLint("InflateParams")
    public void addErrorMsgWithpara(ViewGroup parentView, int msgType, View dataView, int top) {
        dataView.setVisibility(View.GONE);
        if (null == errorView) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            errorView = LayoutInflater.from(this).inflate(R.layout.layout_error_msg, null);
            parentView.addView(errorView, 0, layoutParams);
        }
        ImageView imageView = (ImageView) errorView.findViewById(R.id.img);
        TextView textView = (TextView) errorView.findViewById(R.id.msg);

        int imgId = R.drawable.default_norecord;
        String msg = getString(R.string.no_net_error);
        switch (msgType) {
            case ERROR_MSG_NO_NET:
                imgId = R.drawable.default_nonetwork;
                msg = getString(R.string.no_net_error);
                break;
            case ERROR_MSG_NO_RECORD:
                imgId = R.drawable.default_nodetail;
                msg = getString(R.string.no_record_error);
                break;
            case ERROR_MSG_NO_GIFT:
                imgId = R.drawable.default_nocoupon;
                msg = getString(R.string.no_gift_error);
                break;
            default:
                break;
        }

        textView.setText(msg);
        imageView.setImageResource(imgId);
        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.setMargins(0,top,0,0);
        errorView.setOnClickListener(v -> refreshError());
    }

    /**
     * 刷新错误界面
     */
    protected void refreshError() {

    }
    /**
     * 移除错误提示
     *
     * @param parentView        错误信息加入的父容器
     * @param dataView              数据列表
     */
    public void removeError(ViewGroup parentView, View dataView) {
        dataView.setVisibility(View.VISIBLE);
        if (null != errorView) {
            parentView.removeView(errorView);
            errorView = null;
        }
    }

    protected void toast(String msg) {
        if (!TextUtils.isEmpty(msg)){
            Funs.showToastCenter(this,msg);
        }
//            BusinessUtil.showToast(msg);
    }

    public abstract int getLayoutId();

    public abstract void initViews(Bundle savedInstanceState);

    public abstract void initToolBar();


    public void loadData() {
    }


    public void showProgressBar() {
        appProgressDialog.show();
    }


    public void hideProgressBar() {
        appProgressDialog.dismiss();
//        appProgressDialog.hide();
    }
    public void showProgressBarHideKeyboard() {
        Funs.hideKeyBoard(this);
        appProgressDialog.show();
    }


    public void hideProgressBarHideKeyboard() {
        Funs.hideKeyBoard(this);
        appProgressDialog.dismiss();
    }


    public void initRecyclerView() {
    }


    public void initRefreshLayout() {
    }


    public void finishTask() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        Funs.hideKeyBoard(this);
        super.finish();
    }

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
//    /**
//     * 隐藏虚拟按键，并且全屏
//     */
//    protected void hideBottomUIMenu() {
//        //隐藏虚拟按键，并且全屏
//        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower implementation
//            View v = this.getWindow().getDecorView();
//            v.setSystemUiVisibility(View.GONE);
//        } else if (Build.VERSION.SDK_INT >= 19) {
//            //for new implementation versions.
//            View decorView = getWindow().getDecorView();
//            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//            decorView.setSystemUiVisibility(uiOptions);
//        }
//    }
}
