package com.yl.triplibrary.net.data.mvp.contract;

import com.standards.library.base.ILoadingView;
import com.standards.library.base.IPresenter;
import com.yl.triplibrary.net.data.manager.entity.RankTripZone;
import com.yl.triplibrary.net.data.mvp.module.HomeTripTab;

import java.util.List;

public interface HomeTripContract {
    public interface HomeTripView extends ILoadingView {

        public void getHomeTripTrip(List<HomeTripTab> data);
    }

    interface HomeTripPresenter extends IPresenter {
        void getData();
    }
}
