package com.wen.xin.xiao.ge.gao.haro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashMap;


public class Fragment_News_TabTwo extends Fragment{
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
        rootView = inflater.inflate(R.layout.fragment_news_section_2, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.news_section_recyclerView2);
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
        PicArray = new int[]{R.drawable.night_eagle,
                R.drawable.fashou_001,
                R.drawable.fashou_002,
                R.drawable.fashou_003,
                R.drawable.fashou_004,
                R.drawable.fashou_005,
                R.drawable.fashou_006};
        TitleArray = new String[]{"扭蛋战士Forte MSN04II 夜莺",
                "Concept Model RX-0 独角兽高达(1:20)",
                "Great Mechanics G 2017 冬季刊",
                "于宇宙中飞翔的战士们——高达世纪 完全复刻版",
                "RG RX-0 独角兽高达(1:144 漫画Bande Dessinee版)",
                "HGBF Mrs.罗安格凛子(1:144)",
                "RG MS-06R-1A 高机动型扎古Ⅱ(黑色三连星专用机 1:144)"
        };
        AbsArray = new String[]{"决定是[夜莺]！大比例的本体与各种附属武装再现的豪华版",
                "独角兽就是可以为所欲为！",
                "『机动战士高达 雷霆宙域』松尾衡监督与Design Works 玄馬宣彦访谈",
                "高达粉丝圈中的传说刊物！极大开拓『机动战士高达』世界观与为宇宙世纪系列作品提供严谨的设定基础的梦幻书籍『月刊OUT 9月号增刊 于宇宙中飞翔的战士们——高达世纪』再版决定！",
                "乘着实物大独角兽高达落成的东风，大森幸三的漫画版独角兽高达RG化！",
                "将三石琴乃姐姐配音的角色串联！Mrs.罗安格凛子发售决定！",
                "虏获雷比尔将军的「黑色三连星」，其搭乘机之一高机动型扎古Ⅱ套件化！"
        };
        TimeArray = new String[]{"2018-1-4",
                "2018-1-3",
                "2017-12-30",
                "2017-12-18",
                "2017-10-20",
                "2017-10-15",
                "2017-8-19"
        };

        mData = new ArrayList<>();
        int[] BannerArray = new int[]{R.drawable.lunbo_01, R.drawable.lunbo_02, R.drawable.lunbo_03};

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal",R.drawable.hannibal);
        file_maps.put("Big Bang Theory",R.drawable.bigbang);
        file_maps.put("House of Cards",R.drawable.house);
        file_maps.put("Game of Thrones", R.drawable.game_of_thrones);

        for (int i=0;i<7;i++) {
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
                    Intent intent = new Intent(getActivity(), Secondary_ScrollingActivity.class);
                    // TODO:此处应该是按照position从mdatas中取出对应的数据或指针放入intent传递给详情页面活动用来加载内容
                    intent.putExtra(Secondary_ScrollingActivity.EXTRA_NAME, getResources().getString(R.string.fashou_title));
                    startActivity(intent);
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


