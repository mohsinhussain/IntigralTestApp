package com.awok.moshin.intigraltestapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LayoutInflater inflator;
    private LinearLayout parentLayout;
    private ProgressBar progressBar;
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
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        requestData();


    }

    /**
     * Requesting parrallel API CALLs from server
     */
    private void requestData(){
        if(Constants.isNetworkAvailable(this)){
            new APIClient(this,  new GetUpcomingCallback()).GetMoviesByCategory("upcoming");
            new APIClient(this,  new GetTopRatedCallback()).GetMoviesByCategory("top_rated");
            new APIClient(this,  new GetPopularCallback()).GetMoviesByCategory("popular");
            new APIClient(this,  new GetNowPlayingCallback()).GetMoviesByCategory("now_playing");
        }
        else{
            Toast.makeText(this, "Please connect to internet", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
        }
    }


    /**
     *
     * Following are the respective callback functions of API calls
     *
     */

    private class GetUpcomingCallback extends AsyncCallback {
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

    private class GetNowPlayingCallback extends AsyncCallback {
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

    private class GetPopularCallback extends AsyncCallback {
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

    private class GetTopRatedCallback extends AsyncCallback {
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




    /**
     * Populate views dynamically as the data returns from server
     * @param type
     */
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
                                }

                                @Override
                                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                                    if (response.getBitmap() != null) {
                                        // load image into imageview
                                        imageView.setImageBitmap(response.getBitmap());
                                    }
                                }
                            });
                    imageView.setTag(x);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = (int) v.getTag();
                            //Show Detail View
                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            intent.putExtra("title", upComingMovieArrayList.get(pos).getTitle());
                            intent.putExtra("releaseDate", upComingMovieArrayList.get(pos).getReleaseDate());
                            intent.putExtra("popularity", upComingMovieArrayList.get(pos).getPopularity());
                            intent.putExtra("voteAverage", upComingMovieArrayList.get(pos).getVoteAverage());
                            intent.putExtra("overView", upComingMovieArrayList.get(pos).getOverView());
                            intent.putExtra("largeImage", upComingMovieArrayList.get(pos).getLargeImage());
                            startActivity(intent);
                        }
                    });
                    containerLayout.addView(imageView);
                    if(nowPlayingMovieArrayList.size()!=0 && topRatedMovieArrayList.size()!=0 && popularMovieArrayList.size()!=0){
                        progressBar.setVisibility(View.GONE);
                    }
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
                                }

                                @Override
                                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                                    if (response.getBitmap() != null) {
                                        // load image into imageview
                                        imageView.setImageBitmap(response.getBitmap());
                                    }
                                }
                            });
                    imageView.setTag(x);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = (int) v.getTag();
                            //Show Detail View
                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            intent.putExtra("title", nowPlayingMovieArrayList.get(pos).getTitle());
                            intent.putExtra("releaseDate", nowPlayingMovieArrayList.get(pos).getReleaseDate());
                            intent.putExtra("popularity", nowPlayingMovieArrayList.get(pos).getPopularity());
                            intent.putExtra("voteAverage", nowPlayingMovieArrayList.get(pos).getVoteAverage());
                            intent.putExtra("overView", nowPlayingMovieArrayList.get(pos).getOverView());
                            intent.putExtra("largeImage", nowPlayingMovieArrayList.get(pos).getLargeImage());
                            startActivity(intent);
                        }
                    });
                    containerLayout.addView(imageView);
                    if(upComingMovieArrayList.size()!=0 && topRatedMovieArrayList.size()!=0 && popularMovieArrayList.size()!=0){
                        progressBar.setVisibility(View.GONE);
                    }
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
                                }

                                @Override
                                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                                    if (response.getBitmap() != null) {
                                        // load image into imageview
                                        imageView.setImageBitmap(response.getBitmap());
                                    }
                                }
                            });
                    imageView.setTag(x);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = (int) v.getTag();
                            //Show Detail View
                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            intent.putExtra("title", popularMovieArrayList.get(pos).getTitle());
                            intent.putExtra("releaseDate", popularMovieArrayList.get(pos).getReleaseDate());
                            intent.putExtra("popularity", popularMovieArrayList.get(pos).getPopularity());
                            intent.putExtra("voteAverage", popularMovieArrayList.get(pos).getVoteAverage());
                            intent.putExtra("overView", popularMovieArrayList.get(pos).getOverView());
                            intent.putExtra("largeImage", popularMovieArrayList.get(pos).getLargeImage());
                            startActivity(intent);
                        }
                    });
                    containerLayout.addView(imageView);
                }
                parentLayout.addView(headingTextView);
                parentLayout.addView((View) containerLayout.getParent());
                if(nowPlayingMovieArrayList.size()!=0 && topRatedMovieArrayList.size()!=0 && upComingMovieArrayList.size()!=0){
                    progressBar.setVisibility(View.GONE);
                }
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
                                }

                                @Override
                                public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                                    if (response.getBitmap() != null) {
                                        // load image into imageview
                                        imageView.setImageBitmap(response.getBitmap());
                                    }
                                }
                            });
                    imageView.setTag(x);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = (int) v.getTag();
                            //Show Detail View
                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            intent.putExtra("title", topRatedMovieArrayList.get(pos).getTitle());
                            intent.putExtra("releaseDate", topRatedMovieArrayList.get(pos).getReleaseDate());
                            intent.putExtra("popularity", topRatedMovieArrayList.get(pos).getPopularity());
                            intent.putExtra("voteAverage", topRatedMovieArrayList.get(pos).getVoteAverage());
                            intent.putExtra("overView", topRatedMovieArrayList.get(pos).getOverView());
                            intent.putExtra("largeImage", topRatedMovieArrayList.get(pos).getLargeImage());
                            startActivity(intent);
                        }
                    });
                    containerLayout.addView(imageView);
                }
                parentLayout.addView(headingTextView);
                parentLayout.addView((View) containerLayout.getParent());
                if(nowPlayingMovieArrayList.size()!=0 && upComingMovieArrayList.size()!=0 && popularMovieArrayList.size()!=0){
                    progressBar.setVisibility(View.GONE);
                }
                break;
        }
    }
}
