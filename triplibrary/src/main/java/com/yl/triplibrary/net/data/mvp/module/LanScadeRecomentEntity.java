package com.yl.triplibrary.net.data.mvp.module;

public class LanScadeRecomentEntity extends ImgInfoEntity {
    private String price;
    private String time;
    private String title;

    public LanScadeRecomentEntity(String price, String time, String title) {
        this.price = price;
        this.time = time;
        this.title = title;
    }

    public LanScadeRecomentEntity(String img_url, String source_url, String goto_ur, String price, String time, String title) {
        super(img_url, source_url, goto_ur);
        this.price = price;
        this.time = time;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
