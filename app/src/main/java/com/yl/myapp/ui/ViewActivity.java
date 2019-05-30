package com.yl.myapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.standards.library.base.BaseTitleBarActivity;
import com.yl.myapp.R;

import com.yl.myapp.ui.widget.FloatButton;


public class ViewActivity extends BaseTitleBarActivity {


    private SeekBar mSeekbar1;
    private SeekBar mSeekbar2;
    private SeekBar mSeekbar3;
    private SeekBar mSeekbar4;
    private SeekBar mSeekbar5;
    private SeekBar mSeekbar6;
    private SeekBar mSeekbar7;
    private FloatButton mDiyView;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_view;
    }

    @Override
    protected void init() {
        setTitle("英雄联盟能力分析图");
        initView();
    }

    @Override
    protected void setListener() {

    }

    private void initView() {
        mSeekbar1 = (SeekBar) findViewById(R.id.seekbar1);
        mSeekbar2 = (SeekBar) findViewById(R.id.seekbar2);
        mSeekbar3 = (SeekBar) findViewById(R.id.seekbar3);
        mSeekbar4 = (SeekBar) findViewById(R.id.seekbar4);
        mSeekbar5 = (SeekBar) findViewById(R.id.seekbar5);
        mSeekbar6 = (SeekBar) findViewById(R.id.seekbar6);
        mSeekbar7 = (SeekBar) findViewById(R.id.seekbar7);
        mDiyView = (FloatButton) findViewById(R.id.diy_view);
        mSeekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDiyView.setValues(progress,mSeekbar2.getProgress(),mSeekbar3.getProgress(),mSeekbar4.getProgress(),mSeekbar5.getProgress(),mSeekbar6.getProgress(),mSeekbar7.getProgress());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDiyView.setValues(mSeekbar1.getProgress(),progress,mSeekbar3.getProgress(),mSeekbar4.getProgress(),mSeekbar5.getProgress(),mSeekbar6.getProgress(),mSeekbar7.getProgress());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekbar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDiyView.setValues(mSeekbar1.getProgress(),mSeekbar2.getProgress(),progress,mSeekbar4.getProgress(),mSeekbar5.getProgress(),mSeekbar6.getProgress(),mSeekbar7.getProgress());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekbar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDiyView.setValues(mSeekbar1.getProgress(),mSeekbar2.getProgress(),mSeekbar3.getProgress(),progress,mSeekbar5.getProgress(),mSeekbar6.getProgress(),mSeekbar7.getProgress());
             
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekbar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDiyView.setValues(mSeekbar1.getProgress(),mSeekbar2.getProgress(),mSeekbar3.getProgress(),mSeekbar4.getProgress(),progress,mSeekbar6.getProgress(),mSeekbar7.getProgress());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekbar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDiyView.setValues(mSeekbar1.getProgress(),mSeekbar2.getProgress(),mSeekbar3.getProgress(),mSeekbar4.getProgress(),mSeekbar5.getProgress(),progress,mSeekbar7.getProgress());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekbar7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mDiyView.setValues(mSeekbar1.getProgress(),mSeekbar2.getProgress(),mSeekbar3.getProgress(),mSeekbar4.getProgress(),mSeekbar5.getProgress(),mSeekbar6.getProgress(),progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
