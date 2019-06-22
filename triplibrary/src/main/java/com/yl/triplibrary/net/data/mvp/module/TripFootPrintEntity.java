package com.yl.triplibrary.net.data.mvp.module;

import java.util.List;

public class TripFootPrintEntity  {

    private TripFootPrintItemHeadEntity printItemHeadInfoEntity;
    private TripFootPrintItemEndEntity printItemEndEntity;
    private List<ImgInfoEntity> imgInfoEntityList;
    private String goto_url;

    public String getGoto_url() {
        return goto_url;
    }

    public void setGoto_url(String goto_url) {
        this.goto_url = goto_url;
    }

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TripFootPrintItemHeadEntity getPrintItemHeadInfoEntity() {
        return printItemHeadInfoEntity;
    }

    public void setPrintItemHeadInfoEntity(TripFootPrintItemHeadEntity printItemHeadInfoEntity) {
        this.printItemHeadInfoEntity = printItemHeadInfoEntity;
    }

    public TripFootPrintItemEndEntity getPrintItemEndEntity() {
        return printItemEndEntity;
    }

    public void setPrintItemEndEntity(TripFootPrintItemEndEntity printItemEndEntity) {
        this.printItemEndEntity = printItemEndEntity;
    }

    public List<ImgInfoEntity> getImgInfoEntityList() {
        return imgInfoEntityList;
    }

    public void setImgInfoEntityList(List<ImgInfoEntity> imgInfoEntityList) {
        this.imgInfoEntityList = imgInfoEntityList;
    }
}
