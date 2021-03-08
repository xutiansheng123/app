package com.ta.xutiansheng.xtsapp.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;
import com.ta.xutiansheng.xtsapp.mvp.view.IView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView {
    private Unbinder unbinder;
    protected Context context;
    protected Activity activity;
    protected P mpresneter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context).inflate(getlayoutid(), null);
        unbinder = ButterKnife.bind(this, view);
        initpresent();
        initview();
       initdata();
        return view;
    }

    protected abstract void initview();

    private void initpresent() {
        mpresneter = createPresenter();
        // 完成Presenter和view的绑定
        if (mpresneter != null) {
            mpresneter.attachView(this);
        }
    }
    protected  void initdata(){};
    protected abstract P createPresenter();

    protected abstract int getlayoutid();


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onFail(Throwable ex, String code, String msg) {

    }

    @Override
    public void onNetError() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onResume() {
        super.onResume();
        initdata();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mpresneter != null) {
            mpresneter.detachView();
            mpresneter = null;
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
