package com.ta.xutiansheng.xtsapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.activity.IndexActivity;
import com.ta.xutiansheng.xtsapp.api.bean.LoginResult;
import com.ta.xutiansheng.xtsapp.api.interceptor.GlobleSettingData;
import com.ta.xutiansheng.xtsapp.login.LoginCont;
import com.ta.xutiansheng.xtsapp.login.LoginPrensenter;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.util.OtherUtil;
import com.ta.xutiansheng.xtsapp.util.ShardPrenUtil;
import com.ta.xutiansheng.xtsapp.util.StringIsEmptyUtil;
import com.ta.xutiansheng.xtsapp.util.ToastUtil;
import com.wang.avi.AVLoadingIndicatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<LoginPrensenter> implements LoginCont.LoginView {

    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.edit_passwd)
    EditText editPasswd;
    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.login_card)
    ConstraintLayout loginCard;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.btnlogin)
    Button btnlogin;
    Logger logger = LoggerFactory.getLogger(MainActivity.class);
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndroidNativeLightStatusBar(this, true);
    }

    @Override
    protected Snackbar createSnackbar() {
        return null;
    }

    @Override
    protected LoginPrensenter createPresenter() {
        return new LoginPrensenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        String username = (String) ShardPrenUtil.getIntance(this).GetParam(ShardPrenUtil.UserName);
        if (!"-1".equals(username)) {
            editUsername.setText(username);
        }
        btnlogin.setOnClickListener(view -> {
            hideKeyInput();
            String passWd = editPasswd.getText().toString();
            String userName = editUsername.getText().toString();
            if (!StringIsEmptyUtil.IsEmpty(passWd) && !StringIsEmptyUtil.IsEmpty(userName)) {
                avi.setVisibility(View.VISIBLE);
                Map<String, String> map = new HashMap<>();
                map.put("userName", userName);
                map.put("passWord", passWd);
                map.put("deviceNo", OtherUtil.getUUid(this));
                Log.d("TAG", "onResume: "+OtherUtil.getUUid(this));
                mPresenter.onLoging(map);
                btnlogin.setEnabled(false);
                btnlogin.setText("");
                avi.show();
            } else {
                ToastUtil.MakeToast(this, "账号密码必填");
            }
        });
    }

    @Override
    public void onLoginSuccess(LoginResult result) {
        logger.debug(new Gson().toJson(result));
        btnlogin.setEnabled(true);
        //存储token
        GlobleSettingData.getInstance().setResult(result);
        startActivity(new Intent(MainActivity.this, IndexActivity.class));
        ShardPrenUtil.getIntance(this).SetParam(ShardPrenUtil.UserName, result.getUserName());
        finish();
    }

    @Override
    public void onLoginFaiure(String result) {
        ToastUtil.MakeToast(this, result);
        logger.debug(new Gson().toJson(result));
        btnlogin.setEnabled(true);
        btnlogin.setText("登陆");
        avi.hide();
    }

    private void initPerssion() {

    }
}