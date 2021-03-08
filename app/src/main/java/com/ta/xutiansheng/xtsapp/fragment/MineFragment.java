package com.ta.xutiansheng.xtsapp.fragment;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.squareup.picasso.Picasso;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.activity.AddressActivity;
import com.ta.xutiansheng.xtsapp.activity.UserListActivity;
import com.ta.xutiansheng.xtsapp.activity.UserinfoSettingActivity;
import com.ta.xutiansheng.xtsapp.bean.UserBean;
import com.ta.xutiansheng.xtsapp.mvp.BaseFragment;
import com.ta.xutiansheng.xtsapp.userInfo.UserInfoContact;
import com.ta.xutiansheng.xtsapp.userInfo.UserInfoPresent;
import com.ta.xutiansheng.xtsapp.util.ShardPrenUtil;
import com.ta.xutiansheng.xtsapp.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends BaseFragment<UserInfoPresent> implements UserInfoContact.UserInfoView {
    @BindView(R.id.img_icon)
    CircleImageView imgIcon;
    @BindView(R.id.tv_pickname)
    TextView tvPickname;
    @BindView(R.id.msg)
    ImageView msg;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.members)
    ConstraintLayout members;
    @BindView(R.id.my_address)
    TextView myAddress;


    @Override
    protected void initview() {

    }

    @Override
    public void onResume() {
        super.onResume();
        myAddress.setOnClickListener(v -> {
            startActivity(new Intent(context, AddressActivity.class));
        });
        msg.setOnClickListener(v -> {
            startActivity(new Intent(context, UserListActivity.class));
        });
        Map<String, Object> map = new HashMap<>();
        map.put("userName", ShardPrenUtil.getIntance(context).GetParam(ShardPrenUtil.UserName));
        mpresneter.getUserInfo(map);
        imgIcon.setOnClickListener(view -> {
            startActivity(new Intent(context, UserinfoSettingActivity.class));
        });
    }

    @Override
    protected UserInfoPresent createPresenter() {
        return new UserInfoPresent();
    }

    @Override
    protected int getlayoutid() {
        return R.layout.minelayout;
    }

    @Override
    public void getuserInfo(UserBean data) {
        Picasso.with(context).load(data.getHeadimg()).into(imgIcon);
        tvPickname.setText(data.getNickname());
        tvTag.setText(data.getTag());
    }

    @Override
    public void getuserFaluid(String msg) {
        ToastUtil.MakeToast(context, msg);
    }

    @Override
    public void updateUserInfo(String msg) {

    }

    @Override
    public void updateUserInfoFauild(String msg) {

    }
}
