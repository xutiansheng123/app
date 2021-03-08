package com.ta.xutiansheng.xtsapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class MsgBrodCastRec extends BroadcastReceiver {
    OnCallBack onCallBack;

    public void setOnCallBack(OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: "+intent.getStringExtra("msg"));
        if (onCallBack != null) {
            onCallBack.getjson(intent.getStringExtra("msg"));
        }
    }

    public interface OnCallBack {
        void getjson(String json);
    }
}
