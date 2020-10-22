package com.standards.library.base.mvp.presenter;

import com.blankj.utilcode.util.LogUtils;
import com.standards.library.base.mvp.model.BaseModel;
import com.standards.library.base.mvp.contract.IBaseContract;

/**
 * @author huangnan
 */

public abstract class BasePresenter<V extends IBaseContract.IView, M extends BaseModel> implements IBaseContract.IPresenter<V, M> {
    private static String TAG = "BasePresenter";
    private V mView;
    private M mModel;

    @Override
    public void attachView(V view) {
        this.mView = view;
        this.mModel = createModel();
    }

    protected abstract M createModel();

    protected M getModel() {
        return mModel;
    }

    public V getView() {
        return this.mView;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            this.mView = null;
        }
        if (mModel != null) {
            this.mModel.detach();
        }
        LogUtils.i(TAG, "*************************已解除绑定***************************");
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }
}
