package com.yl.markremember.db.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserInfo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    /**
     *用户姓名
     */
    @ColumnInfo(name = "userName")
    private String userName;
    /**
     *性别（只填： 男 或 女）
     */
    @ColumnInfo(name = "userSex")
    private String userSex;
    /**
     *出生日期（如：20190215）
     */
    @ColumnInfo(name = "userAge")
    private String userAge;
    /**
     *身份证
     */
    @ColumnInfo(name = "userNum")
    private String userNum;
    /**
     *用户籍贯
     */
    @ColumnInfo(name = "userAddress")
    private String userAddress;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
