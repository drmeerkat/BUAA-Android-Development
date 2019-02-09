package com.wen.xin.xiao.ge.gao.haro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashMap;


public class Fragment_News_TabThree extends Fragment{
    //    private TextView mTextView;
    private List<Fragment_News_SubDataItem> mData;
    private View rootView;
    private RecyclerView mRecyclerView;
    private int[] PicArray;
    private String[] TitleArray;
    private String[] TimeArray;
    private String[] AbsArray;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news_section_3, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.news_section_recyclerView3);
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

    private void initData() {
        Random random = new Random();
        PicArray = new int[]{R.drawable.pingce_001,
                R.drawable.pingce_002,
                R.drawable.pingce_003,
                R.drawable.pingce_004,
                R.drawable.pingce_005
        };
        TitleArray = new String[]{"Metal Build GNT-0000 量子型00高达测评",
                "MG RX-0 独角兽高达2号机·报丧女妖 Ver.Ka(1:100)",
                "HGGTO MS-11 ACT扎古(1:144 GTO版)",
                "SD高达BB战士 No.405 胡轸强人&部队兵(董卓军)",
                "Hi-Resolution Model XXXG-00W0 飞翼零式敢达EW(1:100 特殊电镀版)"
        };
        AbsArray = new String[]{"这款高达历史上唯一一款和外星人对打的高达，以最高规格Metal Build呈现的质量到底如何呢？",
                "「觉醒的狮子」RX-0-2 独角兽高达2号机·报丧女妖MG套件Ver.Ka登场！",
                "初登场于外传『MS-X』，培曾计划下诞生的高性能机ACT扎古，借由不断扩展GTO世界观的MSD(Mobile Suit Discovery)套件化！",
                "侍奉董卓的其中一名武将——吕布之后的「镇江将军」胡轸通过成型色变更、追加3董卓军士兵、封绘重绘再登场！其主武器长型镰刀“击锐牙”与隐藏刀刃的盾牌“锐牙盘”均附属！两者更可合体，再现“锐牙杀影斩”！",
                "使用珍珠色电镀、亚光电镀，附送原创配色支架的飞翼零式EW，发售决定！"
        };
        TimeArray = new String[]{"2018-1-3",
                "2017-12-14",
                "2017-12-1",
                "2017-11-25",
                "2017-10-15"
        };

        mData = new ArrayList<>();

        // TODO:添加slideritem
        for (int i=0;i<5;i++) {
            Fragment_News_SubDataItem tempItem;
            tempItem = new Fragment_News_SubDataItem(
                    Fragment_News_SectionAdapter.CardItem,
                    TimeArray[i],
                    TitleArray[i],
                    PicArray[i],
                    AbsArray[i],
                    null);
            mData.add(tempItem);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        // 此处若出现空指针，则因为context获取问题，需要重写application类
        Fragment_News_SectionAdapter adapter = new Fragment_News_SectionAdapter(mData, null);
        adapter.addOnclickListener(new Fragment_News_SectionAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                int viewtype = mData.get(position).getItemCat();
                String msg = "";
                if (viewtype == Fragment_News_SectionAdapter.CardItem) {
                    msg = "CardItem";
                }
                else if (viewtype == Fragment_News_SectionAdapter.PicNewsItem) {
                    msg = "PicNewsItem";

                }
                else if (viewtype == Fragment_News_SectionAdapter.SliderItem) {
                    msg = "SliderItem";

                }
                else if (viewtype == Fragment_News_SectionAdapter.SimNewsItem) {
                    msg = "SimNewsItem";

                }
                else if (viewtype == Fragment_News_SectionAdapter.BannerItem) {
                    msg = "BannerItem";

                }
                Toast.makeText(getActivity(), "You clicked item type "+msg+" on "+position, Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

}


