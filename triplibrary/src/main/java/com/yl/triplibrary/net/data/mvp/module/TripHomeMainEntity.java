package com.yl.triplibrary.net.data.mvp.module;

import com.yl.triplibrary.net.data.manager.entity.RankTripZone;

import java.util.List;

public class TripHomeMainEntity {
    private List<ImaInfoTitleEntity>banners_list;
    private List<ImaInfoTitleEntity>grid_buttons_list;
    private TripHomeSortConoditionEntity conoditionEntity;
    private List<TripHomeRecommnetEntity>recomment_list;

    public List<ImaInfoTitleEntity> getBanners_list() {
        return banners_list;
    }

    public void setBanners_list(List<ImaInfoTitleEntity> banners_list) {
        this.banners_list = banners_list;
    }

    public List<ImaInfoTitleEntity> getGrid_buttons_list() {
        return grid_buttons_list;
    }

    public void setGrid_buttons_list(List<ImaInfoTitleEntity> grid_buttons_list) {
        this.grid_buttons_list = grid_buttons_list;
    }

    public TripHomeSortConoditionEntity getConoditionEntity() {
        return conoditionEntity;
    }

    public void setConoditionEntity(TripHomeSortConoditionEntity conoditionEntity) {
        this.conoditionEntity = conoditionEntity;
    }

    public List<TripHomeRecommnetEntity> getRecomment_list() {
        return recomment_list;
    }

    public void setRecomment_list(List<TripHomeRecommnetEntity> recomment_list) {
        this.recomment_list = recomment_list;
    }
}
