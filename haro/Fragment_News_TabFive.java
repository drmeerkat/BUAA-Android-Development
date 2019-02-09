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


public class Fragment_News_TabFive extends Fragment{
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
        rootView = inflater.inflate(R.layout.fragment_news_section_5, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.news_section_recyclerView5);
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
        PicArray = new int[]{R.drawable.lianzai_p1_01,
                R.drawable.lianzai_p1_02,
                R.drawable.lianzai_p1_03,
                R.drawable.lianzai_p1_04,
                R.drawable.lianzai_p1_05,
                R.drawable.lianzai_p1_06,
                R.drawable.lianzai_p1_07,
                R.drawable.lianzai_p1_08,
        };
        TitleArray = new String[]{"高达 U.C.0096 最后之日 12 - 以死为食之人(噬尸鹫)",
                "MSV-R The Return of Johnny Ridden  64 – In Kennel",
                "高达外传 – 失落的节点(Missing Link) 16 HADES VS HADES",
                "海盗高达(Crossbone Gundam) Ghost 17 宣战",
                "海盗高达(Crossbone Gundam) Ghost 22 螺旋的追击者"
        };
        AbsArray = new String[]{"AEMedia与独角兽TV动画 Re 0096联动汉化！独角兽三号机登场、错综复杂的另一片战场的故事！！",
                " 由葛木ヒヨン作画，关西リョウジ撰写剧本的《机动战士高达 U.C.0094 穿越苍穹》续篇",
                "金色的独角兽3号机在战斗中突然停止运作，紧接着被突然闯入的联邦特殊部队捕获，美露梅尔队乘机撤退。乔里安对于眼前的神秘部队按捺不住，舰长不为扩大事态只得包羞忍耻，却从对方的ZZ原型机驾驶员口中得知那个熟悉的部队名称… ∑(゜ロ゜;)",
                "『把RX-0给我安全地带回来吧，我亲爱的新兵们』",
                "『我们是地球联邦宇宙军所属特殊干预部队-噬尸鹫』",
                "《机动战士高达 基连暗杀计划》作者Ark Performance最新作！",
                "月刊Gundam Ace的“MSV-R”系列联动漫画启动！以一年战争王牌驾驶员乔尼·莱登为主角的全新系列正谱写新的传说！",
                "为了营救心爱的部下，以及确保基迈拉的宝藏，胡佛展开了一系列了超高手腕排除万难，加利福利亚基地早已尘封多年的亚伽马发射计划被再次启动，曾经的敌人此刻即将汇聚一处，战后最强混编部队即将诞生！",
                "『现在就算过去，也只不过是增加一台残骸罢了！』",
                "长谷川裕一历时14年的连载作品『海盗高达』完结后的再启动，头顶骷髅的高达再临战场。新的Gundam传说现在开始！！",
                "为了寻找失去线索的天使之召唤，「新宇宙海贼」与反赞斯卡尔军事组织「神圣军事同盟」合作降下昔日联邦的总部，位于南美密林中的Jaburo基地。在哪里，他们面对的是「一骑挡千」马戏团的海陆空三方的攻击，为此而分成三组的「新宇宙海贼」究竟是否能将他们各个击破呢？"
        };
        TimeArray = new String[]{"2018-1-4",
                "2018-1-3",
                "2017-12-30",
                "2018-1-1",
                "2017-12-27"
        };
        mData = new ArrayList<>();
        int[] BannerArray = new int[]{R.drawable.lunbo_01, R.drawable.lunbo_02, R.drawable.lunbo_03};

        Fragment_News_SubDataItem tempItem;
        // TODO:添加slideritem
        for (int i=0;i<5;i++) {
            tempItem = new Fragment_News_SubDataItem(
                    Fragment_News_SectionAdapter.CardItem,
                    TimeArray[random.nextInt(TimeArray.length)],
                    TitleArray[random.nextInt(TitleArray.length)],
                    PicArray[random.nextInt(PicArray.length)],
                    AbsArray[random.nextInt(AbsArray.length)],null);
            mData.add(tempItem);
            tempItem = new Fragment_News_SubDataItem(
                    Fragment_News_SectionAdapter.BannerItem,
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


