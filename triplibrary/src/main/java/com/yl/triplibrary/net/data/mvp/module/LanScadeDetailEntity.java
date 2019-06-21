package com.yl.triplibrary.net.data.mvp.module;

import java.util.List;

public class LanScadeDetailEntity {
    private LanScadeDetailHeadEntity headEntity;
   private List<LanScadeDetailBodyEntity> detailBodyEntity;
    private String foot_recommnet_title;
    private List<LanScadeRecomentEntity>recomentEntityList;

    public List<LanScadeDetailBodyEntity> getDetailBodyEntity() {
        return detailBodyEntity;
    }

    public void setDetailBodyEntity(List<LanScadeDetailBodyEntity> detailBodyEntity) {
        this.detailBodyEntity = detailBodyEntity;
    }

    public List<LanScadeRecomentEntity> getRecomentEntityList() {
        return recomentEntityList;
    }

    public void setRecomentEntityList(List<LanScadeRecomentEntity> recomentEntityList) {
        this.recomentEntityList = recomentEntityList;
    }

    public LanScadeDetailHeadEntity getHeadEntity() {
        return headEntity;
    }

    public void setHeadEntity(LanScadeDetailHeadEntity headEntity) {
        this.headEntity = headEntity;
    }



    public String getFoot_recommnet_title() {
        return foot_recommnet_title;
    }

    public void setFoot_recommnet_title(String foot_recommnet_title) {
        this.foot_recommnet_title = foot_recommnet_title;
    }
}
