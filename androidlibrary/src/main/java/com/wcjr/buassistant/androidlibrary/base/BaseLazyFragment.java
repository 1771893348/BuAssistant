package com.wcjr.buassistant.androidlibrary.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.wcjr.buassistant.androidlibrary.R;
import com.wcjr.buassistant.androidlibrary.utils.Funs;
import com.wcjr.buassistant.androidlibrary.view.AppProgressDialog;


/**
 * fragment基类
 */
public abstract class BaseLazyFragment extends Fragment {
    /**
     * 没有网络
     */
    public static final int ERROR_MSG_NO_NET = 0;
    /**
     * 没有记录
     */
    public static final int ERROR_MSG_NO_RECORD = 1;
    /**
     * 没有红包
     */
    public static final int ERROR_MSG_NO_GIFT = 2;
    /**
     * 没有历史红包
     */
    public static final int ERROR_MSG_NO_GIFT_HIS = 3;
    /**
     * 贷后风控
     */
    public static final int ERROR_MSG_NO_AFTER_RISK = 4;

    private View parentView = null;

    private FragmentActivity activity;

    // 标志位 标志已经初始化完成。
    protected boolean isPrepared;

    //标志位 fragment是否可见
    protected boolean isVisible;


    /**
     * 错误提示
     */
    protected View errorView = null;

    protected AppProgressDialog appProgressDialog;

    private boolean isFragmentVisible;
    private boolean isReuseView;
    private boolean isFirstVisible;
    private View rootView;

    public abstract
    @LayoutRes
    int getLayoutResId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        parentView = inflater.inflate(getLayoutResId(), null);
        activity = getSupportActivity();

        createDialog();
        initView(parentView);
        return parentView;
    }
    public abstract void initView(View view);
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        finishCreateView(savedInstanceState);

        //如果setUserVisibleHint()在rootView创建前调用时，那么
        //就等到rootView创建完后才回调onFragmentVisibleChange(true)
        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作
        if (rootView == null) {
            rootView = view;
            if (getUserVisibleHint()) {
                if (isFirstVisible) {
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                onFragmentVisibleChange(true);
                isFragmentVisible = true;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public abstract void finishCreateView(Bundle state);

    private void createDialog() {
        if (appProgressDialog == null) {
            appProgressDialog = new AppProgressDialog(getActivity(), R.style.CustomProgressDialog);
            appProgressDialog.setCanceledOnTouchOutside(false);
            Window windowapp = appProgressDialog.getWindow();
            if (null != windowapp)
                windowapp.getAttributes().gravity = Gravity.CENTER;
        }
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
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            errorView = LayoutInflater.from(getContext()).inflate(R.layout.layout_error_msg, null);
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
            case ERROR_MSG_NO_AFTER_RISK:
                imgId = R.drawable.default_noabnormalities;
                msg = getString(R.string.after_risk_msg);
                break;
            default:
                break;
        }

        textView.setText(msg);
        imageView.setImageResource(imgId);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }

    //setUserVisibleHint()在Fragment创建时会先被调用一次，传入isVisibleToUser = false
    //如果当前Fragment可见，那么setUserVisibleHint()会再次被调用一次，传入isVisibleToUser = true
    //如果Fragment从可见->不可见，那么setUserVisibleHint()也会被调用，传入isVisibleToUser = false
    //总结：setUserVisibleHint()除了Fragment的可见状态发生变化时会被回调外，在new Fragment()时也会被回调
    //如果我们需要在 Fragment 可见与不可见时干点事，用这个的话就会有多余的回调了，那么就需要重新封装一个
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //setUserVisibleHint()有可能在fragment的生命周期外被调用
        if (rootView == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser) {
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }
    }

    protected void showProgressBar() {
        Funs.hideKeyBoard(getActivity());
        appProgressDialog.show();
    }


    protected void hideProgressBar() {
        appProgressDialog.dismiss();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T $(int id) {

        return (T) parentView.findViewById(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        initVariable();
    }

    private void initVariable() {
        isFirstVisible = true;
        isFragmentVisible = false;
        rootView = null;
        isReuseView = true;
    }

    /**
     * 设置是否使用 view 的复用，默认开启
     * view 的复用是指，ViewPager 在销毁和重建 Fragment 时会不断调用 onCreateView() -> onDestroyView()
     * 之间的生命函数，这样可能会出现重复创建 view 的情况，导致界面上显示多个相同的 Fragment
     * view 的复用其实就是指保存第一次创建的 view，后面再 onCreateView() 时直接返回第一次创建的 view
     *
     * @param isReuse
     */
    protected void reuseView(boolean isReuse) {
        isReuseView = isReuse;
    }

    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     *
     * 可在该回调方法里进行一些ui显示与隐藏，比如加载框的显示和隐藏
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    /**
     * 在fragment首次可见时回调，可在这里进行加载数据，保证只在第一次打开Fragment时才会加载数据，
     * 这样就可以防止每次进入都重复加载数据
     * 该方法会在 onFragmentVisibleChange() 之前调用，所以第一次打开时，可以用一个全局变量表示数据下载状态，
     * 然后在该方法内将状态设置为下载状态，接着去执行下载的任务
     * 最后在 onFragmentVisibleChange() 里根据数据下载状态来控制下载进度ui控件的显示与隐藏
     */
    protected void onFragmentFirstVisible() {

    }

    protected boolean isFragmentVisible() {
        return isFragmentVisible;
    }
}
