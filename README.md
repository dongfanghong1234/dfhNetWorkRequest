# dfhNetWorkRequest
自娱自乐
 PostHttpRequestBuilder postHttpRequestBuilder = new PostHttpRequestBuilder();
 postHttpRequestBuilder.postUrl("http://v5.pc.duomi.com/search-ajaxsearch-searchal")
  .params("kw", "ss")
  .params("pi", "1")
  .params("pz", "1")
  .retryCount(2)
