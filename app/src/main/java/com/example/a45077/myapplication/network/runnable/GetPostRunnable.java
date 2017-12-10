package com.example.a45077.myapplication.network.runnable;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

import com.example.a45077.myapplication.network.HttpManager;
import com.example.a45077.myapplication.network.PostHttpRequest;
import com.example.a45077.myapplication.network.callBack.BaseCallBack;
import com.example.a45077.myapplication.network.status.HttpStatus;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 45077 on 2017/12/7.
 */

public class GetPostRunnable implements Runnable {

    private BaseCallBack baseCallBack;
    //    private ProgressDialogCallBack progressDialogCallBack;
    private PostHttpRequest postHttpRequest;
    private Handler handler = new Handler(Looper.getMainLooper());
    private AtomicInteger atomicInteger;

    public GetPostRunnable(BaseCallBack baseCallBack, PostHttpRequest postHttpRequest) {
        this.baseCallBack = baseCallBack;
        this.postHttpRequest = postHttpRequest;
    }

//    public GetPostRunnable(ProgressDialogCallBack progressDialogCallBack) {
//        this.progressDialogCallBack = progressDialogCallBack;
//    }
//
//    public GetPostRunnable(PostHttpRequest postHttpRequest) {
//        this.postHttpRequest = postHttpRequest;
//    }

    @Override
    public void run() {
        android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        if (postHttpRequest.getRetryCount() != 0) {
            atomicInteger = new AtomicInteger(postHttpRequest.getRetryCount());
        }
        enqueue(baseCallBack);


    }

    private void enqueue(final BaseCallBack baseCallBack) {
        Request.Builder builder = new Request.Builder();
        String postUrl = postHttpRequest.getPostUrl();
        if (postUrl == null || TextUtils.isEmpty(postUrl)) {
            return;
        }
        Log.i("StringBuffer", postUrl);
//        builder.url(postUrl);


        HashMap<String, String> headers = postHttpRequest.getHeaders();
        if (headers.size() > 0) {
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }


        HashMap<String, String> urlParams = postHttpRequest.getUrlParams();
        StringBuffer buffer = new StringBuffer();
        if (urlParams.size() > 0) {
            if (postUrl.endsWith("?")) {

            } else {
                buffer.append("?");
            }

            appendUrlParams(buffer, urlParams);

        }
        builder.url(postUrl + buffer.toString());
        Log.i("URL", postUrl + buffer.toString());


        HashMap<String, String> params = postHttpRequest.getParams();
        if (params.size() > 0) {
            FormBody.Builder encodingBuilder = new FormBody.Builder();
            appendParams(encodingBuilder, params);
            builder.post(encodingBuilder.build());
        }
        Request request = builder.build();

        HttpManager.getInstance().client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
//                Log.i("TAH",e.getMessage());
//                baseCallBack.onFail(e.getMessage());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        baseCallBack.onFail(e.getMessage());
                        if (atomicInteger.get() != 0) {
                            atomicInteger.getAndDecrement();
                            enqueue(baseCallBack);
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                if (response.isSuccessful()) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                baseCallBack.onSuccess(response.body().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            baseCallBack.onFail(HttpStatus.getValue(response.code()));
                            Log.i("ERROR",response.code()+"");
                            if (atomicInteger.get() != 0) {
                                atomicInteger.getAndDecrement();
                                enqueue(baseCallBack);
                            }
                        }
                    });
                }
            }
        });


    }

    private void appendParams(FormBody.Builder builder, HashMap<String, String> params) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }

    }

    private void appendUrlParams(StringBuffer buffer, HashMap<String, String> urlParams) {
        int count = 0;
        try {
            for (Map.Entry<String, String> entry : urlParams.entrySet()) {
                buffer.append(URLEncoder.encode(entry.getKey(), "utf-8")).append("=").
                        append(URLEncoder.encode(entry.getValue(), "utf-8"));
                if (count != urlParams.size() - 1) {
                    buffer.append("&");
                }
                count++;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
