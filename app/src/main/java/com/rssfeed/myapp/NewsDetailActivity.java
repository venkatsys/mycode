package com.rssfeed.myapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.rssfeed.myapp.utility.AppConstants;

/**
 * Created by venkatesanr on 27-05-2015.
 */
public class NewsDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        if(savedInstanceState == null) {
            NewsDetailFragment fragment = new NewsDetailFragment();
            Bundle b = getIntent().getBundleExtra(AppConstants.NEWS_TASK);
            fragment.setArguments(b);
            //getFragmentManager().beginTransaction().add(R.id.detailContainer,fragment).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.detailContainer,fragment).commit();
        }
    }
}
