package com.standards.library.base;

import com.standards.library.base.mvp.model.BaseModel;

/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/1 14:46
 * @version: V1.0
 */
public interface IPresenter<V extends ILoadingView, M extends BaseModel> {

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
