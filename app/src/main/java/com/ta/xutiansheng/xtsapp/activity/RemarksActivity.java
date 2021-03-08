package com.ta.xutiansheng.xtsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.mvp.presenter.BasePresenter;

import butterknife.BindView;

public class RemarksActivity extends BaseMvpActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.top)
    ConstraintLayout top;
    @BindView(R.id.edt_reamrks)
    EditText edtReamrks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ok.setOnClickListener(view -> {
            String remark = edtReamrks.getText().toString();
            Intent intent = new Intent(RemarksActivity.this, CreatOrderActivity.class);
            intent.putExtra("remarks", remark);
            setResult(201, intent);
            finish();
        });
        ivBack.setOnClickListener(view -> {
            finish();
        });
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
        return R.layout.activity_remarks;
    }

    @Override
    protected void initView() {

    }
}