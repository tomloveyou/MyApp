package com.yl.triplibrary.net.data.mvp.contract;

import com.standards.library.base.ILoadingView;
import com.yl.triplibrary.net.data.mvp.module.TripFootPrintEntity;
import com.yl.triplibrary.net.data.mvp.module.TripWenDaEntity;

import java.util.List;

public interface TripWenDaContract {
    public interface TripWenDaView extends ILoadingView {

        public void getWenDaData(List<TripWenDaEntity> data);
    }


}
