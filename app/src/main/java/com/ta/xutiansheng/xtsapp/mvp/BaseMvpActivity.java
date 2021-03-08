package com.ta.xutiansheng.xtsapp.mvp;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;
import com.google.android.material.snackbar.Snackbar;
import com.ta.xutiansheng.xtsapp.util.StatusBarUtil;
import com.ta.xutiansheng.xtsapp.util.SystemBarTintManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xutiansheng
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements IView {
    protected P mPresenter;
    private Unbinder unbinder;
    protected Snackbar snackbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        unbinder = ButterKnife.bind(this);
        snackbar =createSnackbar();
        // 初始化Presenter
        initPresenter();
        initView();
    }
    protected void hideKeyInput(){
        View view = this.getCurrentFocus();
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (manager.isActive()&&view.getWindowToken()!=null) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    protected abstract Snackbar createSnackbar();

    private void initPresenter() {
        mPresenter = createPresenter();
        // 完成Presenter和view的绑定
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 将Presenter和view解绑
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        // 解绑ButterKnife
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
    protected String replace(String str) {
        Log.d("---", "replace: 之前" + str);
        str = str.replaceAll(" ", "");
        str = str.replaceAll("\t", "");
        str = str.replaceAll("\n", "");
        Log.d("---", "replace: 之后" + str);
        return str;
    }
    @Override
    public void showLoading() {
        // 这里实现自己的加载弹框
    }

    @Override
    public void dismissLoading() {
        // 取消弹框
    }

    @Override
    public void onFail(Throwable ex, String code, String msg) {
        // 基础的网络请求失败处理
        dismissLoading();
    }

    @Override
    public void onNetError() {
        // 网络错误处理
        dismissLoading();
    }

    /**
     * 页面初始化数据
     */
    protected void initData() {

    }

    /**
     * 创建Presenter
     *
     * @return Presenter
     */
    protected abstract P createPresenter();

    /**
     * 获取当前activity的id
     *
     * @return 当前xml的布局res ID
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view控件
     */
    protected abstract void initView();
    protected  void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}

