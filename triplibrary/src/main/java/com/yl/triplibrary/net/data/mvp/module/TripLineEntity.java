package com.yl.triplibrary.net.data.mvp.module;

public class TripLineEntity extends ImgInfoEntity {
    private String title;
    private String go_start_eral;
    private String people_count;
    private String people_price_unit_info;
    private String publish_time;

    public TripLineEntity(String title, String go_start_eral,String people_count, String people_price_unit_info) {
        this.title = title;
        this.go_start_eral = go_start_eral;
        this.people_price_unit_info = people_price_unit_info;
    }

    public TripLineEntity(String img_url, String source_url, String goto_ur, String title, String go_start_eral,String people_count, String people_price_unit_info) {
        super(img_url, source_url, goto_ur);
        this.title = title;
        this.go_start_eral = go_start_eral;
        this.people_price_unit_info = people_price_unit_info;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getPeople_count() {
        return people_count;
    }

    public void setPeople_count(String people_count) {
        this.people_count = people_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGo_start_eral() {
        return go_start_eral;
    }

    public void setGo_start_eral(String go_start_eral) {
        this.go_start_eral = go_start_eral;
    }

    public String getPeople_price_unit_info() {
        return people_price_unit_info;
    }

    public void setPeople_price_unit_info(String people_price_unit_info) {
        this.people_price_unit_info = people_price_unit_info;
    }
}
