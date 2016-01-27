package com.awok.moshin.intigraltestapp.Activities;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.awok.moshin.intigraltestapp.AppController;
import com.awok.moshin.intigraltestapp.Models.Movie;
import com.awok.moshin.intigraltestapp.NetworkLayer.APIClient;
import com.awok.moshin.intigraltestapp.NetworkLayer.AsyncCallback;
import com.awok.moshin.intigraltestapp.R;
import com.awok.moshin.intigraltestapp.Utilities.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LayoutInflater inflator;
    private LinearLayout parentLayout;
    private ArrayList<Movie> upComingMovieArrayList = new ArrayList<Movie>();
    private ArrayList<Movie> nowPlayingMovieArrayList = new ArrayList<Movie>();
    private ArrayList<Movie> popularMovieArrayList = new ArrayList<Movie>();
    private ArrayList<Movie> topRatedMovieArrayList = new ArrayList<Movie>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflator = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        parentLayout = (LinearLayout) findViewById(R.id.parentLayout);

//        for(int i=0;i<7;i++){
//            //Adding dynamic header
//            TextView headingTextView = (TextView) inflator.inflate(R.layout.header_text_layout, null).findViewById(R.id.headerTextView);
//            headingTextView.setText("Heading "+i);
//
//            //Adding horizontal scrollview container for interactive images
//            LinearLayout containerLayout = (LinearLayout) inflator.inflate(R.layout.horizontal_scroll_view_layout, null)
//                    .findViewById(R.id.containerLayout);
//            for(int x=0;x<10;x++){
//                //Adding interactive images
//                ImageView imageView = (ImageView) inflator.inflate(R.layout.image_layout, null)
//                        .findViewById(R.id.imageView);
//                imageView.setImageDrawable(getResources().getDrawable(R.drawable.test_image));
//                imageView.setTag(x);
//                imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(MainActivity.this, "Image: " + v.getTag(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//                containerLayout.addView(imageView);
//            }
//            parentLayout.addView(headingTextView);
//            parentLayout.addView((View) containerLayout.getParent());
//        }

        new APIClient(this,  new GetUpcomingCallback()).GetMoviesByCategory("upcoming");
        new APIClient(this,  new GetTopRatedCallback()).GetMoviesByCategory("top_rated");
        new APIClient(this,  new GetPopularCallback()).GetMoviesByCategory("popular");
        new APIClient(this,  new GetNowPlayingCallback()).GetMoviesByCategory("now_playing");

    }



    public class GetUpcomingCallback extends AsyncCallback {
        public void onTaskComplete(String response) {
            System.out.println("Response: "+response);
            try {
                JSONObject respObject = new JSONObject(response);
                if(respObject.has("status_message")){
                    Toast.makeText(getApplicationContext(), respObject.getString("status_message"), Toast.LENGTH_LONG).show();
                }
                else{
                    upComingMovieArrayList.clear();
                    JSONArray resultArray = respObject.getJSONArray("results");
                    for(int i=0;i<resultArray.length();i++){
                        JSONObject movieObject = resultArray.getJSONObject(i);
                        upComingMovieArrayList.add(new Movie(movieObject.getInt("id"), movieObject.getString("title"), movieObject.getString("overview"), movieObject.getString("backdrop_path"), movieObject.getString("poster_path"),
                                movieObject.getInt("popularity"), movieObject.getInt("vote_average"), movieObject.getString("release_date")));
                    }
                    populateViews(0);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onTaskCancelled() {
        }
        @Override
        public void onPreExecute() {
            // TODO Auto-generated method stub
        }
    }


    public class GetNowPlayingCallback extends AsyncCallback {
        public void onTaskComplete(String response) {
            System.out.println("Response: "+response);
            try {
                JSONObject respObject = new JSONObject(response);
                if(respObject.has("status_message")){
                    Toast.makeText(getApplicationContext(), respObject.getString("status_message"), Toast.LENGTH_LONG).show();
                }
                else{
                    nowPlayingMovieArrayList.clear();
                    JSONArray resultArray = respObject.getJSONArray("results");
                    for(int i=0;i<resultArray.length();i++){
                        JSONObject movieObject = resultArray.getJSONObject(i);
                        nowPlayingMovieArrayList.add(new Movie(movieObject.getInt("id"), movieObject.getString("title"), movieObject.getString("overview"), movieObject.getString("backdrop_path"), movieObject.getString("poster_path"),
                                movieObject.getInt("popularity"), movieObject.getInt("vote_average"), movieObject.getString("release_date")));
                    }
                    populateViews(1);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onTaskCancelled() {
        }
        @Override
        public void onPreExecute() {
            // TODO Auto-generated method stub
        }
    }


    public class GetPopularCallback extends AsyncCallback {
        public void onTaskComplete(String response) {
            System.out.println("Response: "+response);
            try {
                JSONObject respObject = new JSONObject(response);
                if(respObject.has("status_message")){
                    Toast.makeText(getApplicationContext(), respObject.getString("status_message"), Toast.LENGTH_LONG).show();
                }
                else{
                    popularMovieArrayList.clear();
                    JSONArray resultArray = respObject.getJSONArray("results");
                    for(int i=0;i<resultArray.length();i++){
                        JSONObject movieObject = resultArray.getJSONObject(i);
                        popularMovieArrayList.add(new Movie(movieObject.getInt("id"), movieObject.getString("title"), movieObject.getString("overview"), movieObject.getString("backdrop_path"), movieObject.getString("poster_path"),
                                movieObject.getInt("popularity"), movieObject.getInt("vote_average"), movieObject.getString("release_date")));
                    }
                    populateViews(2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onTaskCancelled() {
        }
        @Override
        public void onPreExecute() {
            // TODO Auto-generated method stub
        }
    }


    public class GetTopRatedCallback extends AsyncCallback {
        public void onTaskComplete(String response) {
            System.out.println("Response: "+response);
            try {
                JSONObject respObject = new JSONObject(response);
                if(respObject.has("status_message")){
                    Toast.makeText(getApplicationContext(), respObject.getString("status_message"), Toast.LENGTH_LONG).show();
                }
                else{
                    topRatedMovieArrayList.clear();
                    JSONArray resultArray = respObject.getJSONArray("results");
                    for(int i=0;i<resultArray.length();i++){
                        JSONObject movieObject = resultArray.getJSONObject(i);
                        topRatedMovieArrayList.add(new Movie(movieObject.getInt("id"), movieObject.getString("title"), movieObject.getString("overview"), movieObject.getString("backdrop_path"), movieObject.getString("poster_path"),
                                movieObject.getInt("popularity"), movieObject.getInt("vote_average"), movieObject.getString("release_date")));
                    }
                    populateViews(3);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onTaskCancelled() {
        }
        @Override
        public void onPreExecute() {
            // TODO Auto-generated method stub
        }
    }

    private void populateViews(int type){
        TextView headingTextView = (TextView) inflator.inflate(R.layout.header_text_layout, null).findViewById(R.id.headerTextView);
        LinearLayout containerLayout = (LinearLayout) inflator.inflate(R.layout.horizontal_scroll_view_layout, null)
                .findViewById(R.id.containerLayout);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        switch (type) {
            case 0:
                headingTextView = (TextView) inflator.inflate(R.layout.header_text_layout, null).findViewById(R.id.headerTextView);
                headingTextView.setText("UPCOMING");

                //Adding horizontal scrollview container for interactive images
                containerLayout = (LinearLayout) inflator.inflate(R.layout.horizontal_scroll_view_layout, null)
                        .findViewById(R.id.containerLayout);
                for(int x=0;x<upComingMovieArrayList.size();x++){
                    //Adding interactive images
                    final ImageView imageView = (ImageView) inflator.inflate(R.layout.image_layout, null)
                            .findViewById(R.id.imageView);
                    imageLoader.get(
                            upComingMovieArrayList.get(x).getPosterPath(),
                            new ImageLoader.ImageListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//                                    viewHolder.productImg.setImageDrawable(mContext.getResources().getDrawable(R.drawable.default_img));
//                                    viewHolder.loadProgressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                                    if (response.getBitmap() != null) {
                                        // load image into imageview
                                        imageView.setImageBitmap(response.getBitmap());
//                                        viewHolder.loadProgressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                    imageView.setTag(x);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = (int) v.getTag();
                            Toast.makeText(MainActivity.this, upComingMovieArrayList.get(pos).getTitle(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    containerLayout.addView(imageView);
                }
                parentLayout.addView(headingTextView);
                parentLayout.addView((View) containerLayout.getParent());
                break;

            case 1:
                headingTextView = (TextView) inflator.inflate(R.layout.header_text_layout, null).findViewById(R.id.headerTextView);
                headingTextView.setText("NOW PLAYING");

                //Adding horizontal scrollview container for interactive images
                containerLayout = (LinearLayout) inflator.inflate(R.layout.horizontal_scroll_view_layout, null)
                        .findViewById(R.id.containerLayout);
                for(int x=0;x<nowPlayingMovieArrayList.size();x++){
                    //Adding interactive images
                    final ImageView imageView = (ImageView) inflator.inflate(R.layout.image_layout, null)
                            .findViewById(R.id.imageView);
                    imageLoader.get(
                            nowPlayingMovieArrayList.get(x).getPosterPath(),
                            new ImageLoader.ImageListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//                                    viewHolder.productImg.setImageDrawable(mContext.getResources().getDrawable(R.drawable.default_img));
//                                    viewHolder.loadProgressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                                    if (response.getBitmap() != null) {
                                        // load image into imageview
                                        imageView.setImageBitmap(response.getBitmap());
//                                        viewHolder.loadProgressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
//                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.test_image));
                    imageView.setTag(x);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = (int) v.getTag();
                            Toast.makeText(MainActivity.this, nowPlayingMovieArrayList.get(pos).getTitle(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    containerLayout.addView(imageView);
                }
                parentLayout.addView(headingTextView);
                parentLayout.addView((View) containerLayout.getParent());
                break;

            case 2:
                headingTextView = (TextView) inflator.inflate(R.layout.header_text_layout, null).findViewById(R.id.headerTextView);
                headingTextView.setText("POPULAR");

                //Adding horizontal scrollview container for interactive images
                containerLayout = (LinearLayout) inflator.inflate(R.layout.horizontal_scroll_view_layout, null)
                        .findViewById(R.id.containerLayout);
                for(int x=0;x<popularMovieArrayList.size();x++){
                    //Adding interactive images
                    final ImageView imageView = (ImageView) inflator.inflate(R.layout.image_layout, null)
                            .findViewById(R.id.imageView);
                    imageLoader.get(
                            popularMovieArrayList.get(x).getPosterPath(),
                            new ImageLoader.ImageListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//                                    viewHolder.productImg.setImageDrawable(mContext.getResources().getDrawable(R.drawable.default_img));
//                                    viewHolder.loadProgressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                                    if (response.getBitmap() != null) {
                                        // load image into imageview
                                        imageView.setImageBitmap(response.getBitmap());
//                                        viewHolder.loadProgressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
//                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.test_image));
                    imageView.setTag(x);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = (int) v.getTag();
                            Toast.makeText(MainActivity.this, popularMovieArrayList.get(pos).getTitle(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    containerLayout.addView(imageView);
                }
                parentLayout.addView(headingTextView);
                parentLayout.addView((View) containerLayout.getParent());
                break;

            case 3:
                headingTextView = (TextView) inflator.inflate(R.layout.header_text_layout, null).findViewById(R.id.headerTextView);
                headingTextView.setText("TOP RATED");

                //Adding horizontal scrollview container for interactive images
                containerLayout = (LinearLayout) inflator.inflate(R.layout.horizontal_scroll_view_layout, null)
                        .findViewById(R.id.containerLayout);
                for(int x=0;x<topRatedMovieArrayList.size();x++){
                    //Adding interactive images
                    final ImageView imageView = (ImageView) inflator.inflate(R.layout.image_layout, null)
                            .findViewById(R.id.imageView);
                    imageLoader.get(
                            topRatedMovieArrayList.get(x).getPosterPath(),
                            new ImageLoader.ImageListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//                                    viewHolder.productImg.setImageDrawable(mContext.getResources().getDrawable(R.drawable.default_img));
//                                    viewHolder.loadProgressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                                    if (response.getBitmap() != null) {
                                        // load image into imageview
                                        imageView.setImageBitmap(response.getBitmap());
//                                        viewHolder.loadProgressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
//                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.test_image));
                    imageView.setTag(x);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = (int) v.getTag();
                            Toast.makeText(MainActivity.this, topRatedMovieArrayList.get(pos).getTitle(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    containerLayout.addView(imageView);
                }
                parentLayout.addView(headingTextView);
                parentLayout.addView((View) containerLayout.getParent());
                break;


        }
    }
}
