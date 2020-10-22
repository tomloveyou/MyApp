package com.standards.library.base.mvp;

import com.standards.library.base.BaseActivity;
import com.standards.library.base.mvp.contract.IBaseContract;
import com.standards.library.base.mvp.presenter.BasePresenter;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements IBaseContract.IView  {
    private P presenter;


    /**
     * 创建Presenter类
     *
     * @return BasePresenter
     */
    public abstract P createPresenter();
    /**
     * 初始化 com.xinchao.dcrm.saletools.com.xinchao.dcrm.ssp.presenter
     */
    private void initPresenter() {
        presenter = createPresenter();
        attach();
    }

    @Override
    protected void init() {
        initPresenter();
    }

    @SuppressWarnings("unchecked")
    private void attach() {
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    private void detach() {
        if (presenter != null) {
            presenter.detachView();
        }
    }
    @Override protected void onDestroy() {
        super.onDestroy();
        dismissLoading();
        detach();
    }

    public P getPresenter() {
        return this.presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showToast(String msg) {

    }
}
