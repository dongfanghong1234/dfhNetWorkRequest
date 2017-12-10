# dfhNetWorkRequest
自娱自乐
 PostHttpRequestBuilder postHttpRequestBuilder = new PostHttpRequestBuilder();
        postHttpRequestBuilder.postUrl("http://v5.pc.duomi.com/search-ajaxsearch-searchal")//地址
                .params("kw", "ss")//键值对
                .params("pi", "1")//
                .params("pz", "1")
                .retryCount(2)//失败重连次数
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
