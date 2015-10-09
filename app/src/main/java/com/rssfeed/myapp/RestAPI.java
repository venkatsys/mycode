package com.rssfeed.myapp;

import android.util.Log;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.SimpleXMLConverter;

/**
 * Created by venkatesanr on 20-05-2015.
 */
public class RestAPI {

    protected static RestAPI mInstance = null;
    public static RestAdapter adapter;
    public static String ENDPOINT = "http://theceweb.thehindu.co.in/";
    private static RssFeedAuthenticationAPI rssFeedAuthenticationAPI = null;

    public RestAPI(){
        this.adapter = new RestAdapter.Builder().setEndpoint(ENDPOINT).setConverter(new SimpleXMLConverter())
                .build();
        /*this.adapter = new RestAdapter.Builder().setEndpoint(ENDPOINT).setConverter(new SimpleXMLConverter())
                .setLogLevel(RestAdapter.LogLevel.FULL).build();*/
        /*this.adapter = new RestAdapter.Builder().setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL).build();*/
    }
    public static RestAPI getInstance(){
        if(mInstance == null)
            mInstance = new RestAPI();
        return mInstance;
    }

    public static void ToGetRSSFeed(String authorizationValue , final RssFeed listener){
        rssFeedAuthenticationAPI = adapter.create(RssFeedAuthenticationAPI.class);
        rssFeedAuthenticationAPI.GetRssFeed(authorizationValue, new Callback<Rss>() {
            @Override
            public void success(Rss rss, Response response) {
                //Log.i("success","rss =>" + rss.getChannel().articleList.toString());
                //Log.i("success","rss =>" + rss.getChannel().articleList.size());
                listener.DisplayRssFeed(rss);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i("Error","error" + retrofitError.toString());
                listener.onFailure(retrofitError);
            }
        });
    }

    public static void TogetNewsRssFeed(String authorizationValue , final RssFeed listener){
        rssFeedAuthenticationAPI = adapter.create(RssFeedAuthenticationAPI.class);
        rssFeedAuthenticationAPI.GetNewsRssFeed(authorizationValue, new Callback<Rss>() {
            @Override
            public void success(Rss rss, Response response) {
                listener.DisplayRssFeed(rss);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onFailure(error);
            }
        });
    }

    public static void TogetOpinionRssFeed(String authorizationValue , final RssFeed listener){
        rssFeedAuthenticationAPI = adapter.create(RssFeedAuthenticationAPI.class);
        rssFeedAuthenticationAPI.GetOpinionRssFeed(authorizationValue, new Callback<Rss>() {
            @Override
            public void success(Rss rss, Response response) {
                listener.DisplayRssFeed(rss);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onFailure(error);
            }
        });
    }


    public static void TogetBusinessRssFeed(String authorizationValue , final RssFeed listener){
        rssFeedAuthenticationAPI = adapter.create(RssFeedAuthenticationAPI.class);
        rssFeedAuthenticationAPI.GetBusinessRssFeed(authorizationValue, new Callback<Rss>() {
            @Override
            public void success(Rss rss, Response response) {
                listener.DisplayRssFeed(rss);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onFailure(error);
            }
        });
    }

    public static void TogetSportRssFeed(String authorizationValue , final RssFeed listener){
        rssFeedAuthenticationAPI = adapter.create(RssFeedAuthenticationAPI.class);
        rssFeedAuthenticationAPI.GetSportRssFeed(authorizationValue, new Callback<Rss>() {
            @Override
            public void success(Rss rss, Response response) {
                listener.DisplayRssFeed(rss);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onFailure(error);
            }
        });
    }


    public static void TogetEntertainmentRssFeed(String authorizationValue , final RssFeed listener){
        rssFeedAuthenticationAPI = adapter.create(RssFeedAuthenticationAPI.class);
        rssFeedAuthenticationAPI.GetEntertainmentRssFeed(authorizationValue, new Callback<Rss>() {
            @Override
            public void success(Rss rss, Response response) {
                listener.DisplayRssFeed(rss);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onFailure(error);
            }
        });
    }
}
