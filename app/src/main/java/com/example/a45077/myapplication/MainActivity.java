package com.example.a45077.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.a45077.myapplication.network.PostHttpRequest;
import com.example.a45077.myapplication.network.PostHttpRequestBuilder;
import com.example.a45077.myapplication.network.callBack.BaseCallBack;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
//        HttpManager.getInstance().addGetRequest("https://www.yeshen.com/", new GetCallBack() {
//            @Override
//            public void success(String data) {
//                Log.i("TAG",data);
//            }
//
//            @Override
//            public void fail(String error) {
//                Log.i("TAG",error);
//            }
//        });

//        PostHttpRequest.PostHttpRequestBuilder postHttpRequestBuilder = new PostHttpRequest.PostHttpRequestBuilder();
//        postHttpRequestBuilder.tag("TAG").urlParams("urlKey", "urlValue")
//                .headers("headersKey", "headersValue")
//                .params("paramsKey", "paramsValue")
//                .postUrl("url")
//                .execute(new GetCallBack() {
//                    @Override
//                    public void success(String data) {
//
//                    }
//
//                    @Override
//                    public void fail(String error) {
//
//                    }
//                });
//
//        Log.i("REQUEST", postHttpRequestBuilder.toString());

        PostHttpRequestBuilder postHttpRequestBuilder = new PostHttpRequestBuilder();
        postHttpRequestBuilder.postUrl("http://v5.pc.duomi.com/search-ajaxsearch-searchal")
                .urlParams("kw", "ss")
                .urlParams("pi", "1")
                .urlParams("pz", "1")
                .retryCount(2)
                .execute(new BaseCallBack() {
                    @Override
                    public void onSuccess(String data) {
//                        Log.i("TAG",data);
                        textView.setText(data);
                    }

                    @Override
                    public void onFail(String error) {
                        textView.setText(error);
                    }
                });

//        postHttpRequestBuilder.postUrl("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?")
//                .params("tel", "18645237576")
//                .execute(new BaseCallBack() {
//                    @Override
//                    public void onSuccess(String data) {
//                        Log.i("UIDATA", data);
//                        textView.setText(data);
//                    }
//
//                    @Override
//                    public void onFail(String error) {
//
//                    }
//                });


//        Log.i("REQUEST", postHttpRequestBuilder.toString());
    }
}
