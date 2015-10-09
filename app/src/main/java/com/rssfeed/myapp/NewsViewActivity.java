package com.rssfeed.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.rssfeed.myapp.utility.AppConstants;
import com.squareup.picasso.Picasso;

/**
 * Created by venkatesanr on 25-05-2015.
 */
public class NewsViewActivity extends Activity {

    TextView sectionName, sectionAuthor, sectionPubdate;
    ImageView sectionPhoto;

    private NewsFeed myFeedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_view);

        if(savedInstanceState == null){
            Bundle b = getIntent().getBundleExtra(AppConstants.NEWS_TASK);
            this.myFeedList = new NewsFeed(b);
        }
        initVariables();
    }

    private void initVariables(){
        sectionPhoto = (ImageView) this.findViewById(R.id.section_unique_src);
        sectionName = (TextView) this.findViewById(R.id.section_unique_title);
        sectionAuthor = (TextView) this.findViewById(R.id.section_unique_author);
        sectionPubdate = (TextView) this.findViewById(R.id.section_unique_pubdate);
        sectionName.setText(this.myFeedList.getTitle());
        sectionAuthor.setText(this.myFeedList.getAuthor());
        //sectionPubdate.setText(this.myFeedList.getPubDate());
        //sectionPubdate.setText(this.myFeedList.getDescription());
        sectionPubdate.setText(Html.fromHtml(this.myFeedList.getDescription()));
        if( (null == this.myFeedList.getNewsSrc()) || (this.myFeedList.getNewsSrc().contentEquals("emptysrc"))){
            sectionPhoto.setImageResource(R.drawable.ic_photo);
        }else{
            Picasso.with(this).load(this.myFeedList.getNewsSrc()).into(sectionPhoto);
        }
    }
}
