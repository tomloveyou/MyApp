package com.yl.triplibrary.net.data.mvp.module;

import java.util.List;

public class TripHomeSortConoditionEntity {
    private List<ImaInfoTitleEntity>start_area;
    private List<ImaInfoTitleEntity>start_month;
    private List<ImaInfoTitleEntity>end_area;

    public List<ImaInfoTitleEntity> getStart_area() {
        return start_area;
    }

    public void setStart_area(List<ImaInfoTitleEntity> start_area) {
        this.start_area = start_area;
    }

    public List<ImaInfoTitleEntity> getStart_month() {
        return start_month;
    }

    public void setStart_month(List<ImaInfoTitleEntity> start_month) {
        this.start_month = start_month;
    }

    public List<ImaInfoTitleEntity> getEnd_area() {
        return end_area;
    }

    public void setEnd_area(List<ImaInfoTitleEntity> end_area) {
        this.end_area = end_area;
    }
}
