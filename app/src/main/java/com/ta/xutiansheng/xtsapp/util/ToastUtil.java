package com.ta.xutiansheng.xtsapp.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void MakeToast(Context context,String msg){
        ((Activity)context).runOnUiThread(()->{
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        });
    }
}
