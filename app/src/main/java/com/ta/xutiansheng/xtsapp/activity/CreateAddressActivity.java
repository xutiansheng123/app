package com.ta.xutiansheng.xtsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.address.AddressContact;
import com.ta.xutiansheng.xtsapp.address.AddressPresent;
import com.ta.xutiansheng.xtsapp.api.interceptor.GlobleSettingData;
import com.ta.xutiansheng.xtsapp.bean.AddressResult;
import com.ta.xutiansheng.xtsapp.bean.BaiduResult;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.util.ShardPrenUtil;
import com.ta.xutiansheng.xtsapp.util.ToastUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAddressActivity extends BaseMvpActivity<AddressPresent> implements AddressContact.AddressView {
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.edt_Name)
    EditText edtName;
    @BindView(R.id.edt_num)
    EditText edtNum;
    @BindView(R.id.edt_home)
    EditText edtHome;
    @BindView(R.id.rdo_boy)
    RadioButton rdoBoy;
    @BindView(R.id.rdo_gril)
    RadioButton rdoGril;
    @BindView(R.id.rdo_check)
    RadioGroup rdoCheck;
    @BindView(R.id.rdo_home)
    RadioButton rdoHome;
    @BindView(R.id.rdo_company)
    RadioButton rdoCompany;
    @BindView(R.id.rdo_school)
    RadioButton rdoSchool;
    @BindView(R.id.rg_type)
    RadioGroup rgType;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.con_fome)
    ConstraintLayout conFome;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.top)
    ConstraintLayout top;
    private int sex = 0;
    private String choseAddress;
    private int type = 0;
    private final static int REQUEST_CODE = 200;
    private BaiduResult location;
    Logger logger = LoggerFactory.getLogger(CreateAddressActivity.class);

    @Override
    protected Snackbar createSnackbar() {
        return null;
    }

    @Override
    protected AddressPresent createPresenter() {
        return new AddressPresent();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.createaddress;
    }

    @Override
    protected void onResume() {
        super.onResume();
        AddressResult result = (AddressResult) getIntent().getSerializableExtra("bean");
        if (result != null) {
            address.setText(result.getAddress());
            edtHome.setText(result.getAddressinfo());
            edtName.setText(result.getUsername());
            location = new BaiduResult();
            BaiduResult.LocationBean locationBean = new BaiduResult.LocationBean();
            locationBean.setLat(Double.parseDouble(result.getLatitude()));
            locationBean.setLng(Double.parseDouble(result.getLongitude()));
            location.setLocation(locationBean);
            location.setName(result.getAddressname());
            edtNum.setText(result.getPhonenum());
            if (result.getType() == 0) {
                rdoHome.setChecked(true);
            } else if (result.getType() == 1) {
                rdoCompany.setChecked(true);
            } else {
                rdoSchool.setChecked(true);
            }
            if (result.getSex() == 0) {
                rdoBoy.setChecked(true);
            } else {
                rdoGril.setChecked(false);
            }
        }
        ivBack.setOnClickListener(view -> {
            finish();
        });
        rdoCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rdo_boy:
                        sex = 0;
                        break;
                    case R.id.rdo_gril:
                        sex = 1;
                        break;
                }
            }
        });
        rgType.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.rdo_home:
                    type = 0;
                    break;
                case R.id.rdo_school:
                    type = 2;
                    break;
                case R.id.rdo_company:
                    type = 1;
                    break;
            }
        });
        btnSave.setOnClickListener(view -> {
            String name = edtName.getText().toString();
            String phoneNum = edtNum.getText().toString();
            String home = edtHome.getEditableText().toString();
            Map<String, Object> map = new HashMap<>();
            if (location == null) {
                ToastUtil.MakeToast(this, "请选择地址");
            } else if ("".equals(replace(name))) {
                ToastUtil.MakeToast(this, "请填写姓名");

            } else if ("".equals(replace(phoneNum))) {
                ToastUtil.MakeToast(this, "请填写手机号码");
            } else if ("".equals(replace(home))) {
                ToastUtil.MakeToast(this, "请填写详细住址");
            } else {
                map.put("username", name);
                map.put("userId", GlobleSettingData.getInstance().getAuthInfo().getUuid());
                map.put("longitude", location.getLocation().getLng());
                map.put("latitude", location.getLocation().getLat());
                map.put("phonenum", phoneNum);
                map.put("address", location.getAddress());
                map.put("addressinfo", home);
                map.put("type", type);
                map.put("sex", sex);
                map.put("addressName", location.getName());
                logger.debug(new Gson().toJson(map));
                mPresenter.onCreateAddress(map);
            }
        });
        address.setOnClickListener(view -> {
            Intent intent = new Intent(CreateAddressActivity.this, MapActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    @Override
    protected void initView() {
        rdoBoy.setChecked(true);
        rdoHome.setChecked(true);
    }

    @Override
    public void onCreateAddressSuccess(String msg) {
        ToastUtil.MakeToast(this, msg);
    }

    @Override
    public void onDeleteAddressSuccess(String msg) {

    }

    @Override
    public void onGetAddressList(List<AddressResult> list) {

    }

    @Override
    public void onFaulire(String msg) {

    }

    @Override
    public void getSeachAddressList(List<BaiduResult> list) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REQUEST_CODE && data != null) {
            //TODO 这里获取mapview设置的位置
            location = (BaiduResult) data.getSerializableExtra("bean");
            address.setText(location.getAddress());
        }
    }
}
