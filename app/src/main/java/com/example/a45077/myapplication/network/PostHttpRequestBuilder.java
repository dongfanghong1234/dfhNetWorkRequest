package com.example.a45077.myapplication.network;



import com.example.a45077.myapplication.network.callBack.BaseCallBack;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by 45077 on 2017/12/7.
 */

public class PostHttpRequestBuilder {
    protected String tag;
    protected String postUrl;
    protected HashMap<String, String> params = new HashMap<>();
    protected HashMap<String, String> headers = new HashMap<>();
    protected HashMap<String, String> urlParams = new HashMap<>();
    protected int retryCount;
//    protected TimeUnit CacheUnit;
//    protected long CacheTime;


    public PostHttpRequestBuilder retryCount(int retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public PostHttpRequestBuilder tag(String tag) {
        this.tag = tag;
        return this;
    }

    public PostHttpRequestBuilder postUrl(String postUrl) {
        this.postUrl = postUrl;
        return this;
    }

    public PostHttpRequestBuilder params(String paramsKey, String paramsValue) {
        params.put(paramsKey, paramsValue);
        return this;
    }

    public PostHttpRequestBuilder headers(String headersKey, String headersValue) {
        headers.put(headersKey, headersValue);
        return this;
    }

    public PostHttpRequestBuilder urlParams(String urlParamsKey, String urlParamsValue) {
        urlParams.put(urlParamsKey, urlParamsValue);
        return this;
    }

    public void execute(BaseCallBack callBack) {
        PostHttpRequest postHttpRequest = new PostHttpRequest(this);
//        Log.i("REQUEST", postHttpRequest.toString());
        HttpManager.getInstance().addPostRequest(callBack, postHttpRequest);
    }

//    public void execute(ProgressDialogCallBack callBack) {
//        PostHttpRequest postHttpRequest = new PostHttpRequest(this);
//        HttpManager.getInstance().addPostRequest(postHttpRequest);
//    }


    @Override
    public String toString() {
        return "PostHttpRequestBuilder{" +
                "tag='" + tag + '\'' +
                ", postUrl='" + postUrl + '\'' +
                ", params=" + params +
                ", headers=" + headers +
                ", urlParams=" + urlParams +
                ", retryCount=" + retryCount +
                '}';
    }
}
