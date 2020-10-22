package com.standards.library.base.mvp.contract;

import com.standards.library.base.mvp.model.BaseModel;

public interface IBaseContract {

    interface IPresenter<V extends IView, M extends BaseModel> {

        /**
         * 依附生命view
         *
         * @param view 绑定activity
         */
        void attachView(V view);

        /**
         * 分离View
         */
        void detachView();

        /**
         * 判断View是否已经销毁
         *
         * @return boolean 是否被回收
         */
        boolean isViewAttached();
    }

    interface IView {

        void showLoading();

        void dismissLoading();

        void showToast(String msg);
    }
}
