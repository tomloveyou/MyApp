package com.yl.triplibrary.net.data.mvp.contract;

import com.standards.library.base.ILoadingView;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintDetaiEntity;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintEntity;

import java.util.List;

public interface TripFootPrintDetailContract {
    public interface TripFootPrintDetailView extends ILoadingView {

        public void getFootPrinteDetailData(TripFootPrintDetaiEntity data);
    }


}
