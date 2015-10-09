package com.rssfeed.myapp.utility;

import android.database.Cursor;
import android.util.Log;

import com.rssfeed.myapp.NewsFeed;
import com.rssfeed.myapp.contentprovider.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by venkatesan on 21-05-2015.
 */
public class StringManipulation {
    /**
     * Method to Get Image URL from the Description Tag
     */
    public static String toGetImageURLFromDesc(String Desc) {
        String img = "";
        try {
            if (!Desc.contentEquals("")) {
                img = Desc;
                String cleanUp = img.substring(0, img.indexOf(">") + 1);
                img = img.substring(img.indexOf("src=") + 5);
                //Log.i("IMAGE", "imgvvvvvvvvvvv=>" + img);
                int indexOf = img.indexOf("'");
                if (indexOf == -1) {
                    indexOf = img.indexOf("\"");
                }
                img = img.substring(0, indexOf);
               // Log.i("IMAGE", "index=>" + img.indexOf("http://"));
                if(img.indexOf("http://") !=-1)
                    //Log.i("IMAGE", "URL=>" + img);
                    return img;
                else
                    //Log.i("IMAGE", "URL=>" + "No Image");
                return "emptysrc";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to Find Replacing Special Characters
     */
    public static String ParseSpecialCharacters(String title){
        //title = "J&amp;K attacks: Don't cover anti-terror operations live, TV channels told";
        //Log.i("my","found=>" + title);
        title = title.replaceAll("[^&amp;&gt;&lt;&quot;&apos;a-zA-Z,0-9: ]", "");
        //Log.i("my", "found=>" + title);
        //title = title.replace(" ", "");
        if(title.matches("(?i).*&amp;.*")){
            //Log.i("my","foundeeeeeeeee");
            title = title.replace("&amp;","&");
            Log.i("my","found=>" + title);
        }
        return title;
        /*String dateTime = "J&amp;K attacks: Don't cover anti-terror operations live, TV channels told";
        dateTime = dateTime.replace("&amp;", "&");
        Log.i("my","found=>" + dateTime);*/
    }

    /**
     *Method to get Image Url From the given String
     */
    public static String ParseDescriptionSpecialCharacters(String desc){
        if( (null == desc) || (desc.contentEquals("emptysrc"))){
            return desc;
        }else{
            if(desc.indexOf("alt")!= -1) {
                return desc.substring(0, desc.indexOf("alt") - 2);
            }
        }
        return null;
    }

    /**
     * Method to Return the Database Results
     */
    public static List<NewsFeed> SelectedCursorResults(Cursor data){
        List<NewsFeed> getNewsFeed = new ArrayList<>();
        String mydesc = null;
        while(data.moveToNext()){
            int columnID = data.getColumnIndex(DatabaseHelper.COLUMN_SECTIONID);
            int title = data.getColumnIndex(DatabaseHelper.COLUMN_SECTIONTITLE);
            int description = data.getColumnIndex(DatabaseHelper.COLUMN_SECTIONDESCRIPTION);
            int link = data.getColumnIndex(DatabaseHelper.COLUMN_SECTIONLINK);
            int author = data.getColumnIndex(DatabaseHelper.COLUMN_SECTIONAUTHOR);
            int date = data.getColumnIndex(DatabaseHelper.COLUMN_SECTIONDATE);

            int id = data.getInt(columnID);
            mydesc = StringManipulation.toGetImageURLFromDesc(data.getString(description));
            //Log.i("oooooooooo",">>>>>>>>>>>>>>>>>>>>>" + mydesc);
            StringManipulation.ParseSpecialCharacters(data.getString(title));
            NewsFeed newsFeed = new NewsFeed();
            newsFeed.setSectionID(id);
            newsFeed.setTitle(StringManipulation.ParseSpecialCharacters(data.getString(title)));
            newsFeed.setDescription(data.getString(description));
            if( (null == StringManipulation.ParseDescriptionSpecialCharacters(mydesc)) || (StringManipulation.ParseDescriptionSpecialCharacters(mydesc).contentEquals("emptysrc"))) {
                newsFeed.setNewsSrc(StringManipulation.toGetImageURLFromDesc(data.getString(description)));
                //Log.i("oooooooooo", ">>>>>>>>>>>>>>>>>>>>>" + StringManipulation.toGetImageURLFromDesc(data.getString(description)));
            }
            else {
                newsFeed.setNewsSrc(StringManipulation.ParseDescriptionSpecialCharacters(mydesc));
            }
            newsFeed.setAuthor(data.getString(author));
            newsFeed.setPubDate(data.getString(date));
            getNewsFeed.add(newsFeed);
        }
        data.close();
        return getNewsFeed;
    }

    /**
     * Method to return Random Numner
     */
    public static int GetRandomNumber(){
        Random r = new Random();
        int Low = 10;
        int High = 100;
        Log.i("Random","r.nextInt(High-Low) + Low" + r.nextInt(High-Low) + Low);
        return r.nextInt(High-Low) + Low;
    }
}
