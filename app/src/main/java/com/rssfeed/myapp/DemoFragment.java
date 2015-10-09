package com.rssfeed.myapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by venkatesan on 22-05-2015.
 */
public class DemoFragment extends Fragment {
    private String color, name;
    public DemoFragment(){}

    /*public static DemoFragment newInstance(String name, String color)
    {
        DemoFragment fragment = new DemoFragment();
        Bundle args = new Bundle();
        args.putString("color", color);
        fragment.setArguments(args);
        return fragment;
    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        return rootView;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
        {
            //fragment recreated from orientation change or activity recreated by ActivityManager
            color = savedInstanceState.getString("color");
        } else
        {
            // for the first time , when fragment created, it should have argument passed by newInstance(String name, String color)
            Bundle args = getArguments();
            color = args.getString("color");
        }
        Log.d("ddddddddd", name + ": onCreate");
    }*/
}
