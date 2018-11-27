package com.yl.myapp.ui;

import android.view.Menu;
import android.widget.Button;

import com.yl.myapp.R;
import com.yl.myapp.base.BaseTitleBarActivity;

import java.util.Random;

import rx.functions.Action1;

public class TwoImgMenuActivity extends BaseTitleBarActivity {


    private Button mButton;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void init() {
        setTitle("标题");
        mButton = (Button) findViewById(R.id.button);
    }

    @Override
    protected void setListener() {
        ClickView(mButton).subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Random random=new Random();
                setTitle("ceshijiji"+random.nextInt());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.two_img, menu);
        return true;
    }
}
