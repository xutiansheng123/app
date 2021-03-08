package com.ta.xutiansheng.xtsapp.util;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.UUID;

public class OtherUtil {
    public static String getUUid(Context context) {
        String androidid = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
        StringBuilder res1 = new StringBuilder();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface nif : Collections.list(networkInterfaces)) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return null;
                }
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return androidid + res1.toString().replaceAll(":","");

    }

    public static String exeshell(String cmd) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec(cmd);
        InputStream is = exec.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while (null != (line = br.readLine())) {
            Log.e("fgtian", line);
        }

        try {
            exec.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return line;
    }
}
