package com.yl.triplibrary.net.data.mvp.module;

public class TripCurrentDayInfoEntity {
    /*标题*/
    private String trip_current_day_info_title;
    /*用餐*/
    private String trip_current_day_info_dining;
    /*住宿*/
    private String trip_current_day_info_accommodation;
    /*车程*/
    private String trip_current_day_info_drive;
    /*行程介绍*/
    private String trip_current_day_info_desc;

    /**
     *
     * @param trip_current_day_info_title 标题
     * @param trip_current_day_info_dining 用餐
     * @param trip_current_day_info_accommodation 住宿
     * @param trip_current_day_info_drive 车程
     * @param trip_current_day_info_desc  行程介绍
     */
    public TripCurrentDayInfoEntity(String trip_current_day_info_title, String trip_current_day_info_dining, String trip_current_day_info_accommodation, String trip_current_day_info_drive, String trip_current_day_info_desc) {
        this.trip_current_day_info_title = trip_current_day_info_title;
        this.trip_current_day_info_dining = trip_current_day_info_dining;
        this.trip_current_day_info_accommodation = trip_current_day_info_accommodation;
        this.trip_current_day_info_drive = trip_current_day_info_drive;
        this.trip_current_day_info_desc = trip_current_day_info_desc;
    }

    public String getTrip_current_day_info_title() {
        return trip_current_day_info_title;
    }

    public void setTrip_current_day_info_title(String trip_current_day_info_title) {
        this.trip_current_day_info_title = trip_current_day_info_title;
    }

    public String getTrip_current_day_info_dining() {
        return trip_current_day_info_dining;
    }

    public void setTrip_current_day_info_dining(String trip_current_day_info_dining) {
        this.trip_current_day_info_dining = trip_current_day_info_dining;
    }

    public String getTrip_current_day_info_accommodation() {
        return trip_current_day_info_accommodation;
    }

    public void setTrip_current_day_info_accommodation(String trip_current_day_info_accommodation) {
        this.trip_current_day_info_accommodation = trip_current_day_info_accommodation;
    }

    public String getTrip_current_day_info_drive() {
        return trip_current_day_info_drive;
    }

    public void setTrip_current_day_info_drive(String trip_current_day_info_drive) {
        this.trip_current_day_info_drive = trip_current_day_info_drive;
    }

    public String getTrip_current_day_info_desc() {
        return trip_current_day_info_desc;
    }

    public void setTrip_current_day_info_desc(String trip_current_day_info_desc) {
        this.trip_current_day_info_desc = trip_current_day_info_desc;
    }
}
