package com.wen.xin.xiao.ge.gao.haro;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wen.xin.xiao.ge.gao.haro.view.SendCommentButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class Forum_Comment extends AppCompatActivity implements SendCommentButton.OnSendClickListener{

    public static final String ARG_DRAWING_START_LOCATION = "arg_drawing_start_location";

    private RecyclerView mRecyclerView;
    private EditText mEditText;
    private SendCommentButton commentButton;
    private LinearLayout contentRoot;
    private Toolbar toolbar;
    private LinearLayout llAddComment;
    private Forum_Comment_Adapter mAdapter;

    private int drawingStartLocation;


    private ArrayList<Fragment_Forum_SubDataItem> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_comment);

        toolbar = (Toolbar)findViewById(R.id.forum_comment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.color_tab_2));

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.color_tab_2));
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);



        mRecyclerView = findViewById(R.id.fragment_forum_comment_recycler);
        mEditText = findViewById(R.id.fragment_forum_EditText);
        mEditText.setHint(R.string.hint);
        mEditText.setHintTextColor(getResources().getColor(R.color.bili_color_light_gray));

        commentButton = (SendCommentButton) findViewById(R.id.fragment_forum_SendComment);
        contentRoot = (LinearLayout) findViewById(R.id.contentRoot);
        llAddComment = (LinearLayout) findViewById(R.id.llAddComment);


        initData();
        setupSendCommentButton();
        drawingStartLocation = getIntent().getIntExtra(ARG_DRAWING_START_LOCATION, 0);

        if (savedInstanceState == null) {
            contentRoot.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    contentRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                    startIntroAnimation();
                    return true;
                }
            });
        }
        //TODO: 此处可以用intentExtra初始化不同的评论，因为每一条动态带有的评论都不一样
    }






    private void setupSendCommentButton() {
        commentButton.setOnSendClickListener(this);
    }

    private void startIntroAnimation() {
        ViewCompat.setElevation(toolbar, 0);
        contentRoot.setScaleY(0.1f);
        contentRoot.setPivotY(drawingStartLocation);
        llAddComment.setTranslationY(200);

        contentRoot.animate()
                .scaleY(1)
                .setDuration(200)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ViewCompat.setElevation(toolbar, Utils.dpToPx(8));
                        animateContent();
                    }
                })
                .start();
    }

    private void animateContent() {
        mAdapter.updateItems();
        llAddComment.animate().translationY(0)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(200)
                .start();
    }

    @Override
    public void onBackPressed() {
        ViewCompat.setElevation(toolbar, 0);
        contentRoot.animate()
                .translationY(Utils.getScreenHeight(this))
                .setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Forum_Comment.super.onBackPressed();
                        overridePendingTransition(0, 0);
                    }
                })
                .start();
    }

    @Override
    public void onSendClickListener(View v) {
        if (validateComment()) {
            mAdapter.addItem();
            mAdapter.setAnimationsLocked(false);
            mAdapter.setDelayEnterAnimation(false);
            mRecyclerView.smoothScrollBy(0, mRecyclerView.getChildAt(0).getHeight() * mAdapter.getItemCount());

            mEditText.setText(null);
            commentButton.setCurrentState(SendCommentButton.STATE_DONE);
        }
    }

    private boolean validateComment() {
        if (TextUtils.isEmpty(mEditText.getText())) {
            commentButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake_error));
            return false;
        }

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
    public void initData() {
        mData = new ArrayList<>();
        Fragment_Forum_SubDataItem tempItem;
        Random random = new Random();
        int[] comments = new int[]{R.string.longtext, R.string.comment2, R.string.comment3, R.string.comment4};

        for (int i=0;i<20;i++) {
            tempItem = new Fragment_Forum_SubDataItem(Fragment_Forum_Adapter.PlainTextItem,
                    "Haro",
                    "0:33 31/12",
                    getResources().getString(comments[random.nextInt(comments.length)]),
                    R.drawable.bili_default_avatar,
                    null);
            mData.add(tempItem);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 此处若出现空指针，则因为context获取问题，需要重写application类
//        mRecyclerView.addItemDecoration(new MyRecyclerViewDecoration());
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new Forum_Comment_Adapter(mData, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    mAdapter.setAnimationsLocked(true);
                }
            }
        });

    }

//    private void setupComments() {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        rvComments.setLayoutManager(linearLayoutManager);
//        rvComments.setHasFixedSize(true);
//
//        commentsAdapter = new CommentsAdapter(this);
//        rvComments.setAdapter(commentsAdapter);
//        rvComments.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        rvComments.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
//                    commentsAdapter.setAnimationsLocked(true);
//                }
//            }
//        });
//    }


}
