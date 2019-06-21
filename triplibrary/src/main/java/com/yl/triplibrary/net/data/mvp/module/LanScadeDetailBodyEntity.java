package com.yl.triplibrary.net.data.mvp.module;

import java.util.List;

public class LanScadeDetailBodyEntity {
    private String body_title;
    private List<ImgInfoEntity> imgInfoEntityList;

    public String getBody_title() {
        return body_title;
    }

    public void setBody_title(String body_title) {
        this.body_title = body_title;
    }

    public List<ImgInfoEntity> getImgInfoEntityList() {
        return imgInfoEntityList;
    }

    public void setImgInfoEntityList(List<ImgInfoEntity> imgInfoEntityList) {
        this.imgInfoEntityList = imgInfoEntityList;
    }
}
