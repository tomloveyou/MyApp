package com.yl.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;


import com.standards.library.base.BaseTitleBarActivity;
import com.yl.myapp.R;


import java.util.Random;

import rx.functions.Action1;

public class TestActivity extends BaseTitleBarActivity {


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
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
