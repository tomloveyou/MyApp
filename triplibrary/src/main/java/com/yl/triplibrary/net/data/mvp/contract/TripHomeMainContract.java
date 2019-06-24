package com.yl.triplibrary.net.data.mvp.contract;

import com.standards.library.base.ILoadingView;
import com.standards.library.base.IPresenter;
import com.yl.triplibrary.net.data.mvp.module.HomeTripTab;
import com.yl.triplibrary.net.data.mvp.module.TripHomeMainEntity;

import java.util.List;

public interface TripHomeMainContract {
    public interface TripHomeMainView extends ILoadingView {

        public void getTripHomeMain(TripHomeMainEntity data);
    }


}
