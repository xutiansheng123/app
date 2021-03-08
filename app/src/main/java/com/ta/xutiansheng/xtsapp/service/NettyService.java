package com.ta.xutiansheng.xtsapp.service;

import android.util.Log;

import com.google.gson.Gson;
import com.ta.xutiansheng.xtsapp.api.interceptor.GlobleSettingData;
import com.ta.xutiansheng.xtsapp.bean.NettyBean;
import com.ta.xutiansheng.xtsapp.handler.SockerHanlder;
import com.ta.xutiansheng.xtsapp.mvp.AppConstants;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import static android.content.ContentValues.TAG;

public class NettyService implements Runnable {
    private ChannelFuture channelFuture;
    private static final Integer SELLP_TIME = 2000;
    public static NettyService getInstance;
    private Bootstrap bootstrap;

    public NettyService() {
        bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(new NioEventLoopGroup());
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_TIMEOUT, SELLP_TIME);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                socketChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                socketChannel.pipeline().addLast("handler", new SockerHanlder());
            }
        });
    }

    public static NettyService getInstance() {
        synchronized (NettyService.class) {
            if (getInstance == null) {
                synchronized (NettyService.class) {
                    if (getInstance == null) {
                        getInstance = new NettyService();
                    }
                }
            }
        }
        return getInstance;
    }

    @Override
    public void run() {
        channelFuture = bootstrap.connect(AppConstants.NETTYIP, AppConstants.PORT);
        try {
            channelFuture.addListener((ChannelFutureListener) channelFuture -> {
                if (channelFuture.isSuccess()) {
                    Log.d(TAG, "operationComplete: 连接成功");
                    NettyBean bean = new NettyBean();
                    bean.setUuid(GlobleSettingData.getInstance().getAuthInfo().getUuid());
                    bean.setMsg("绑定");
                    bean.setType(-1);
                    sendDataToserver(new Gson().toJson(bean).getBytes());
                } else {
                    Log.d(TAG, "operationComplete: 连接失败");
                }
            }).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void sendDataToserver(byte[] sendBytes) {
        if (sendBytes != null && sendBytes.length > 0) {
            if (channelFuture != null) {
                //客户端往服务端发送数据
                channelFuture.channel().writeAndFlush(Unpooled.buffer().writeBytes(sendBytes));
            }
        }
    }
    public void reConnection(){
        bootstrap.connect("192.168.244.5", AppConstants.PORT);
    }

}
