package com.ta.xutiansheng.xtsapp.api.rx;

/**
 * @author xutiansheng
 * 全局请求错误
 */
public class ExceptionEvent {

    private String eventCode;
    private String msg;

    public ExceptionEvent(String eventCode, String msg) {
        this.eventCode = eventCode;
        this.msg = msg;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
