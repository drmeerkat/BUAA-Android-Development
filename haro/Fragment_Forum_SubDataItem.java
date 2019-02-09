package com.wen.xin.xiao.ge.gao.haro;


public class Fragment_Forum_SubDataItem {

    private String username;
    private String time;
    private String longtext;
    private int usericon;
    private int[] images;

    public int getItemCat() {
        return ItemCat;
    }

    int ItemCat;

    public Fragment_Forum_SubDataItem(int itemCat, String username, String time, String longtext, int usericon, int[] images) {
        this.ItemCat = itemCat;
        this.images = images;
        this.usericon = usericon;
        this.username = username;
        this.time = time;
        this.longtext = longtext;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public String getLongtext() {
        return longtext;
    }

    public int getUsericon() {
        return usericon;
    }

    public int[] getImages() {
        return images;
    }



}
