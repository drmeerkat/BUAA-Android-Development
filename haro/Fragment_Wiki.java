package com.wen.xin.xiao.ge.gao.haro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.wen.xin.xiao.ge.gao.haro.Fragment_Wiki_Adapter.BannerItem;
import static com.wen.xin.xiao.ge.gao.haro.Fragment_Wiki_Adapter.HeadItem;
import static com.wen.xin.xiao.ge.gao.haro.Fragment_Wiki_Adapter.SliderItem;
import static com.wen.xin.xiao.ge.gao.haro.Fragment_Wiki_Adapter.SubTitleItem;

public class Fragment_Wiki extends Fragment {
    View rootView;
    private RecyclerView mRecyclerView;
    private String[] NameArray;
    private int[] IconArray;
    private List<Fragment_Wiki_SubDataItem> mData;
    private String[] TitleArray;
    private int[] PicArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_wiki_layout, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.wiki_recyclerView);
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
        int[] bannerid = new int[]{
                R.drawable.wiki_banner_01,
                R.drawable.wiki_banner_03,
                R.drawable.wiki_banner_05,
                R.drawable.wiki_banner_07
        };
        int[] sliderid = new int[]{R.drawable.lianzai_02, R.drawable.lianzai_05, R.drawable.tuijian_08, R.drawable.tuijian_09};
        NameArray = this.getContext().getResources().getStringArray(R.array.wiki);
        IconArray = new int[]{R.mipmap.ag, R.mipmap.buc, R.mipmap.cw, R.mipmap.dx, R.mipmap.ez, R.mipmap.f91,
                R.mipmap.ic_0079, R.mipmap.ic_0083 ,R.drawable.ic_more_horiz_black_48dp};
        TitleArray = new String[]{
                "RX-78-1 Prototype Gundam Rollout Type",
                "RX-78-2 Gundam",
                "MS-07B Gouf",
                "MS-09MS-09B Dom",
                "RX-78GP02 Gundam GP02 “Physalis”",
                "FA-78GP01 Full Armored Gundam GP01″Zephyranthes”",
                "RX-78GP00 Gundam GP-00 “Blossom”",
                "RX-0 Unicorn Gundam",
                "MSN-001X Gundam Delta Kai(Δ改)",
                "MSN-06S Sinanju",
                "RX-0 Unicorn Gundam 2 “Banshee”"
        };
        PicArray = new int[]{
                R.drawable.wiki_card_01,
                R.drawable.wiki_card_02,
                R.drawable.wiki_card_03,
                R.drawable.wiki_card_04,
                R.drawable.wiki_card_05,
                R.drawable.wiki_card_06,
                R.drawable.wiki_card_07,
                R.drawable.wiki_card_08,
                R.drawable.wiki_card_09,
                R.drawable.wiki_card_10,
                R.drawable.wiki_card_11
        };
        mData = new ArrayList<>();
        Fragment_Wiki_SubDataItem tempItem;
        for (int i=0;i<IconArray.length;i++) {
            tempItem = new Fragment_Wiki_SubDataItem(HeadItem, NameArray[i],IconArray[i], "", IconArray[i]);
            mData.add(tempItem);
        }
        for (int i=0;i<IconArray.length-1;i++) {
            tempItem = new Fragment_Wiki_SubDataItem(SubTitleItem, NameArray[i],IconArray[i], "", IconArray[i]);
            mData.add(tempItem);
            if (i % 2 == 0) {
                // 随便添加一些Banner
                tempItem = new Fragment_Wiki_SubDataItem(BannerItem, NameArray[i], IconArray[i], "",bannerid[(i+1)/2]);
                mData.add(tempItem);
            }
//            tempItem = new Fragment_Wiki_SubDataItem(SubTitleItem, NameArray[i],IconArray[i], "", sliderid[random.nextInt(sliderid.length)]);
            tempItem = new Fragment_Wiki_SubDataItem(SliderItem, TitleArray[random.nextInt(TitleArray.length)],
                    IconArray[i], "", PicArray[random.nextInt(PicArray.length)]);
            mData.add(tempItem);
            tempItem = new Fragment_Wiki_SubDataItem(SliderItem, TitleArray[random.nextInt(TitleArray.length)],
                    IconArray[i], "", PicArray[random.nextInt(PicArray.length)]);
            mData.add(tempItem);
            tempItem = new Fragment_Wiki_SubDataItem(SliderItem, TitleArray[random.nextInt(TitleArray.length)],
                    IconArray[i], "", PicArray[random.nextInt(PicArray.length)]);
            mData.add(tempItem);
            tempItem = new Fragment_Wiki_SubDataItem(SliderItem, TitleArray[random.nextInt(TitleArray.length)],
                    IconArray[i], "", PicArray[random.nextInt(PicArray.length)]);
            mData.add(tempItem);

        }

        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 6, GridLayoutManager.VERTICAL, false));
        // 此处若出现空指针，则因为context获取问题，需要重写application类
        Fragment_Wiki_Adapter adapter = new Fragment_Wiki_Adapter(mData);
        adapter.addOnclickListener(new Fragment_Wiki_Adapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                int viewtype = mData.get(position).getItemCat();
                String msg="";
                if (viewtype == HeadItem) {
                    msg="HeadItem";
                }
                else if (viewtype == SubTitleItem) {
                    msg="SubTitleItem";
                }
                else if (viewtype == SliderItem) {
                    msg="SliderItem";

                }
                else if (viewtype == BannerItem) {
                    msg="BannerItem";

                }
                Toast.makeText(getActivity(), "You clicked item type "+msg+" on "+position, Toast.LENGTH_SHORT).show();
            }

        });
        mRecyclerView.setAdapter(adapter);


    }

}
