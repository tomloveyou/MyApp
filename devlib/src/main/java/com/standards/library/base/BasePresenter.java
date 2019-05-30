package com.standards.library.base;

import android.app.Activity;

import com.standards.library.rx.CSubscriber;
import com.standards.library.rx.ErrorThrowable;

import java.lang.ref.WeakReference;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/6/27 12:54
 * @version: V1.0
 */
public abstract class BasePresenter<T extends ILoadingView> implements IPresenter {
    protected T mView;
    protected WeakReference<Activity> mActivity;
    protected CompositeSubscription mCompositeSubscription;

    public BasePresenter(Activity activity) {
        this.mView = (T) activity;
        this.mActivity = new WeakReference<>(activity);
    }

    public BasePresenter(T view, Activity activity) {
        mView = view;
        this.mActivity = new WeakReference<>(activity);
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 因为在请求过程中用户可能会进行其他操作销毁当前页面，
     * 这边必须要通过addSubscribe 来做请求，防止页面销毁后订阅者
     * 的生命周期没结束抛异常
     *
     * @param subscription
     */
    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    protected <T> Subscriber<T> getSubscriber(final OnSubscribeSuccess<T> onSubscribeSuccess) {
        return new CSubscriber<T>() {
            @Override
            public void onPrepare() {
                mView.showLoading();
            }

            @Override
            public void onError(ErrorThrowable throwable) {
                mView.hideLoading();
                mView.showError(throwable);
            }

            @Override
            public void onSuccess(T t) {
                mView.hideLoading();
                onSubscribeSuccess.onSuccess(t);
            }
        };
    }

    protected <T> Subscriber<T> getSubscriberNoProgress(final OnSubscribeSuccess<T> onSubscribeSuccess) {
        return new CSubscriber<T>() {
            @Override
            public void onPrepare() {
            }

            @Override
            public void onError(ErrorThrowable throwable) {
                mView.showError(throwable);
            }

            @Override
            public void onSuccess(T t) {
                onSubscribeSuccess.onSuccess(t);
            }
        };
    }

    @Override
    public void detachView() {
        this.mView = null;
        mActivity = null;
        unSubscribe();
    }
}
