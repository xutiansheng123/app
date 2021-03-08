package com.ta.xutiansheng.xtsapp.activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.R;
import com.ta.xutiansheng.xtsapp.api.interceptor.GlobleSettingData;
import com.ta.xutiansheng.xtsapp.bean.MessageBean;
import com.ta.xutiansheng.xtsapp.bean.NettyBean;
import com.ta.xutiansheng.xtsapp.handler.SockerHanlder;
import com.ta.xutiansheng.xtsapp.mvp.AppConstants;
import com.ta.xutiansheng.xtsapp.service.NettyService;
import com.ta.xutiansheng.xtsapp.util.ShardPrenUtil;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import static android.content.ContentValues.TAG;

public class Myservice extends Service {
    private static final int NO_1 = 0x1;
    private static final String CHANNEL_ONE_NAME = "xtsapp";
    private static int CHANNEL_ONE_ID = 0;
    MsgBrodCastRec msgBrodCastRec;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        msgBrodCastRec = new MsgBrodCastRec();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.xutiansheng.app");
        registerReceiver(msgBrodCastRec, intentFilter);
        msgBrodCastRec.setOnCallBack(json -> {
            if (SendMsgActivity.isHide) {
                MessageBean bean = new Gson().fromJson(json, MessageBean.class);
                Intent intent = new Intent(this, UserListActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.icon)
                        .setContentTitle("你有一条消息")
                        .setContentText(bean.getMsg())
                        .setAutoCancel(true)
                        .setContentIntent(pi);
                builder.setNumber(CHANNEL_ONE_ID);
                CHANNEL_ONE_ID++;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    //修改安卓8.1以上系统报错
                    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ONE_ID + "", CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_MIN);
                    notificationChannel.enableLights(false);//如果使用中的设备支持通知灯，则说明此通知通道是否应显示灯
                    notificationChannel.setShowBadge(false);//是否显示角标
                    notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.createNotificationChannel(notificationChannel);
                    builder.setChannelId(CHANNEL_ONE_ID + "");
                }
                Notification notification = builder.build();
                notification.defaults = Notification.DEFAULT_SOUND;
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    manager.notify(Integer.parseInt(notification.getChannelId()), notification);
                }
            }

        });
        try {
            NettyService nettyService = NettyService.getInstance();
            nettyService.run();
            SockerHanlder.context = getApplicationContext();
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    while (true) {
                        try {
                            Thread.sleep(60000);
                            NettyBean bean = new NettyBean();
                            bean.setUuid(GlobleSettingData.getInstance().getAuthInfo().getUuid());
                            bean.setMsg("绑定");
                            bean.setType(-1);
                            NettyService.getInstance().sendDataToserver(new Gson().toJson(bean).getBytes());
                            getLocation();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void onDestroy() {
        unregisterReceiver(msgBrodCastRec);
        super.onDestroy();
    }
}
