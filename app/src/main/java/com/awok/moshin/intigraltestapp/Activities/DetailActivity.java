package com.awok.moshin.intigraltestapp.Activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.awok.moshin.intigraltestapp.AppController;
import com.awok.moshin.intigraltestapp.R;

public class DetailActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView releaseDateTextView;
    private TextView popularityTextView;
    private TextView voteAverageTextView;
    private TextView overViewTextView;
    private ImageView largeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initilizeViews();
        populateView();

    }

    /**
     * Populating Views
     */
    private void populateView(){
        setTitle(getIntent().getExtras().getString("title"));
        titleTextView.setText(getIntent().getExtras().getString("title"));
        releaseDateTextView.setText(getIntent().getExtras().getString("releaseDate"));
        popularityTextView.setText(Integer.toString(getIntent().getExtras().getInt("popularity")));
        voteAverageTextView.setText(Integer.toString(getIntent().getExtras().getInt("voteAverage")));
        overViewTextView.setText(getIntent().getExtras().getString("overView"));
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        imageLoader.get(
                getIntent().getExtras().getString("largeImage"),
                new ImageLoader.ImageListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }

                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                        if (response.getBitmap() != null) {
                            // load image into imageview
                            largeImageView.setImageBitmap(response.getBitmap());
                            overViewTextView.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }


    /**
     * Initializing Views
     */
    private void initilizeViews(){
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        releaseDateTextView = (TextView) findViewById(R.id.releaseDateTextView);
        popularityTextView = (TextView) findViewById(R.id.popularityTextView);
        voteAverageTextView = (TextView) findViewById(R.id.voteAverageTextView);
        overViewTextView = (TextView) findViewById(R.id.overViewTextView);
        largeImageView = (ImageView) findViewById(R.id.largeImageView);
    }

}
