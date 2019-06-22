package com.yl.triplibrary.net.data.mvp.module;

public class UserInfoEntity {
    private String username;
    private ImgInfoEntity useravator;
    public UserInfoEntity(String username,String img_url, String source_url, String goto_ur) {
        this.username = username;
        this.useravator = new ImgInfoEntity(img_url,source_url,goto_ur);

    }
    public UserInfoEntity(String username, ImgInfoEntity useravator) {
        this.username = username;
        this.useravator = useravator;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ImgInfoEntity getUseravator() {
        return useravator;
    }

    public void setUseravator(ImgInfoEntity useravator) {
        this.useravator = useravator;
    }
}
