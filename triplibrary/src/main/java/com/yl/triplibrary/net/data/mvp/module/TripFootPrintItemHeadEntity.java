package com.yl.triplibrary.net.data.mvp.module;

public class TripFootPrintItemHeadEntity extends UserInfoEntity{
    private String readinfo;
    public TripFootPrintItemHeadEntity(String username, String readinfo,String img_url, String source_url, String goto_ur) {
        super(username,img_url,source_url,goto_ur);
        this.readinfo = readinfo;
    }
    public TripFootPrintItemHeadEntity(String username, ImgInfoEntity useravator, String readinfo) {
        super(username, useravator);
        this.readinfo = readinfo;
    }

    public String getReadinfo() {
        return readinfo;
    }

    public void setReadinfo(String readinfo) {
        this.readinfo = readinfo;
    }
}
