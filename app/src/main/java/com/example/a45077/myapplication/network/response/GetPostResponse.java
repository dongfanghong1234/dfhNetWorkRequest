package com.example.a45077.myapplication.network.response;

import android.text.TextUtils;
import android.util.Log;

import com.example.a45077.myapplication.HttpResponseManager;
import com.example.a45077.myapplication.network.PostHttpRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 45077 on 2017/12/7.
 */

public class GetPostResponse {
    private OkHttpClient client;

    private GetPostResponse() {
        client = new OkHttpClient();
    }

    private static volatile GetPostResponse instance;

    public static GetPostResponse getInstance() {
        synchronized (GetPostResponse.class) {
            if (instance == null) {
                instance = new GetPostResponse();
            }
        }
        return instance;
    }

    public Response getPostResponse(PostHttpRequest postHttpRequest) {
        Request.Builder builder = new Request.Builder();
        String postUrl = postHttpRequest.getPostUrl();
        if (postUrl == null || TextUtils.isEmpty(postUrl)) {
            return null;
        }
        Log.i("StringBuffer",postUrl);
//        builder.url(postUrl);


        HashMap<String, String> headers = postHttpRequest.getHeaders();
        if (headers.size() > 0) {
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }


        HashMap<String, String> params = postHttpRequest.getParams();
        StringBuffer buffer = new StringBuffer();
        if (params.size() > 0) {
            if (postUrl.endsWith("?")){

            }else {
                buffer.append("?");
            }

            int count = 0;
            try {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    buffer.append(URLEncoder.encode(entry.getKey(), "utf-8")).append("=").
                            append(URLEncoder.encode(entry.getValue(), "utf-8"));
                    if (count != params.size() - 1) {
                        buffer.append("&");
                    }
                    count++;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Log.i("StringBuffer",buffer.toString());
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), buffer.toString().getBytes());
//        builder.post(requestBody).build();
        builder.url(postUrl+buffer.toString());
        Request request = builder.build();
        Response response = null;

//        try {
//            response = client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });

        return response;

//        HashMap<String, String> urlParams = postHttpRequest.getUrlParams();
//        if (urlParams.size() > 0) {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(postUrl);
//            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
//            while (iterator.hasNext()) {
//                stringBuilder.append("");
//            }
//        }

    }

}
