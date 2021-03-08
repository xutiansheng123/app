package com.ta.xutiansheng.xtsapp.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.api.interceptor.GlobleSettingData;
import com.ta.xutiansheng.xtsapp.bean.UserBean;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.usersettings.UserPresent;
import com.ta.xutiansheng.xtsapp.usersettings.UserSettingsContact;
import com.ta.xutiansheng.xtsapp.util.GifSizeFilter;
import com.ta.xutiansheng.xtsapp.util.ShardPrenUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.filter.Filter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserinfoSettingActivity extends BaseMvpActivity<UserPresent> implements UserSettingsContact.UserSettingView {

    private static final int REQUEST_CODE_CHOOSE = 200;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.top)
    ConstraintLayout top;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right)
    ImageView right;
    @BindView(R.id.iv_headimg)
    CircleImageView ivHeadimg;
    @BindView(R.id.con_head)
    ConstraintLayout conHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.con_nickname)
    ConstraintLayout conNickname;
    @BindView(R.id.right_2)
    ImageView right2;
    @BindView(R.id.con_address)
    ConstraintLayout conAddress;
    @BindView(R.id.right_3)
    ImageView right3;
    @BindView(R.id.con_userIntroduction)
    ConstraintLayout conUserIntroduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, Object> map = new HashMap<>();
        map.put("userName", GlobleSettingData.getInstance().getAuthInfo().getUserName());
        mPresenter.getUserInfo(map);
        ivBack.setOnClickListener(view -> {
            finish();
        });
        conAddress.setOnClickListener(v -> {
        startActivity(new Intent(UserinfoSettingActivity.this,AddressActivity.class));
        });
        conNickname.setOnClickListener(v -> {
            Intent intent =new Intent(UserinfoSettingActivity.this,UserinfoEdtActivity.class);
            intent.putExtra("type",0);
            startActivity(intent);
        });
        conUserIntroduction.setOnClickListener(v -> {
            Intent intent =new Intent(UserinfoSettingActivity.this,UserinfoEdtActivity.class);
            intent.putExtra("type",1);
            startActivity(intent);
        });
        conHead.setOnClickListener(view -> {
            Matisse.from(UserinfoSettingActivity.this)
                    .choose(MimeType.ofAll())
                    .countable(true)
                    .maxSelectable(1)
                    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f)
                    .imageEngine(new PicassoEngine())
                    .showPreview(false) // Default is `true`
                    .forResult(REQUEST_CODE_CHOOSE);
        });

    }

    @Override
    protected Snackbar createSnackbar() {
        return null;
    }

    @Override
    protected UserPresent createPresenter() {
        return new UserPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_userinfo_setting;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Map<String, Object> map = new HashMap<>();
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<String> url = Matisse.obtainPathResult(data);
            List<File> files = new ArrayList<>();
            for (int i = 0; i < url.size(); i++) {
                files.add(new File(url.get(i)));
            }
            map.put("userName", GlobleSettingData.getInstance().getAuthInfo().getUserName());
            mPresenter.onUploadHeadModel(files, map);
        }
    }

    @Override
    public void onUploadHeadResult(List<String> urls) {
        Log.d("TAG", "onUploadHeadResult: " + urls.size());
    }

    @Override
    public void onFulide(String msg) {

    }

    @Override
    public void getuserInfo(UserBean data) {
        tvNickname.setText(data.getNickname());
        Picasso.with(this).load(data.getHeadimg()).into(ivHeadimg);
    }

    @Override
    public void getuserFaluid(String msg) {

    }
}