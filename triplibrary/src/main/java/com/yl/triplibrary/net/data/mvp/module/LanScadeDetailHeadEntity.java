package com.yl.triplibrary.net.data.mvp.module;

public class LanScadeDetailHeadEntity extends ImgInfoEntity {
    private String title;
    private String desc;

    public LanScadeDetailHeadEntity() {
    }
    public LanScadeDetailHeadEntity(String title, String img_url, String source_url, String desc) {
        super(img_url,source_url,null);
        this.title = title;
        this.desc = desc;
    }
    public LanScadeDetailHeadEntity(String title, String img_url, String source_url,  String goto_ur,String desc) {
        super(img_url,source_url,goto_ur);
        this.title = title;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
