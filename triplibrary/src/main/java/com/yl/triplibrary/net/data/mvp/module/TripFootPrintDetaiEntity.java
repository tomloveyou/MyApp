package com.yl.triplibrary.net.data.mvp.module;

import java.util.List;

public class TripFootPrintDetaiEntity {
    private TripFootPrintItemHeadEntity headEntity;
    private String content;
    private List<ImgInfoEntity>imgInfoEntityList;
    private LanScadeInfoEntity lanScadeInfoEntity;
    private List<TripFootPrintEntity>footPrintEntityList;

    public TripFootPrintItemHeadEntity getHeadEntity() {
        return headEntity;
    }

    public void setHeadEntity(TripFootPrintItemHeadEntity headEntity) {
        this.headEntity = headEntity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ImgInfoEntity> getImgInfoEntityList() {
        return imgInfoEntityList;
    }

    public void setImgInfoEntityList(List<ImgInfoEntity> imgInfoEntityList) {
        this.imgInfoEntityList = imgInfoEntityList;
    }

    public LanScadeInfoEntity getLanScadeInfoEntity() {
        return lanScadeInfoEntity;
    }

    public void setLanScadeInfoEntity(LanScadeInfoEntity lanScadeInfoEntity) {
        this.lanScadeInfoEntity = lanScadeInfoEntity;
    }

    public List<TripFootPrintEntity> getFootPrintEntityList() {
        return footPrintEntityList;
    }

    public void setFootPrintEntityList(List<TripFootPrintEntity> footPrintEntityList) {
        this.footPrintEntityList = footPrintEntityList;
    }
}
