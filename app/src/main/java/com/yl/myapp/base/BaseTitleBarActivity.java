package com.yl.myapp.base;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yl.myapp.R;
import com.yl.myapp.ui.utils.StatusBarValue;


/**
 * 带titleBar的Activity抽象
 *
 * @param <T>
 */
public abstract class BaseTitleBarActivity<T extends BasePresenter> extends BaseFuncActivity<T> {
    protected Toolbar mToolbar;

    @Override
    public void setContentView(View contentView) {
        ViewGroup superContentView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_base, (ViewGroup) contentView, false);
        mToolbar = (Toolbar) superContentView.findViewById(R.id.toolbar);
        mToolbar.setTitle("");//先置空后面设置title才有用
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RelativeLayout content = (RelativeLayout) superContentView.findViewById(R.id.content);
        content.addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        super.setContentView(superContentView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public void  setTitle(String title){
        mToolbar.setTitle(title);
    }


    @Override
    public StatusBarValue getStatusBar() {
        return new StatusBarValue(true, R.color.main_hint_color);
    }
}
