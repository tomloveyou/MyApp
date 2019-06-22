package com.yl.triplibrary.net.data.mvp.module;

import java.util.List;

public class TripLinelDetailMainEntity {
    private ImaInfoTitleEntity titleHeadEntity;
    private List<PreBookInfoEntity> preBookInfoEntityList;
    private TripClubEntity tripClubEntity;
    private String trip_line_features;
    private List<TripProfileEntity> tripProfileEntities;
    private List<TripCurrentDayItemInfoEntity> tripCurrentDayItemInfoEntityList;
    private List<String> cost_info_list;
    private List<String> tip_info_list;

    public ImaInfoTitleEntity getTitleHeadEntity() {
        return titleHeadEntity;
    }

    public void setTitleHeadEntity(ImaInfoTitleEntity titleHeadEntity) {
        this.titleHeadEntity = titleHeadEntity;
    }

    public List<PreBookInfoEntity> getPreBookInfoEntityList() {
        return preBookInfoEntityList;
    }

    public void setPreBookInfoEntityList(List<PreBookInfoEntity> preBookInfoEntityList) {
        this.preBookInfoEntityList = preBookInfoEntityList;
    }

    public TripClubEntity getTripClubEntity() {
        return tripClubEntity;
    }

    public void setTripClubEntity(TripClubEntity tripClubEntity) {
        this.tripClubEntity = tripClubEntity;
    }

    public String getTrip_line_features() {
        return trip_line_features;
    }

    public void setTrip_line_features(String trip_line_features) {
        this.trip_line_features = trip_line_features;
    }

    public List<TripProfileEntity> getTripProfileEntities() {
        return tripProfileEntities;
    }

    public void setTripProfileEntities(List<TripProfileEntity> tripProfileEntities) {
        this.tripProfileEntities = tripProfileEntities;
    }

    public List<TripCurrentDayItemInfoEntity> getTripCurrentDayItemInfoEntityList() {
        return tripCurrentDayItemInfoEntityList;
    }

    public void setTripCurrentDayItemInfoEntityList(List<TripCurrentDayItemInfoEntity> tripCurrentDayItemInfoEntityList) {
        this.tripCurrentDayItemInfoEntityList = tripCurrentDayItemInfoEntityList;
    }

    public List<String> getCost_info_list() {
        return cost_info_list;
    }

    public void setCost_info_list(List<String> cost_info_list) {
        this.cost_info_list = cost_info_list;
    }

    public List<String> getTip_info_list() {
        return tip_info_list;
    }

    public void setTip_info_list(List<String> tip_info_list) {
        this.tip_info_list = tip_info_list;
    }
}
