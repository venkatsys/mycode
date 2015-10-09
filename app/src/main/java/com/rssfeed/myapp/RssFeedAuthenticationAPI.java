package com.rssfeed.myapp;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

/**
 * Created by venkatesan on 20-05-2015.
 */
public interface RssFeedAuthenticationAPI {
    /**
     * Method to Get the categories
     */
    @GET("/?service=feeder")
    //@GET("/news/?service=feeder")
    //@GET("/Opinion/?service=feeder")
    //@GET("/business/?service=feeder")
    //@GET("/sport/?service=feeder")
    //@GET("/entertainment/?service=feeder")
    public void GetRssFeed(@Header("Authorization") String authKey, Callback<Rss> cb);

    @GET("/news/?service=feeder")
    public void GetNewsRssFeed(@Header("Authorization") String authKey, Callback<Rss> cb);

    @GET("/Opinion/?service=feeder")
    public void GetOpinionRssFeed(@Header("Authorization") String authKey, Callback<Rss> cb);

    @GET("/business/?service=feeder")
    public void GetBusinessRssFeed(@Header("Authorization") String authKey, Callback<Rss> cb);

    @GET("/sport/?service=feeder")
    public void GetSportRssFeed(@Header("Authorization") String authKey, Callback<Rss> cb);

    @GET("/entertainment/?service=feeder")
    public void GetEntertainmentRssFeed(@Header("Authorization") String authKey, Callback<Rss> cb);
}
