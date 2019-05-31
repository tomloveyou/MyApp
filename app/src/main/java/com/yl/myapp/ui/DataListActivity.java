package com.yl.myapp.ui;


import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.widget.EditText;

import com.standards.library.base.BaseTitleBarActivity;
import com.yl.myapp.R;

import com.yl.myapp.manager.DataViewModel;

public class DataListActivity extends BaseTitleBarActivity {

    private EditText mTextInput1;
    private EditText mTextInput2;
    private DataViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_input;
    }

    @Override
    protected void init() {
        initView();
    }

    @Override
    protected void setListener() {

    }

    private void initView() {
        mTextInput1 = (EditText) findViewById(R.id.text_input1);
        mTextInput2 = (EditText) findViewById(R.id.text_input2);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DataViewModel.class);
        viewModel.getCurrentName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mTextInput1.setText(s);
            }
        });
    }
}
