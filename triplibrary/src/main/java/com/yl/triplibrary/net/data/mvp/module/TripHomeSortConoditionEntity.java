package com.yl.triplibrary.net.data.mvp.module;

import java.util.List;

public class TripHomeSortConoditionEntity {
    private List<String>start_area;
    private List<String>start_month;
    private List<String>end_area;

    public List<String> getStart_area() {
        return start_area;
    }

    public void setStart_area(List<String> start_area) {
        this.start_area = start_area;
    }

    public List<String> getStart_month() {
        return start_month;
    }

    public void setStart_month(List<String> start_month) {
        this.start_month = start_month;
    }

    public List<String> getEnd_area() {
        return end_area;
    }

    public void setEnd_area(List<String> end_area) {
        this.end_area = end_area;
    }
}
