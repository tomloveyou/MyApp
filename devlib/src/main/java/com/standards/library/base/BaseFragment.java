package com.standards.library.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;
import com.standards.library.BuildConfig;
import com.standards.library.R;
import com.standards.library.listview.loading.ProgressDialog;
import com.standards.library.util.LogUtil;
import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxFragment;


import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/7 11:11
 * @version: V1.0
 */
public abstract class BaseFragment extends RxFragment {
    private LinearLayout superView;
    private View view;
    private ProgressDialog progressDialog;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext=null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtra();
        superView = new LinearLayout(this.getActivity());
        superView.setOrientation(LinearLayout.VERTICAL);
        superView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(this.getActivity()).inflate(getLayoutId(), null);
        superView.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        init();
        setListener();
        return superView;
    }

    public Observable ClickView(View view) {
        return RxView.clicks(view).throttleFirst(BuildConfig.TIME_CLICK_IGNORE, TimeUnit.MILLISECONDS).compose(bindUntilEvent(FragmentEvent.DESTROY));
    }

    public void showLoadingDialog() {
        showLoadingDialog(this.getString(R.string.load_loading), false);
    }

    public void showLoadingDialog(String text) {
        showLoadingDialog(text, false);
    }

    public void showLoadingDialog(String loadText, boolean cancelable) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(getActivity(), loadText, cancelable);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        progressDialog.setLoadText(loadText);
    }

    public void closeLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    public void getExtra() {
    }

    protected <V extends View> V findView(int resId) {
        return (V) view.findViewById(resId);
    }

    public abstract int getLayoutId();

    public abstract void init();

    public abstract void setListener();

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("onDestroy");
    }
}
