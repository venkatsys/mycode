package com.rssfeed.myapp.customadapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rssfeed.myapp.NewsFeed;
import com.rssfeed.myapp.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by venkatesan on 21-05-2015.
 */
public class NewsFeedListAdapter extends ArrayAdapter<NewsFeed>{
    private List<NewsFeed> getSectionfeeds;
    private Context context;
    private LayoutInflater inflater;
    private LruCache<Integer , Bitmap> imageCache;

    public NewsFeedListAdapter(Context context , int fragment_section_list,List<NewsFeed> sectionfeeds){
        super(context,fragment_section_list,sectionfeeds);
        this.getSectionfeeds = sectionfeeds;
        this.context = context;
        this.inflater = (LayoutInflater) this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
        final int cacheSize = maxMemory / 8;
        this.imageCache = new LruCache<Integer, Bitmap>(cacheSize);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsFeed sectionNewsFeed = this.getSectionfeeds.get(position);
        ViewHolder viewHolder;
        if(null == convertView){
            //convertView = this.inflater.inflate(R.layout.fragment_section_queue_list,parent,false);
            convertView = this.inflater.inflate(R.layout.rss_view_list,parent,false);
            viewHolder = new ViewHolder();
            //viewHolder.sectionID = (TextView) convertView.findViewById(R.id.section_unique_id);
            viewHolder.sectionPhoto = (ImageView) convertView.findViewById(R.id.section_unique_src);
            viewHolder.sectionName = (TextView) convertView.findViewById(R.id.section_unique_title);
            viewHolder.sectionAuthor = (TextView) convertView.findViewById(R.id.section_unique_author);
            viewHolder.sectionPubdate = (TextView) convertView.findViewById(R.id.section_unique_pubdate);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //viewHolder.sectionID.setText("1");
        Log.i("MINcustom","sectionNewsFeed.getTitle()=>" +sectionNewsFeed.getNewsSrc());
        viewHolder.sectionName.setText(sectionNewsFeed.getTitle());
        viewHolder.sectionAuthor.setText(sectionNewsFeed.getAuthor());
        viewHolder.sectionPubdate.setText(sectionNewsFeed.getPubDate());
        Bitmap bitmap = this.imageCache.get(sectionNewsFeed.getSectionID());
        viewHolder.sectionPhoto.setImageResource(R.drawable.ic_photo);
        if(bitmap !=null) {
            viewHolder.sectionPhoto.setImageBitmap(sectionNewsFeed.getBitmap());
        }else{
            SectionView container = new SectionView();
            container.view = convertView;
            container.feedResponse = sectionNewsFeed;

            if( (null == sectionNewsFeed.getNewsSrc()) || (sectionNewsFeed.getNewsSrc().contentEquals("emptysrc"))){
                //Log.i("Mytitle", "TITLEadapter =>" + sectionNewsFeed.getNewsSrc());
                viewHolder.sectionPhoto.setImageResource(R.drawable.ic_photo);
            }else{
                new ImageLoader().execute(container);
            }
        }
        return convertView;
    }

    /**
     * Class for Viewholder pattern in the getview
     */
    class ViewHolder{
        TextView sectionID, sectionName, sectionAuthor, sectionPubdate;
        ImageView sectionPhoto;
    }

    /**
     * Class for Lazy loading Images
     */
    class SectionView{
        public NewsFeed feedResponse;
        public View view;
        public Bitmap bitmap;
    }

    /**
     * Class to get download image from the given url
     */
    private class ImageLoader extends AsyncTask<SectionView , Void , SectionView>{
        DefaultHttpClient httpClient;
        @Override
        protected void onPreExecute() {
            httpClient = new DefaultHttpClient();
            super.onPreExecute();
        }

        @Override
        protected SectionView doInBackground(SectionView... params) {
            SectionView container = params[0];
            NewsFeed respObject = container.feedResponse;
            /*try {
                Log.i("sections", "imageURL =>" + respObject.getNewsSrc());
                String imageUrl = respObject.getNewsSrc();
                HttpPost httpPost = new HttpPost(imageUrl);
                HttpResponse response = httpClient.execute(httpPost);
                InputStream in = response.getEntity().getContent();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                respObject.setBitmap(bitmap);
                in.close();
                container.bitmap = bitmap;
                //container.bitmap = getResizedBitmap(bitmap,60);
                return container;
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            //return null;

            Bitmap img = null;
            String imageUrl = respObject.getNewsSrc();
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(imageUrl);
            HttpResponse response;
            try {
                response = (HttpResponse)client.execute(request);
                HttpEntity entity = response.getEntity();
                BufferedHttpEntity bufferedEntity = new BufferedHttpEntity(entity);
                InputStream inputStream = bufferedEntity.getContent();
                img = BitmapFactory.decodeStream(inputStream);
                respObject.setBitmap(img);
                container.bitmap = img;
                return container;
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
            /*InputStream in = null;
            Bitmap bmp = null;
            String imageUrl = respObject.getNewsSrc();
            in = OpenHttpConnection(imageUrl);
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, options);

            options.inSampleSize = calculateSampleSize(options, 64, 64);

            options.inJustDecodeBounds = true;
            bmp = BitmapFactory.decodeStream(in, null, options);
            respObject.setBitmap(bmp);
            container.bitmap = bmp;
            return container;*/
        }

        @Override
        protected void onPostExecute(SectionView result) {
            super.onPostExecute(result);
            Log.i("result","result is => " + result);
            try {
                ImageView sectionPhoto = (ImageView) result.view.findViewById(R.id.section_unique_src);
                sectionPhoto.setImageBitmap(result.bitmap);
                Log.i("sections", "TITLEadapter =>" + result.feedResponse.getSectionID());
                //result.feedResponse.setBitmap(result.bitmap);
                imageCache.put(result.feedResponse.getSectionID(), result.bitmap);
            }catch(Exception e){
                e.printStackTrace();
            }

        }

        private InputStream OpenHttpConnection(String strURL) {

            try {
                URL url = new URL(strURL);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(connection.getInputStream());
                return in;
            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }

        public int calculateSampleSize(BitmapFactory.Options options,
                                              int reqWidth, int reqHeight) {

            final int width = options.outWidth;
            final int height = options.outHeight;
            int inSampleSize = 1;

            if (width > reqWidth || height > reqHeight) {
                if (width > height) {
                    inSampleSize = Math.round((float) height / (float) reqHeight);
                } else {
                    inSampleSize = Math.round((float) width / (float) reqWidth);
                }
            }
            return inSampleSize;
        }

        public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
            int width = image.getWidth();
            int height = image.getHeight();

            float bitmapRatio = (float)width / (float) height;
            if (bitmapRatio > 0) {
                width = maxSize;
                height = (int) (width / bitmapRatio);
            } else {
                height = maxSize;
                width = (int) (height * bitmapRatio);
            }
            return Bitmap.createScaledBitmap(image, width, height, true);
        }

    }

}
