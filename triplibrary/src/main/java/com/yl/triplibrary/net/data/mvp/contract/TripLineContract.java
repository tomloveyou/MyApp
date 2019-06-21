package com.yl.triplibrary.net.data.mvp.contract;

import com.standards.library.base.ILoadingView;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailEntity;
import com.yl.triplibrary.net.data.mvp.module.TripLineEntity;

import java.util.List;

public interface TripLineContract {
    public interface TripLineView extends ILoadingView {

        public void getTripLineData(List<TripLineEntity> data);
    }


}
