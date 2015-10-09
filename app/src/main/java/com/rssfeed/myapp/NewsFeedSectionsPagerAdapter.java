package com.rssfeed.myapp;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by venkatesanr on 22-05-2015.
 */
public class NewsFeedSectionsPagerAdapter extends FragmentPagerAdapter{

    public NewsFeedSectionsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       switch (position){
            case 0:
                Fragment sectionFragment = new SectionFragment();
                return sectionFragment;
           case 1:
               Fragment newsFragment = new NewsFragment();
               return newsFragment;
           case 2:
               Fragment opinionFragment = new OpinionFragment();
               return opinionFragment;
           case 3:
               Fragment businessFragment = new BusinessFragment();
               return businessFragment;
           case 4:
               Fragment sportFragment = new SportFragment();
               return sportFragment;
           case 5:
               Fragment entertainmentFragment = new EntertainmentFragment();
               return entertainmentFragment;
            default:
                Fragment fragment = new DemoFragment();
                return fragment;
        }
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "HOME";
            case 1:
                return "NEWS";
            case 2:
                return "OPINION";
            case 3:
                return "BUSINESS";
            case 4:
                return "SPORT";
            case 5:
                return "ENTERTAINMENT";
        }
        return null;
    }
}
