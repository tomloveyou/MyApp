package com.yl.triplibrary.net.data.mvp.module;

public class ImaInfoTitleEntity extends ImgInfoEntity {
    private String title;


    public ImaInfoTitleEntity() {
    }
    public ImaInfoTitleEntity(String title, String img_url, String source_url) {
        super(img_url,source_url,null);
        this.title = title;

    }
    public ImaInfoTitleEntity(String title, String img_url, String source_url, String goto_ur) {
        super(img_url,source_url,goto_ur);
        this.title = title;

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
