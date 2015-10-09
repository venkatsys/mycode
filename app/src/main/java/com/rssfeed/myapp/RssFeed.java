package com.rssfeed.myapp;

import retrofit.RetrofitError;

/**
 * Created by venkatesan on 20-05-2015.
 */
public interface RssFeed {
    void DisplayRssFeed(Rss rssfeed);
    void onFailure(RetrofitError retrofitError);
}
