package com.yl.triplibrary.net.data.mvp.module;

public class TripClubEntity {
    private ImgInfoEntity club_icon;
    private String club_name;
    private String club_people_count;
    private String club_focus_people_count;
    private String club_tripline_count;
    public TripClubEntity(String img_url, String source_url, String goto_ur, String club_name, String club_people_count, String club_focus_people_count, String club_tripline_count) {
        this.club_icon =new ImgInfoEntity(img_url,source_url,goto_ur) ;
        this.club_name = club_name;
        this.club_people_count = club_people_count;
        this.club_focus_people_count = club_focus_people_count;
        this.club_tripline_count = club_tripline_count;
    }
    public TripClubEntity(ImgInfoEntity club_icon, String club_name, String club_people_count, String club_focus_people_count, String club_tripline_count) {
        this.club_icon = club_icon;
        this.club_name = club_name;
        this.club_people_count = club_people_count;
        this.club_focus_people_count = club_focus_people_count;
        this.club_tripline_count = club_tripline_count;
    }

    public ImgInfoEntity getClub_icon() {
        return club_icon;
    }

    public void setClub_icon(ImgInfoEntity club_icon) {
        this.club_icon = club_icon;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getClub_people_count() {
        return club_people_count;
    }

    public void setClub_people_count(String club_people_count) {
        this.club_people_count = club_people_count;
    }

    public String getClub_focus_people_count() {
        return club_focus_people_count;
    }

    public void setClub_focus_people_count(String club_focus_people_count) {
        this.club_focus_people_count = club_focus_people_count;
    }

    public String getClub_tripline_count() {
        return club_tripline_count;
    }

    public void setClub_tripline_count(String club_tripline_count) {
        this.club_tripline_count = club_tripline_count;
    }
}
