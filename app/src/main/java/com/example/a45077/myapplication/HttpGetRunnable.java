package com.example.a45077.myapplication;

import android.os.Handler;
import android.os.Process;
import android.util.Log;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by 45077 on 2017/12/7.
 */

public class HttpGetRunnable implements Runnable{

    private GetCallBack callBack;

    private String url;

    public HttpGetRunnable(String url,GetCallBack callBack){
        this.url=url;
        this.callBack=callBack;
    }

    @Override
    public void run() {
        android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        Response response=HttpResponseManager.getInstance().getResponse(url);
        if (response==null){
            return;
        }


        if (response.isSuccessful()){
            try {
                String data=response.body().string();

                callBack.success(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            callBack.fail(response.code()+"");
        }

    }
}
