package com.yl.markremember.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.app.SkinAppCompatDelegateImpl;

import com.blankj.utilcode.util.BarUtils;
import com.standards.library.base.BaseFuncActivity;
import com.standards.library.base.BasePresenter;
import com.yl.markremember.R;

public abstract class MarkBaseActivity<T extends BasePresenter> extends BaseFuncActivity<T> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarTransparent(this);
    }

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        return SkinAppCompatDelegateImpl.get(this, this);
    }


}
