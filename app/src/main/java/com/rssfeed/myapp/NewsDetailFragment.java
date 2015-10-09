package com.rssfeed.myapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by venkatesanr on 27-05-2015.
 */
public class NewsDetailFragment extends Fragment {

    private NewsFeed myFeedList;
    public NewsDetailFragment(){}
    TextView sectionName, sectionAuthor, sectionPubdate;
    ImageView sectionPhoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        //if(b != null && b.containsKey(AppConstants.NEWS_TASK)){
            this.myFeedList = new NewsFeed(b);
        //}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_view , container , false);
        Log.i("pppppppppppppppppppp","this.myFeedList.getTitle()" +this.myFeedList.getTitle() );
        if(this.myFeedList != null){
            sectionPhoto = (ImageView) view.findViewById(R.id.section_unique_src);
            sectionName = (TextView) view.findViewById(R.id.section_unique_title);
            sectionAuthor = (TextView) view.findViewById(R.id.section_unique_author);
            sectionPubdate = (TextView) view.findViewById(R.id.section_unique_pubdate);
            sectionName.setText(this.myFeedList.getTitle());
            sectionAuthor.setText(this.myFeedList.getAuthor());
            sectionPubdate.setText(Html.fromHtml(this.myFeedList.getDescription()));
            if( (null == this.myFeedList.getNewsSrc()) || (this.myFeedList.getNewsSrc().contentEquals("emptysrc"))){
                sectionPhoto.setImageResource(R.drawable.ic_photo);
            }else{
                Picasso.with(getActivity()).load(this.myFeedList.getNewsSrc()).into(sectionPhoto);
            }
        }
        return view;
    }
}
