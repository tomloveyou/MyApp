package com.standards.library.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.standards.library.model.Event;
import com.standards.library.rx.ErrorThrowable;
import com.standards.library.util.ToastUtil;
import com.standards.library.util.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


/**
 * <这一层是对BaseActivity的进一步抽象，
 * 1. 实现了Presenter的部分方法showloading,showError等，
 * 2. EventBus注册解除注册>
 *
 * @data: 2016/7/7 11:11
 * @version: V1.0
 */
public abstract class BaseFuncActivity<T extends BasePresenter> extends BaseActivity implements ILoadingView {
    protected T mPresenter;
    private List<EditText> editTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPresenter = getPresenter();
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        getBaseContentView().setOnClickListener(v -> hideAllKeyBoard());
        getEditTextView((ViewGroup) getBaseContentView());
    }
    /**
     * 设置状态栏全透明
     * **/
    protected static void setStatusBarTransparent(Activity activity){
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 5.0 以上全透明状态栏
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏 加下面几句可以去除透明状态栏的灰色阴影,实现纯透明
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            //6.0 以上可以设置状态栏的字体为黑色.使用下面注释的这行打开亮色状态栏模式,实现黑色字体,白底的需求用这句setStatusBarColor(Color.WHITE);
            //window.getDecorView().setSystemUiVisibility(
            //                   View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(Color.TRANSPARENT);

        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){//4.4 全透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void getExtra() {

    }

    private void getEditTextView(ViewGroup viewGroup) {
        if (editTexts == null) editTexts = new ArrayList<>();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof EditText) {
                editTexts.add((EditText) view);
            }
            if (view instanceof ViewGroup) {
                getEditTextView((ViewGroup) view);
            }
        }

    }

    public T getPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showError(ErrorThrowable errorThrowable) {
        errorThrowable.printStackTrace();
        closeLoadingDialog();
        ToastUtil.showToast(errorThrowable.msg);
    }

    @Subscribe
    public void logout(Event event) {

    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        closeLoadingDialog();
    }

    public void hideAllKeyBoard() {
        if (editTexts == null) return;
        for (EditText editText : editTexts) {
            Utils.hideInputKeyboard(editText);
        }
    }


}
