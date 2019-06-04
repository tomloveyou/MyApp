package com.yl.userlibrary.mvp.module;

import cn.bmob.v3.BmobUser;

public class UserinfoBean extends BmobUser {
    /**
     * 用户头像
     */
    private String avator_url;
    /**
     * 个人用户url
     */
    private String head_bg_url;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 个人签名
     */
    private String personal_sign;

    public String getAvator_url() {
        return avator_url;
    }

    public void setAvator_url(String avator_url) {
        this.avator_url = avator_url;
    }

    public String getHead_bg_url() {
        return head_bg_url;
    }

    public void setHead_bg_url(String head_bg_url) {
        this.head_bg_url = head_bg_url;
    }

    public String getNickname() {
        if (nickname==null||"".equals(nickname)){
            return "用户"+getUsername()+"(默认)";
        }
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPersonal_sign() {
        if (personal_sign==null||"".equals(personal_sign)){
            return "您还没有签名哦（点击添加）";
        }
        return personal_sign;
    }

    public void setPersonal_sign(String personal_sign) {
        this.personal_sign = personal_sign;
    }
}
