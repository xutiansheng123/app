package com.ta.xutiansheng.xtsapp.bean;

public class NettyBean {
    private String msg;
    private String uuid;
    //0是消息
    private int type;
    private String destUuid;

    public String getDestUuid() {
        return destUuid;
    }

    public void setDestUuid(String destUuid) {
        this.destUuid = destUuid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
