package com.ta.xutiansheng.xtsapp.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.api.interceptor.GlobleSettingData;
import com.ta.xutiansheng.xtsapp.bean.UserBean;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.userInfo.UserInfoContact;
import com.ta.xutiansheng.xtsapp.userInfo.UserInfoPresent;
import com.ta.xutiansheng.xtsapp.util.StatusBarUtil;
import com.ta.xutiansheng.xtsapp.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class UserinfoEdtActivity extends BaseMvpActivity<UserInfoPresent> implements UserInfoContact.UserInfoView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.con_top)
    ConstraintLayout conTop;
    @BindView(R.id.edt_info)
    EditText edtInfo;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarDarkTheme(this,true);
        type = getIntent().getIntExtra("type", -1);
        if (type == 0) {
            tvTitle.setText("设置昵称");
            edtInfo.setHint("请输入昵称");
        } else {
            tvTitle.setText("个人简介");
            edtInfo.setHint("介绍下自己吧～");
        }
    }

    @Override
    protected Snackbar createSnackbar() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvSave.setOnClickListener(v -> {
            Map<String, Object> map = new HashMap<>();
            if (type == 0) {
                map.put("nickName", edtInfo.getText().toString());
            } else {
                map.put("tag", edtInfo.getText().toString());
            }
            map.put("userName", GlobleSettingData.getInstance().getAuthInfo().getUserName());
            mPresenter.updateUserInfo(map);
        });
    }

    @Override
    protected UserInfoPresent createPresenter() {
        return new UserInfoPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_userinfo_edt;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void getuserInfo(UserBean data) {

    }

    @Override
    public void getuserFaluid(String msg) {

    }

    @Override
    public void updateUserInfo(String msg) {
        finish();
    }

    @Override
    public void updateUserInfoFauild(String msg) {
        ToastUtil.MakeToast(this, msg);
        finish();
    }
}