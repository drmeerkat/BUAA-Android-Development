package com.wen.xin.xiao.ge.gao.haro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jaren.lib.view.LikeView;


public class Secondary_ScrollingActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "title";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary__scrolling);

        Intent intent = getIntent();
        final String title = intent.getStringExtra(EXTRA_NAME);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle(title);

        CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Log.i("title is ",title);
        collapsingToolbarLayout.setTitle(title);


        final ImageView imageView = (ImageView)findViewById(R.id.backdrop);
        Glide.with(this).load(R.drawable.night_eagle).centerCrop().into(imageView);

        LikeView likeview = (LikeView) findViewById(R.id.news_content_lv);
        likeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "I'm glad you like this", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Window window = getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            //点击HOME ICON finish当前Activity
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.color_tab_1));
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        super.onDestroy();
    }

}
