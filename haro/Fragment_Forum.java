package com.wen.xin.xiao.ge.gao.haro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Fragment_Forum extends Fragment {
    View rootView;
    RecyclerView mRecyclerView;
    private List<Fragment_Forum_SubDataItem> mData;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.Tab2Theme);

        // clone the inflater using the ContextThemeWrapper
        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);

        rootView = localInflater.inflate(R.layout.fragment_forum_layout, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.forum_recyclerView);

        return rootView;
    }

    public View getRootview() {
        if (rootView != null) {
            return rootView;
        }
        else {
            return null;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        super.onActivityCreated(savedInstanceState);
    }

    public void initData() {
        mData = new ArrayList<>();
        Fragment_Forum_SubDataItem tempItem;
        Random random = new Random();
        int[] comments = new int[]{R.string.longtext, R.string.comment2, R.string.comment3, R.string.comment4};


        for (int i=0;i<20;i++) {
            tempItem = new Fragment_Forum_SubDataItem(Fragment_Forum_Adapter.PlainTextItem,"Haro","0:33 31/12",getResources().getString(comments[random.nextInt(comments.length)]),R.drawable.bili_default_avatar,null);
            mData.add(tempItem);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        // 此处若出现空指针，则因为context获取问题，需要重写application类
        mRecyclerView.addItemDecoration(new MyRecyclerViewDecoration());
        Fragment_Forum_Adapter mAdaper = new Fragment_Forum_Adapter(mData);
//        Log.i("Information","Forum item added");
        mAdaper.addOnclickListener(new Fragment_Forum_Adapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
//                Snackbar.make(view, "You have click item "+position+" of Forum", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                // TODO:判断各种position给予不同的点击事件
                Toast.makeText(getActivity(), "You clicked item "+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Forum_Comment.class);
                startActivity(intent);
            }

            @Override
            public void onCommentClick (View v, int position) {
                Toast.makeText(getActivity(), "You clicked item "+position+" 's comment", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Forum_Comment.class);
                startActivity(intent);

            }
        });
        mRecyclerView.setAdapter(mAdaper);

    }


}
