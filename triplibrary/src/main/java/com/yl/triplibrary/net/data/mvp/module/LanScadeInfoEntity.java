package com.yl.triplibrary.net.data.mvp.module;

public class LanScadeInfoEntity {
    private ImgInfoEntity imgInfoEntity;
    private String lanScadeName;
    private String lanScadeDesc;
    private String lanScadInfoTime;
    private String lanScadDetailAddress;
    private String lanScadSimpleAddress;
    private String lanScadSimpleAddress_goto_url;

    public LanScadeInfoEntity(ImgInfoEntity imgInfoEntity, String lanScadeName, String lanScadeDesc, String lanScadInfoTime, String lanScadDetailAddress, String lanScadSimpleAddress,String lanScadSimpleAddress_goto_url) {
        this.imgInfoEntity = imgInfoEntity;
        this.lanScadeName = lanScadeName;
        this.lanScadeDesc = lanScadeDesc;
        this.lanScadInfoTime = lanScadInfoTime;
        this.lanScadDetailAddress = lanScadDetailAddress;
        this.lanScadSimpleAddress = lanScadSimpleAddress;
        this.lanScadSimpleAddress_goto_url = lanScadSimpleAddress_goto_url;
    }
    public LanScadeInfoEntity(String img_url, String source_url, String goto_ur, String lanScadeName, String lanScadeDesc, String lanScadInfoTime, String lanScadDetailAddress, String lanScadSimpleAddress,String lanScadSimpleAddress_goto_url) {
        this.imgInfoEntity = new ImgInfoEntity(img_url,source_url,goto_ur);
        this.lanScadeName = lanScadeName;
        this.lanScadeDesc = lanScadeDesc;
        this.lanScadInfoTime = lanScadInfoTime;
        this.lanScadDetailAddress = lanScadDetailAddress;
        this.lanScadSimpleAddress = lanScadSimpleAddress;
        this.lanScadSimpleAddress_goto_url = lanScadSimpleAddress_goto_url;

    }

    public String getLanScadSimpleAddress_goto_url() {
        return lanScadSimpleAddress_goto_url;
    }

    public void setLanScadSimpleAddress_goto_url(String lanScadSimpleAddress_goto_url) {
        this.lanScadSimpleAddress_goto_url = lanScadSimpleAddress_goto_url;
    }

    public ImgInfoEntity getImgInfoEntity() {
        return imgInfoEntity;
    }

    public void setImgInfoEntity(ImgInfoEntity imgInfoEntity) {
        this.imgInfoEntity = imgInfoEntity;
    }

    public String getLanScadeName() {
        return lanScadeName;
    }

    public void setLanScadeName(String lanScadeName) {
        this.lanScadeName = lanScadeName;
    }

    public String getLanScadeDesc() {
        return lanScadeDesc;
    }

    public void setLanScadeDesc(String lanScadeDesc) {
        this.lanScadeDesc = lanScadeDesc;
    }

    public String getLanScadInfoTime() {
        return lanScadInfoTime;
    }

    public void setLanScadInfoTime(String lanScadInfoTime) {
        this.lanScadInfoTime = lanScadInfoTime;
    }

    public String getLanScadDetailAddress() {
        return lanScadDetailAddress;
    }

    public void setLanScadDetailAddress(String lanScadDetailAddress) {
        this.lanScadDetailAddress = lanScadDetailAddress;
    }

    public String getLanScadSimpleAddress() {
        return lanScadSimpleAddress;
    }

    public void setLanScadSimpleAddress(String lanScadSimpleAddress) {
        this.lanScadSimpleAddress = lanScadSimpleAddress;
    }
}
