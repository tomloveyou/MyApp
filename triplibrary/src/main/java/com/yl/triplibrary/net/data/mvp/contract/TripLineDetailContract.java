package com.yl.triplibrary.net.data.mvp.contract;

import com.standards.library.base.ILoadingView;
import com.yl.triplibrary.net.data.mvp.module.TripLineEntity;
import com.yl.triplibrary.net.data.mvp.module.TripLinelDetailMainEntity;

import java.util.List;

public interface TripLineDetailContract {
    public interface TripLineDetailView extends ILoadingView {

        public void getTripLineDetailData(TripLinelDetailMainEntity data);
    }


}
