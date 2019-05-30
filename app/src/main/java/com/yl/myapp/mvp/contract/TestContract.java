package com.yl.myapp.mvp.contract;


import com.standards.library.base.ILoadingView;
import com.standards.library.base.IPresenter;


import java.util.List;

public interface TestContract {
    public interface TestView extends ILoadingView {
        public List<String> getAllDatas();

        public List<String> getGrepDatas(String data);
    }

    interface TestPresenter extends IPresenter {
        void getData();
    }
}
