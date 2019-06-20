package com.yl.myapp.mvp.module;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

public class Banner extends BmobObject {
    private String title;
    private String img_url;
    private String goto_url;
    private BmobRelation relation;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getGoto_url() {
        return goto_url;
    }

    public void setGoto_url(String goto_url) {
        this.goto_url = goto_url;
    }

    public BmobRelation getRelation() {
        return relation;
    }

    public void setRelation(BmobRelation relation) {
        this.relation = relation;
    }
}
