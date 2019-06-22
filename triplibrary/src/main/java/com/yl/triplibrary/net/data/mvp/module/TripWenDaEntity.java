package com.yl.triplibrary.net.data.mvp.module;

public class TripWenDaEntity {
    private String title;
    private UserInfoEntity userInfoEntity;
    private String content;
    private String address;
    private String other_desc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOther_desc() {
        return other_desc;
    }

    public void setOther_desc(String other_desc) {
        this.other_desc = other_desc;
    }
}
