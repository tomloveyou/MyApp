package com.yl.triplibrary.net.data.mvp.module;

import java.util.List;

public class TripTitleContentImgEntity {
    private String title;
    private String content;
    private List<ImgInfoEntity> imgInfoEntityList;

    public TripTitleContentImgEntity() {
    }

    public TripTitleContentImgEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public List<ImgInfoEntity> getImgInfoEntityList() {
        return imgInfoEntityList;
    }

    public void setImgInfoEntityList(List<ImgInfoEntity> imgInfoEntityList) {
        this.imgInfoEntityList = imgInfoEntityList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
