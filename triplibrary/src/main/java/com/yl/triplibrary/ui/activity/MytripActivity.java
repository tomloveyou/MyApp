package com.yl.triplibrary.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.standards.library.base.BaseTitleBarActivity;
import com.yl.triplibrary.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MytripActivity extends BaseTitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mytrip;
    }

    @Override
    protected void init() {
        setTitle("热点景区排行");
    }

    @Override
    protected void setListener() {

    }
}
