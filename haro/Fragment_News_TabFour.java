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


public class Fragment_News_TabFour extends Fragment{
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
        rootView = inflater.inflate(R.layout.fragment_news_section_4, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.news_section_recyclerView4);

//        mTextView = (TextView) view.findViewById(R.id.section_label);
//        mTextView.setText("Tab one of News");
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
        PicArray = new int[]{
                R.drawable.youxi_p1_01,
                R.drawable.youxi_p2_01,
                R.drawable.youxi_p3_01,
                R.drawable.youxi_p4_01,
                R.drawable.youxi_p5_01
        };
        TitleArray = new String[]{
                "超级高达大乱斗(Super Gundam Royal)",
                "高达征服(Gundam Conquest)",
                "[PS4]高达对决Premium G Sound Edition",
                "SD敢达G世纪 革命(SD Gundam G Generation RE)",
                "敢达 争锋对决"
        };
        AbsArray = new String[]{"S·RPG战略高达主题游戏的安卓端β测试！",
                "国民级Robot Gundam现在目前最强游戏登陆！",
                "万代·南梦宫今秋配信预定的iOS/Android的新作游戏『超级高达大乱斗』将于8月12日午后2点期限限定安卓端β测试实施中。本次β测试主分『机动战士高达』和『机动战士高达SEED』两个剧本，四个角度任务。",
                "目前最强的手机端高达游戏登场啦！！以即时战略和手动操作为相结合，全新的操作系统，让人绝对欲罢不能。目前主要分联邦、公国、联合、ZAFT四大阵营。",
                "VS.系列第五世代最新作『高达对决』登陆PS4！",
                "实时模拟高达战略游戏，免费下载，部分项目课金",
                "机动战士敢达正版授权，超高能敢达手游来袭！",
                "数十台人气机体，数十名人气机师，经典剧情高度还原，再次唤起你心中的敢达热血！"
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
        int[] SmallPicArray = new int[]{R.drawable.tuijian_05,R.drawable.tuijian_07,R.drawable.tuijian_08,R.drawable.tuijian_09,R.drawable.tuijian_10};

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

        Fragment_News_SubDataItem tempItem;
        // TODO:添加slideritem
        for (int i=0;i<4;i++) {
            tempItem = new Fragment_News_SubDataItem(
                    Fragment_News_SectionAdapter.PicNewsItem,
                    TimeArray[random.nextInt(TimeArray.length)],
                    TitleArray[random.nextInt(TitleArray.length)],
                    PicArray[random.nextInt(PicArray.length)],
                    AbsArray[random.nextInt(AbsArray.length)],null);
            mData.add(tempItem);
            tempItem = new Fragment_News_SubDataItem(
                    Fragment_News_SectionAdapter.SimNewsItem,
                    TimeArray[random.nextInt(TimeArray.length)],
                    TitleArray[random.nextInt(TitleArray.length)],
                    PicArray[random.nextInt(PicArray.length)],
                    AbsArray[random.nextInt(AbsArray.length)],null);
            mData.add(tempItem);
            tempItem = new Fragment_News_SubDataItem(
                    Fragment_News_SectionAdapter.SimNewsItem,
                    TimeArray[random.nextInt(TimeArray.length)],
                    TitleArray[random.nextInt(TitleArray.length)],
                    PicArray[random.nextInt(PicArray.length)],
                    AbsArray[random.nextInt(AbsArray.length)],null);
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


