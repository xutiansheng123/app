package com.ta.xutiansheng.xtsapp.activity;

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
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.ta.xutiansheng.xtsapp.fragment.Indexfragment;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.fragment.MineFragment;
import com.ta.xutiansheng.xtsapp.fragment.OrderFragment;
import com.ta.xutiansheng.xtsapp.handler.SockerHanlder;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;
import com.ta.xutiansheng.xtsapp.service.NettyService;
import com.ta.xutiansheng.xtsapp.util.ShardPrenUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.BindView;

public class IndexActivity extends BaseMvpActivity {
    public Logger logger = LoggerFactory.getLogger(IndexActivity.class);
    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.rg_index)
    RadioGroup rgIndex;
    private Fragment currentFragment = new Fragment();
    private Indexfragment indexfragment;
    private OrderFragment orderFragment;
    private MineFragment minefragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPermission(this);
        startService(new Intent(IndexActivity.this, Myservice.class));

        indexfragment = new Indexfragment();
        orderFragment = new OrderFragment();
        minefragment = new MineFragment();
        replaceFragment(indexfragment);
    }

    public void initPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 200);
            } else {
                getLocation();

            }
        } else {
            getLocation();

        }
    }

    @Override
    protected Snackbar createSnackbar() {
        return null;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_index;
    }

    @Override
    protected void initView() {

    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            double altitude = location.getLongitude();
            double latitude = location.getLatitude();
            ShardPrenUtil.getIntance(this).SetParam(ShardPrenUtil.ALTITUDE, String.valueOf(altitude));
            ShardPrenUtil.getIntance(this).SetParam(ShardPrenUtil.LATITUDE, String.valueOf(latitude));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        rgIndex.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.rdo_index:
                    replaceFragment(indexfragment);
                    setAndroidNativeLightStatusBar(this, false);
                    break;
                case R.id.rdo_order:
                    setAndroidNativeLightStatusBar(this, false);
                    replaceFragment(orderFragment);
                    break;
                case R.id.rdo_mine:
                    setAndroidNativeLightStatusBar(this, true);
                    replaceFragment(minefragment);
                    break;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200:
                getLocation();
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (!fragment.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.fragment, fragment)
                    .commit();
        } else {
            transaction
                    .hide(currentFragment)
                    .show(fragment)
                    .commit();
        }
        currentFragment = fragment;
    }
}