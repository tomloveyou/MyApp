package com.yl.triplibrary.net.data.mvp.module;

import com.yl.triplibrary.net.data.manager.entity.RankTripZone;

import java.util.List;

public class TripHomeRecommnetEntity {
    private String title;
    private String content;
    private ImgInfoEntity imgInfoEntity;

    public TripHomeRecommnetEntity(String title, String content, String img_url, String source_url, String goto_ur) {
        this.title = title;
        this.content = content;
        this.imgInfoEntity = new ImgInfoEntity(img_url,source_url,goto_ur);
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

    public ImgInfoEntity getImgInfoEntity() {
        return imgInfoEntity;
    }

    public void setImgInfoEntity(ImgInfoEntity imgInfoEntity) {
        this.imgInfoEntity = imgInfoEntity;
    }
}
