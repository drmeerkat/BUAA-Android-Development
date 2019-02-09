package com.wen.xin.xiao.ge.gao.haro;


import android.support.annotation.Nullable;

import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.List;

public class Fragment_News_SubDataItem {
    private int ItemCat;
    private String time;
    private int pic;
    private String title;
    private String abs;
    private List<TextSliderView> sliders;

    public Fragment_News_SubDataItem(int ItemCat, String time, String title, int pic, String abs, @Nullable List<TextSliderView> msliders) {
        this.ItemCat = ItemCat;
        this.pic = pic;
        this.title = title;
        this.time = time;
        this.abs = abs;
        this.sliders = msliders;
    }

    public int getPic() {
        return pic;
    }

    public int getItemCat() {
        return ItemCat;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getAbs() {
        return abs;
    }

    public List<TextSliderView> getSliders () {
        return sliders;
    }
}
