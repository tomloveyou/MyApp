package com.yl.triplibrary.net.data.mvp.contract;

import com.standards.library.base.ILoadingView;
import com.standards.library.base.IPresenter;
import com.yl.triplibrary.net.data.mvp.module.HomeTripTab;
import com.yl.triplibrary.net.data.mvp.module.LanScadeDetailEntity;

import java.util.List;

public interface LanScadeDeailContract {
    public interface LanScadeDeailView extends ILoadingView {

        public void getLanScadeDeailData(LanScadeDetailEntity data);
    }


}
