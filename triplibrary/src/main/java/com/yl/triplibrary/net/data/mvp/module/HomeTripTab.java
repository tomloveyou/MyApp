package com.yl.triplibrary.net.data.mvp.module;

public class HomeTripTab {
    private String tab_title;
    private String tab_url;

    public HomeTripTab(String tab_title) {
        this.tab_title = tab_title;
    }

    public HomeTripTab(String tab_title, String tab_url) {
        this.tab_title = tab_title;
        this.tab_url = tab_url;
    }

    public String getTab_title() {
        return tab_title;
    }

    public void setTab_title(String tab_title) {
        this.tab_title = tab_title;
    }

    public String getTab_url() {
        return tab_url;
    }

    public void setTab_url(String tab_url) {
        this.tab_url = tab_url;
    }
}
