package com.yl.myapp.bean;

import java.io.Serializable;

public class JobBean implements Serializable{

    /**
     * id : 7
     * sysUserId : 1
     * sysRoleOrganizationId : 2
     * rank : 0
     * createTime : 1477928954000
     * updateTime : 1477929234000
     * createBy : 0
     * updateBy : 0
     * status : 1
     * isFinal : 2
     */

    private int id;
    private int sysUserId;
    private int sysRoleOrganizationId;
    private int rank;
    private long createTime;
    private long updateTime;
    private int createBy;
    private int updateBy;
    private int status;
    private int isFinal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(int sysUserId) {
        this.sysUserId = sysUserId;
    }

    public int getSysRoleOrganizationId() {
        return sysRoleOrganizationId;
    }

    public void setSysRoleOrganizationId(int sysRoleOrganizationId) {
        this.sysRoleOrganizationId = sysRoleOrganizationId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(int isFinal) {
        this.isFinal = isFinal;
    }
}
