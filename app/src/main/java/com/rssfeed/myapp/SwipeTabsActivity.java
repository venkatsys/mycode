package com.rssfeed.myapp;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by venkatesanr on 22-05-2015.
 */
public class SwipeTabsActivity extends AppCompatActivity {

    NewsFeedSectionsPagerAdapter mNewsfeedSectionsAdapter;
    ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_tabs);

        this.mNewsfeedSectionsAdapter = new NewsFeedSectionsPagerAdapter(getSupportFragmentManager());
        this.mViewPager = (ViewPager) this.findViewById(R.id.pager);
        this.mViewPager.setOffscreenPageLimit(2);
        this.mViewPager.setAdapter(this.mNewsfeedSectionsAdapter);

    }


}
