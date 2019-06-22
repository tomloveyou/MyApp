package com.yl.triplibrary.net.data.mvp.module;

import java.util.List;

public class TripCurrentDayItemInfoEntity {
    private TripCurrentDayInfoEntity currentDayInfoEntity;
    private List<TripTitleContentImgEntity> tripTitleContentImgEntity;

    public TripCurrentDayItemInfoEntity() {
    }

    public TripCurrentDayInfoEntity getCurrentDayInfoEntity() {
        return currentDayInfoEntity;
    }

    public void setCurrentDayInfoEntity(TripCurrentDayInfoEntity currentDayInfoEntity) {
        this.currentDayInfoEntity = currentDayInfoEntity;
    }

    public List<TripTitleContentImgEntity> getTripTitleContentImgEntity() {
        return tripTitleContentImgEntity;
    }

    public void setTripTitleContentImgEntity(List<TripTitleContentImgEntity> tripTitleContentImgEntity) {
        this.tripTitleContentImgEntity = tripTitleContentImgEntity;
    }
}
