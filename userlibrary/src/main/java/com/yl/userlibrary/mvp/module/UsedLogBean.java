package com.yl.userlibrary.mvp.module;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

public class UsedLogBean extends BmobObject {
    /**
     * 记录使用app用户账户
     */
    private String used_userAcount;
    /**
     * 记录使用用户账户
     */
    private String used_username;
    /**
     * 记录使用用户操作类型（删除，修改，新增，删除）
     */
    private String used_type="0";
    /**
     * 记录用户使用操作模块
     */
    private String used_module;
    /**
     * 记录用户使用操作内容
     */
    private String used_content;

    public String getUsed_userAcount() {
        return used_userAcount;
    }

    public void setUsed_userAcount(String used_userAcount) {
        this.used_userAcount = used_userAcount;
    }

    public String getUsed_username() {
        return used_username;
    }

    public void setUsed_username(String used_username) {
        this.used_username = used_username;
    }

    public String getUsed_type() {
        return used_type;
    }

    public void setUsed_type(String used_type) {
        this.used_type = used_type;
    }

    public String getUsed_module() {
        return used_module;
    }

    public void setUsed_module(String used_module) {
        this.used_module = used_module;
    }

    public String getUsed_content() {
        return used_content;
    }

    public void setUsed_content(String used_content) {
        this.used_content = used_content;
    }

    public String getUsedTypeString(String type) {
        switch (type) {
            case "0":
                return "新增";

            case "1":
                return "修改";
            case "2":
                return "删除";
            case "3":
                return "查看";

        }
        return used_type;
    }


    public String getdesc() {
        return "用户'" + used_username + '\'' +
                ", 在"+ getCreatedAt() + '\'' +
                ", "+ getUsedTypeString(used_type) + '\'' +
                ", " + used_module + '\'';
    }
}
