package com.yl.triplibrary.net.data.mvp.module;

import com.yl.triplibrary.net.data.manager.entity.RankTripZone;

import java.util.List;

public class TripHomeRecommnetEntity extends ImgInfoEntity{
    private String title;
    private String time_info;
    private  String people_info;
    private String price_info;
    private String start_area_info;
    private String buyed_inf;

    public TripHomeRecommnetEntity(String title, String time_info, String people_info, String price_info, String start_area_info, String buyed_inf) {
        this.title = title;
        this.time_info = time_info;
        this.people_info = people_info;
        this.price_info = price_info;
        this.start_area_info = start_area_info;
        this.buyed_inf = buyed_inf;
    }

    public TripHomeRecommnetEntity(String title, String time_info, String people_info, String price_info, String start_area_info, String buyed_inf,String img_url, String source_url, String goto_ur) {
        super(img_url, source_url, goto_ur);
        this.title = title;
        this.time_info = time_info;
        this.people_info = people_info;
        this.price_info = price_info;
        this.start_area_info = start_area_info;
        this.buyed_inf = buyed_inf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
