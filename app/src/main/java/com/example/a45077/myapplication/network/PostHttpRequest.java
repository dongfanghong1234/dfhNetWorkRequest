package com.example.a45077.myapplication.network;

import java.util.HashMap;

/**
 * Created by 45077 on 2017/12/7.
 */

public class PostHttpRequest {

    private String postUrl;
    private String tag;
    private HashMap<String, String> params = new HashMap<>();
    private HashMap<String, String> headers = new HashMap<>();
    private HashMap<String, String> urlParams = new HashMap<>();
    //    private HashMap<String, String> fileParams = new HashMap<>();
    private int retryCount = 0;

    public int getRetryCount() {
        return retryCount;
    }

    protected PostHttpRequest(PostHttpRequestBuilder builder) {
        this.postUrl = builder.postUrl;
        this.tag = builder.tag;
        this.params = builder.params;
        this.headers = builder.headers;
        this.urlParams = builder.urlParams;
        this.retryCount = builder.retryCount;
    }


    public String getPostUrl() {
        return postUrl;
    }

    public String getTag() {
        return tag;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public HashMap<String, String> getUrlParams() {
        return urlParams;
    }


    @Override
    public String toString() {
        return "PostHttpRequest{" +
                "postUrl='" + postUrl + '\'' +
                ", tag='" + tag + '\'' +
                ", params=" + params +
                ", headers=" + headers +
                ", urlParams=" + urlParams +
                '}';
    }
}
