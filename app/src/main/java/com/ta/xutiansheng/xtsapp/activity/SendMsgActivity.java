package com.ta.xutiansheng.xtsapp.activity;

import android.content.IntentFilter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.adapter.MessageAdapter;
import com.ta.xutiansheng.xtsapp.api.interceptor.GlobleSettingData;
import com.ta.xutiansheng.xtsapp.bean.MessageBean;
import com.ta.xutiansheng.xtsapp.bean.NettyBean;
import com.ta.xutiansheng.xtsapp.msg.MessageContanct;
import com.ta.xutiansheng.xtsapp.msg.MessagePresent;
import com.ta.xutiansheng.xtsapp.mvp.BaseMvpActivity;
import com.ta.xutiansheng.xtsapp.service.NettyService;
import com.ta.xutiansheng.xtsapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class SendMsgActivity extends BaseMvpActivity<MessagePresent> implements MessageContanct.MessageView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.top)
    ConstraintLayout top;
    @BindView(R.id.edt_msg)
    EditText edtMsg;
    @BindView(R.id.sendmsg)
    TextView sendmsg;
    @BindView(R.id.bottom)
    ConstraintLayout bottom;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private String destUuid;
    private MsgBrodCastRec msgBrodCastRec;
    private List<MessageBean> list;
    private MessageAdapter adapter;
    public static   boolean isHide =true;
    @Override
    protected Snackbar createSnackbar() {
        return null;
    }

    @Override
    protected MessagePresent createPresenter() {
        return new MessagePresent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isHide=false;
        ivBack.setOnClickListener(v -> {
            finish();
        });
        destUuid = getIntent().getStringExtra("destUuid");
        initData();
        sendmsg.setOnClickListener(v -> {
            NettyBean bean = new NettyBean();
            bean.setDestUuid(destUuid);
            bean.setUuid(GlobleSettingData.getInstance().getAuthInfo().getUuid());
            bean.setMsg(edtMsg.getText().toString());
            bean.setType(0);
            NettyService.getInstance().sendDataToserver(new Gson().toJson(bean).getBytes());
            MessageBean messageBean = new MessageBean();
            messageBean.setCreateTime(String.valueOf(System.currentTimeMillis()));
            messageBean.setMsg(edtMsg.getText().toString());
            messageBean.setType(0);
            messageBean.setUuid(GlobleSettingData.getInstance().getAuthInfo().getUuid());
            list.add(messageBean);
            adapter.notifyDataSetChanged();
            recycler.post(() -> {
                recycler.scrollToPosition(adapter.getItemCount() - 1);
            });
            edtMsg.setText("");
        });
        msgBrodCastRec.setOnCallBack(msg -> {
            MessageBean messageBean = new Gson().fromJson(msg, MessageBean.class);
            list.add(messageBean);
            adapter.notifyDataSetChanged();
            recycler.post(() -> {
                recycler.scrollToPosition(adapter.getItemCount() - 1);
            });
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_msgactivity;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adapter = new MessageAdapter(this, list);
        adapter.setHasStableIds(true);
        recycler.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xutiansheng.app");
        msgBrodCastRec = new MsgBrodCastRec();
        registerReceiver(msgBrodCastRec, intentFilter);
    }

    @Override
    protected void onPause() {
        isHide=true;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(msgBrodCastRec);
    }

    @Override
    public void onGetMessage(List<MessageBean> data) {
        list.clear();
        list.addAll(data);
        adapter.notifyDataSetChanged();
        recycler.scrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    protected void initData() {
        super.initData();
        Map<String, String> map = new HashMap<>();
        map.put("uuid", GlobleSettingData.getInstance().getAuthInfo().getUuid());
        map.put("uuid2", destUuid);
        mPresenter.onGetMessagePresent(map);
    }

    @Override
    public void onFauild(String msg) {
        ToastUtil.MakeToast(this, msg);
    }
}
