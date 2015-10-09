package com.rssfeed.myapp;

import android.graphics.Bitmap;
import android.os.Bundle;

/**
 * Created by venkatesan on 21-05-2015.
 */
public class NewsFeed {

    public static final String SECTITLE = "title";
    public static  final String SECAUTHOR= "author";
    public static  final String SECDESCRIPTION= "desc";
    public static  final String SECPUBDATE= "pubdate";
    public static  final String SECLINK= "link";
    public static  final String SECBITMAP= "photomap";

    private int sectionID;
    private String title;
    private String author;
    private String description;
    private String pubDate;
    private String newsSrc;
    private Bitmap bitmap;

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getNewsSrc() {
        return newsSrc;
    }

    public void setNewsSrc(String newsSrc) {
        this.newsSrc = newsSrc;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public NewsFeed(){}
    public NewsFeed(Bundle b){
        if(b!= null){
            this.title = b.getString(SECTITLE);
            this.author = b.getString(SECAUTHOR);
            this.description = b.getString(SECDESCRIPTION);
            this.pubDate = b.getString(SECPUBDATE);
            this.newsSrc = b.getString(SECLINK);
            //this.bitmap = b.getParcelable(SECBITMAP);
        }
    }
    //Package Data between Activities
    public Bundle toBundle(){
        Bundle b = new Bundle();
        b.putString(SECTITLE , this.title);
        b.putString(SECAUTHOR , this.author);
        b.putString(SECDESCRIPTION , this.description);
        b.putString(SECPUBDATE , this.pubDate);
        b.putString(SECLINK , this.newsSrc);
        //b.putParcelable(SECBITMAP , this.bitmap);
        return b;
    }
    @Override
    public String toString() {
        /*return "NewsFeed{" +
                "title='" + title + '\'' +
                '}';*/
        return  this.title;
    }
}
