package com.ta.xutiansheng.xtsapp.api.bean;

import java.io.Serializable;

/**
 * @author xutiansheng
 */
public class BaseResponse<D> implements Serializable {

    public int code ;
    public String msg = "";
    public D data;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int status) {
        this.code = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public D getResult() {
        return data;
    }

    public void setResult(D result) {
        this.data = result;
    }
}
