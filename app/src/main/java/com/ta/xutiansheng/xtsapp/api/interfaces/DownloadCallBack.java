package com.ta.xutiansheng.xtsapp.api.interfaces;

/**
 * @author xutiansheng
 * 文件下载回调
 */
public interface
DownloadCallBack {
    void onProgress(int progress);

    void onCompleted();

    void onError(String msg);
}
