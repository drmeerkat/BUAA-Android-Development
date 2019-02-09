package com.wen.xin.xiao.ge.gao.haro;



public class Fragment_Wiki_SubDataItem {
    private int ItemCat;
    private String HeadName;
    private int HeadIcon;
    private int pic;
    private String title;

    public Fragment_Wiki_SubDataItem(int ItemCat, String name, int icon,  String title, int pic) {
        this.ItemCat = ItemCat;
        this.HeadName = name;
        this.HeadIcon = icon;
        this.pic = pic;
        this.title = title;

    }

    public int getPic() {
        return pic;
    }

    public String getHeadName() {
        return HeadName;
    }

    public int getHeadIcon()
    {
        return HeadIcon;
    }

    public int getItemCat() {
        return ItemCat;
    }
}
