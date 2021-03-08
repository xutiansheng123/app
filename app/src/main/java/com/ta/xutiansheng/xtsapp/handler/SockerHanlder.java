package com.ta.xutiansheng.xtsapp.handler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static android.content.ContentValues.TAG;

public class SockerHanlder extends SimpleChannelInboundHandler<String> {
   public static Context context;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        if (context != null) {
            Intent intent = new Intent("com.xutiansheng.app");
            intent.putExtra("msg", s);
            context.sendBroadcast(intent);
        }
    }
}
