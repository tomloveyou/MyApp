package com.yl.triplibrary.net.data.mvp.module;

import android.graphics.ImageDecoder;

import java.util.List;

public class TripStrategyEntity {
    private String title;
    private String userinfo;
    private String readinfo;
    private String time;
    private List<ImgInfoEntity>imgInfoEntityList;

    public String getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(String userinfo) {
        this.userinfo = userinfo;
    }

    public String getReadinfo() {
        return readinfo;
    }

    public void setReadinfo(String readinfo) {
        this.readinfo = readinfo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ImgInfoEntity> getImgInfoEntityList() {
        return imgInfoEntityList;
    }

    public void setImgInfoEntityList(List<ImgInfoEntity> imgInfoEntityList) {
        this.imgInfoEntityList = imgInfoEntityList;
    }
}
