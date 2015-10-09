package com.rssfeed.myapp.utility;

import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

import com.rssfeed.myapp.MyApplication;
import com.rssfeed.myapp.Rss;
import com.rssfeed.myapp.contentprovider.DatabaseHelper;

/**
 * Created by venkatesanr on 25-05-2015.
 */
public class LocalUpdate implements Runnable{
    private Rss myfeed;
    private String mydesc = null;
    private ContentValues insertSectionContentValues = null;
    private Uri todoUri,deleteUri = null;
    private DisplayResults callback;
    private int s = -1;

    public LocalUpdate(DisplayResults listener){
        this.callback = listener;
    }
    public void TogetRssFeed(Rss rssfeed , int pk){
        this.myfeed = rssfeed;
        this.s = pk;
    }
    private void BulkInsert(){
        Log.i("TotalRecords","" + this.myfeed.getChannel().articleList.size());
        this.insertSectionContentValues = new ContentValues();
        for(int i = 0 ; i < myfeed.getChannel().articleList.size() ; i++){
            mydesc = StringManipulation.toGetImageURLFromDesc(myfeed.getChannel().articleList.get(i).description);
            StringManipulation.ParseSpecialCharacters(myfeed.getChannel().articleList.get(i).title);
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONSID,String.valueOf(StringManipulation.GetRandomNumber()));
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONTITLE,StringManipulation.ParseSpecialCharacters(myfeed.getChannel().articleList.get(i).title));
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONDESCRIPTION, myfeed.getChannel().articleList.get(i).description);
            if( (null == StringManipulation.ParseDescriptionSpecialCharacters(mydesc)) || (StringManipulation.ParseDescriptionSpecialCharacters(mydesc).contentEquals("emptysrc")))
                this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONLINK,StringManipulation.toGetImageURLFromDesc(myfeed.getChannel().articleList.get(i).description));
            else
                this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONLINK,StringManipulation.ParseDescriptionSpecialCharacters(mydesc));

            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONAUTHOR,myfeed.getChannel().articleList.get(i).author);
            this.insertSectionContentValues.put(DatabaseHelper.COLUMN_SECTIONDATE, myfeed.getChannel().articleList.get(i).pubDate);
            if(AppConstants.SECTION_HOME == this.s)
                this.todoUri = Uri.parse("content://com.rssfeed/hindu/section/add");
            else if(AppConstants.SECTION_NEWS == this.s)
                this.todoUri = Uri.parse("content://com.rssfeed/hindu/news/add");
            else if(AppConstants.SECTION_OPINION == this.s)
                this.todoUri = Uri.parse("content://com.rssfeed/hindu/opinion/add");
            else if(AppConstants.SECTION_BUSINESS == this.s)
                this.todoUri = Uri.parse("content://com.rssfeed/hindu/business/add");
            else if(AppConstants.SECTION_SPORT == this.s)
                this.todoUri = Uri.parse("content://com.rssfeed/hindu/sport/add");
            else if(AppConstants.SECTION_ENTERTAINMENT == this.s)
                this.todoUri = Uri.parse("content://com.rssfeed/hindu/entertainment/add");
            MyApplication.getAppContext().getContentResolver().insert(this.todoUri, insertSectionContentValues);
        }
        this.callback.onSuccess(true);
        this.todoUri = null;
        this.insertSectionContentValues.clear();
    }

    @Override
    public void run() {
        this.BulkInsert();
    }
}
