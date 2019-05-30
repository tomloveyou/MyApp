package com.yl.myapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import com.standards.library.base.BaseTitleBarActivity;
import com.yl.myapp.R;


import java.util.Random;

import rx.functions.Action1;

public class MenuActivity extends BaseTitleBarActivity {


    private Button mButton;
    private int menuType = 0;

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
    public void getExtra() {
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            menuType=bundle.getInt("menuType",0);
        }else {
            menuType= getIntent().getIntExtra("menuType",0);
        }
    }

    @Override
    protected void setListener() {
        ClickView(mButton).subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Random random = new Random();
                setTitle("ceshijiji" + random.nextInt());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (menuType){
            case 0:
                getMenuInflater().inflate(R.menu.one_img, menu);
                break;
            case 1:
                getMenuInflater().inflate(R.menu.two_img, menu);
                break;
            case 2:
                getMenuInflater().inflate(R.menu.one, menu);
                break;
            case 3:
                getMenuInflater().inflate(R.menu.two, menu);
                break;
        }

        return true;
    }
}
