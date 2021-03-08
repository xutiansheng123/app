package com.ta.xutiansheng.xtsapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class ShardPrenUtil {
    public static final String ALTITUDE = "altitude";
    public static final String LATITUDE = "latitude";
    public static final String ADDRESSID = "addressid";
    private static final String SPNAME = "xtsapp";
    private SharedPreferences sharedPreferences;
    private static Context ctx;
    private static ShardPrenUtil shardPrenUtil;
    public static final String UserName="USNAME";
    public static ShardPrenUtil getIntance(Context context) {
        if (ctx != null) {
            ctx = context;
            return new ShardPrenUtil(context);
        } else {
            if (shardPrenUtil == null) {
                synchronized (ShardPrenUtil.class) {
                    if (shardPrenUtil == null) {
                        shardPrenUtil = new ShardPrenUtil(context);
                    }
                }
            }
        }
        return shardPrenUtil;
    }

    public ShardPrenUtil(Context context) {
        ctx= context;
        sharedPreferences = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
    }

    public void SetParam(String key, String value) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        Log.d("tag", "SetParam: "+value);
        edit.apply();
    }

    public Object GetParam(String key) {
        String value =sharedPreferences.getString(key, "-1");
        Log.d(TAG, "GetParam: "+value);
        return value;
    }
}
