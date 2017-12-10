package com.example.a45077.myapplication;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 45077 on 2017/12/7.
 */

public class HttpResponseManager {

    private OkHttpClient client;

    private static final HttpResponseManager HTTP_RESPONSE_MANAGER = new HttpResponseManager();

    public static HttpResponseManager getInstance() {
        return HTTP_RESPONSE_MANAGER;
    }

    private HttpResponseManager() {
        client = new OkHttpClient();
    }

    public Response getResponse(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            Log.i("RESPONSE",response.code()+"");
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
