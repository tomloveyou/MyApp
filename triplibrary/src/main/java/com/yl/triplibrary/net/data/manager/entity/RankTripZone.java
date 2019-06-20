package com.yl.triplibrary.net.data.manager.entity;

import java.io.Serializable;

public class RankTripZone implements Serializable {
 private    String title;
    private   String content ;
    private  String thumb_img;
    private  String original_img ;
    private String url ;
    private String img_head;

    public RankTripZone() {
    }

    public RankTripZone(String title, String content, String thumb_img, String original_img, String url, String img_head) {
        this.title = title;
        this.content = content;
        this.thumb_img = thumb_img;
        this.original_img = original_img;
        this.url = url;
        this.img_head = img_head;
    }

    public String getImg_head() {
        return img_head;
    }

    public void setImg_head(String img_head) {
        this.img_head = img_head;
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

    public String getThumb_img() {
        return thumb_img;
    }

    public void setThumb_img(String thumb_img) {
        this.thumb_img = thumb_img;
    }

    public String getOriginal_img() {
        return original_img;
    }

    public void setOriginal_img(String original_img) {
        this.original_img = original_img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
