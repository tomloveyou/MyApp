package com.yl.triplibrary.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.standards.library.base.BaseFuncActivity;
import com.standards.library.base.BaseTitleBarActivity;
import com.yl.triplibrary.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MytripActivity extends BaseFuncActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_mytrip;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListener() {

    }
}
