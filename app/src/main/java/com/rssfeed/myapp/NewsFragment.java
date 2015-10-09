package com.rssfeed.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rssfeed.myapp.customadapter.NewsFeedListAdapter;
import com.rssfeed.myapp.utility.AppConstants;
import com.rssfeed.myapp.utility.ConnectionDetector;
import com.rssfeed.myapp.utility.DisplayResults;
import com.rssfeed.myapp.utility.LocalUpdate;
import com.rssfeed.myapp.utility.StringManipulation;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by venkatesanr on 25-05-2015.
 */
public class NewsFragment extends Fragment implements AdapterView.OnItemClickListener{
    private ListView rssfeed;
    private ArrayAdapter adapter;
    private List<NewsFeed> getNewsFeed = new ArrayList<>();
    private List<NewsFeed> myFeedList;
    private ProgressDialog dialog = null;
    private NewsFeedListAdapter customAdapter;
    private LocalUpdate localUpdateff = null;
    private Cursor SelectedSections = null;
    private Uri todoUri , deleteUri= null;
    private boolean isTwoPane = false;
    public NewsFragment(){
        this.localUpdateff = new LocalUpdate(GetDisplay());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rss_feed_display_news,container,false);
        init(view);
        return view;
    }

    /**
     * Method to go For Initialization
     */
    private void init(View sectionView){
        this.rssfeed =  (ListView) sectionView.findViewById(R.id.listView);
        if(sectionView.findViewById(R.id.newsDetailContainer) != null){
            this.isTwoPane = true;
        }
        User createUser = new User();
        createUser.setUsername("testfeed");
        createUser.setUserpassword("testfeed1");
        RssFeed displayRssfeed = new RssFeed() {
            @Override
            public void DisplayRssFeed(Rss rssfeed) {
                String mydesc = null;
                localUpdateff.TogetRssFeed(rssfeed,2);
                EmptyTable();
                localUpdateff.run();
            }

            @Override
            public void onFailure(RetrofitError failure) {
                if(failure.toString().matches("(?i).*failed to connect.*")){
                    Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        };
        ToDisplayDialog();
        ToCheckInternetConnection();
        if(ConnectionDetector.isConnectingToInternet(MyApplication.getAppContext()))
            RestAPI.getInstance().TogetNewsRssFeed(ToGetBase64EncodedString(createUser), displayRssfeed);
    }

    /**
     * Method to Return base64encoded String for Authorization Authentication Challenge
     */
    private String ToGetBase64EncodedString(User myCreatedUser){
        final String userAndPassword = myCreatedUser.getUsername() + ":" + myCreatedUser.getUserpassword();
        return "Basic " + Base64.encodeToString(userAndPassword.getBytes(), Base64.NO_WRAP);
    }

    /**
     * Method to display Dialog
     */
    private void ToDisplayDialog(){
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("loading");
        dialog.setCancelable(false);
        dialog.show();
    }
    /**
     * Method to Check Records
     */
    private void ToCheckRecords(){
        todoUri = null;
        todoUri = Uri.parse("content://com.rssfeed/hindu/news/get");
        this.SelectedSections = MyApplication.getAppContext().getContentResolver().query(todoUri, null,
                null, null, null);
        this.myFeedList = StringManipulation.SelectedCursorResults(SelectedSections);
        this.customAdapter = new NewsFeedListAdapter(getActivity(),R.layout.rss_view_list,this.myFeedList);
        this.rssfeed.setAdapter(this.customAdapter);
        this.rssfeed.setOnItemClickListener(this);
        this.dialog.dismiss();
    }

    /**
     * Method to Return Callbacks to Display the results
     */
    private DisplayResults GetDisplay(){
        DisplayResults displayResults = new DisplayResults() {
            @Override
            public void onSuccess(boolean status) {
                ToCheckRecords();
            }
        };
        return displayResults;
    }

    /**
     * Method to return the Total Number of Records
     */
    private int TotalRecords(){
        todoUri = null;
        todoUri = Uri.parse("content://com.rssfeed/hindu/news/get");
        this.SelectedSections = MyApplication.getAppContext().getContentResolver().query(todoUri, null,
                null, null, null);
        return SelectedSections.getCount();
    }

    /**
     * Method to Check Internet Connection
     */
    private void ToCheckInternetConnection(){
        if(!ConnectionDetector.isConnectingToInternet(MyApplication.getAppContext())){
            Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();
            this.dialog.dismiss();
            if(TotalRecords() > 0)
                ToCheckRecords();
        }
    }

    /**
     * Method to Empty Records in the Table
     */
    private void EmptyTable(){
        this.deleteUri = null;
        this.deleteUri = Uri.parse("content://com.rssfeed/hindu/news/delete");
        MyApplication.getAppContext().getContentResolver().delete(this.deleteUri,null,null);
        this.deleteUri = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final NewsFeed taskfeed = this.myFeedList.get(position);
        Bundle b = taskfeed.toBundle();
        /*Intent myintent = new Intent(getActivity() , NewsViewActivity.class);
        myintent.putExtra(AppConstants.NEWS_TASK,b);
        startActivity(myintent);*/
        if(this.isTwoPane){
            NewsDetailFragment fragment = new NewsDetailFragment();
            fragment.setArguments(b);
            getFragmentManager().beginTransaction().replace(R.id.newsDetailContainer,fragment).commit();
        }else{
            Intent myintent = new Intent(getActivity() , NewsDetailActivity.class);
            myintent.putExtra(AppConstants.NEWS_TASK, b);
            startActivity(myintent);
        }
    }
}
