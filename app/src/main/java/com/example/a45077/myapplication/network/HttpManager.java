package com.example.a45077.myapplication.network;

import android.support.annotation.NonNull;

import com.example.a45077.myapplication.network.callBack.BaseCallBack;
import com.example.a45077.myapplication.network.runnable.GetPostRunnable;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.OkHttpClient;


/**
 * Created by 45077 on 2017/12/7.
 */

public class HttpManager {

    private final static int MAX_THREAD_SIZE = 10;
    private final static int CORE_POOL_SIZE = 2;

    public OkHttpClient client;

    private ThreadPoolExecutor threadPoolExecutor;

    private HttpManager() {
        client = new OkHttpClient();
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_THREAD_SIZE, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
            private AtomicInteger atomicInteger = new AtomicInteger(1);

            @Override
            public Thread newThread(@NonNull Runnable runnable) {
                Thread thread = new Thread(runnable, "THREAD" + atomicInteger.getAndIncrement());
                return thread;
            }
        });
    }

    private static volatile HttpManager instance;

    public static HttpManager getInstance() {
        synchronized (HttpManager.class) {
            if (instance == null) {
                instance = new HttpManager();
            }
        }

        return instance;
    }


    public void addPostRequest(BaseCallBack callBack, PostHttpRequest builder) {
        threadPoolExecutor.submit(new GetPostRunnable(callBack, builder));
    }
}
