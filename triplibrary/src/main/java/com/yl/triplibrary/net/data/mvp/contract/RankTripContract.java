package com.yl.triplibrary.net.data.mvp.contract;

import com.standards.library.base.ILoadingView;
import com.standards.library.base.IPresenter;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;

import java.util.List;

public interface RankTripContract {
    public interface RankTripView extends ILoadingView {

        public void getRankTrip(List<RankTripZone> data);
    }

    interface RankTripPresenter extends IPresenter {
        void getData();
    }
}
