package com.yl.triplibrary.net.data.mvp.contract;

import com.standards.library.base.ILoadingView;
import com.yl.triplibrary.net.data.mvp.module.TripLineEntity;
import com.yl.triplibrary.net.data.mvp.module.TripStrategyEntity;

import java.util.List;

public interface TripStrategyContract {
    public interface TTripStrategyView extends ILoadingView {

        public void getTripStrategyData(List<TripStrategyEntity> data);
    }


}
