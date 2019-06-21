package com.yl.triplibrary.net.data.mvp.module;

public class ImgInfoEntity {
    private String img_url;
    private String source_url;
    private String goto_url;

    public ImgInfoEntity() {
    }

    public ImgInfoEntity(String img_url, String source_url, String goto_ur) {
        this.img_url = img_url;
        this.source_url = source_url;
        this.goto_url = goto_ur;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getGoto_ur() {
        return goto_url;
    }

    public void setGoto_ur(String goto_ur) {
        this.goto_url = goto_ur;
    }
}
