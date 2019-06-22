package com.yl.triplibrary.net.data.mvp.contract;

import com.standards.library.base.ILoadingView;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintEntity;
import com.yl.triplibrary.net.data.mvp.module.TripLineEntity;

import java.util.List;

public interface TripFootPrintContract {
    public interface TripFootPrintView extends ILoadingView {

        public void getFootPrinteData(List<TripFootPrintEntity> data);
    }


}
