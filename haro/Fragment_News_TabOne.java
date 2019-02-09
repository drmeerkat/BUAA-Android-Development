package com.wen.xin.xiao.ge.gao.haro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashMap;



public class Fragment_News_TabOne extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
//    private TextView mTextView;
    private List<Fragment_News_SubDataItem> mData;
    private View rootView;
    private RecyclerView mRecyclerView;
    private int[] PicArray;
    private String[] TitleArray;
    private String[] TimeArray;
    private String[] AbsArray;

    private Fragment_News_SectionAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news_section_1, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.news_section_recyclerView1);
        return rootView;

    }

    public Fragment_News_SectionAdapter getAdapter() {
        return adapter;
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
        PicArray = new int[]{R.drawable.tuijian_p1_01,
                R.drawable.tuijian_p1_02,
                R.drawable.tuijian_p1_03,
                R.drawable.tuijian_p1_04,
                R.drawable.night_eagle,
                R.drawable.fashou_001,
                R.drawable.fashou_002,
                R.drawable.fashou_003,
                R.drawable.fashou_004,
                R.drawable.fashou_005,
                R.drawable.fashou_006
        };
        TitleArray = new String[]{"机动战士高达00 十周年纪念蛋糕",
                "Concept Model RX-0 独角兽高达(1:20)",
                "RG RX-0 独角兽高达(1:144 漫画Bande Dessinee版)",
                "Great Mechanics G 2017 冬季刊",
                "Metal Build GNT-0000 量子型00高达测评"
        };
        AbsArray = new String[]{"机动战士高达00，10岁生日快乐！",
                "这款高达历史上唯一一款和外星人对打的高达，以最高规格Metal Build呈现的质量到底如何呢？",
                "乘着实物大独角兽高达落成的东风，大森幸三的漫画版独角兽高达RG化！",
                "『机动战士高达 雷霆宙域』松尾衡监督与Design Works 玄馬宣彦访谈",
                "独角兽就是可以为所欲为！"
        };
        TimeArray = new String[]{"2018-1-4",
                "2018-1-3",
                "2017-12-30",
                "2018-1-1",
                "2017-12-27"
        };
        int[] SmallPicArray = new int[]{R.drawable.tuijian_05,R.drawable.tuijian_07,R.drawable.tuijian_08,R.drawable.tuijian_09,R.drawable.tuijian_10};
        mData = new ArrayList<>();
        int[] BannerArray = new int[]{R.drawable.lunbo_01, R.drawable.lunbo_02, R.drawable.lunbo_03};

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("物似人非的【赤色破坏神】:红夜改 重拳形态",R.drawable.tuijian_slider_01);
        file_maps.put("2017年评测荟萃",R.drawable.tuijian_sider_02);
        file_maps.put("[幻之机体] S高达深度强袭MG200号纪念登场",R.drawable.tuijian_slider_03);
        //file_maps.put("Game of Thrones", R.drawable.game_of_thrones);

        Fragment_News_SubDataItem tempItem;
        // TODO:添加slideritem
        List<TextSliderView> mTextSliderView = new ArrayList<>();

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this.getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mTextSliderView.add(textSliderView);
        }
        tempItem = new Fragment_News_SubDataItem(Fragment_News_SectionAdapter.SliderItem, "12-29",
                TitleArray[random.nextInt(TitleArray.length)],
                PicArray[random.nextInt(PicArray.length)],
                "这是一段简介或者说是摘要", mTextSliderView);
        mData.add(tempItem);

        for (int i=0;i<5;i++) {
            tempItem = new Fragment_News_SubDataItem(
                    Fragment_News_SectionAdapter.CardItem,
                    TimeArray[i],
                    TitleArray[random.nextInt(TitleArray.length)],
                    PicArray[random.nextInt(PicArray.length)],
                    AbsArray[random.nextInt(AbsArray.length)], null);
            mData.add(tempItem);
            tempItem = new Fragment_News_SubDataItem(
                    Fragment_News_SectionAdapter.BannerItem,
                    TimeArray[i],
                    TitleArray[random.nextInt(TitleArray.length)],
                    PicArray[random.nextInt(PicArray.length)],
                    AbsArray[random.nextInt(AbsArray.length)],null);
            mData.add(tempItem);
            tempItem = new Fragment_News_SubDataItem(
                    Fragment_News_SectionAdapter.PicNewsItem,
                    TimeArray[i],
                    TitleArray[random.nextInt(TitleArray.length)],
                    PicArray[random.nextInt(PicArray.length)],
                    AbsArray[random.nextInt(AbsArray.length)],null);
            mData.add(tempItem);
            tempItem = new Fragment_News_SubDataItem(
                    Fragment_News_SectionAdapter.PicNewsItem,
                    TimeArray[i],
                    TitleArray[random.nextInt(TitleArray.length)],
                    PicArray[random.nextInt(PicArray.length)],
                    AbsArray[random.nextInt(AbsArray.length)],null);
            mData.add(tempItem);

        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        // 此处若出现空指针，则因为context获取问题，需要重写application类
        adapter = new Fragment_News_SectionAdapter(mData, this);
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

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this.getContext(),slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onDestroy() {
        adapter.onDestroySlider();
        super.onDestroy();
    }
}


