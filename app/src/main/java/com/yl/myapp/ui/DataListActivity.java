package com.yl.myapp.ui;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import com.standards.library.base.BaseTitleBarActivity;
import com.standards.library.listview.adapter.LoadMoreRecycleAdapter;
import com.standards.library.listview.listview.XRecycleListViewImpl;
import com.standards.library.listview.manager.BaseGroupListManager;
import com.standards.library.util.ToastUtil;
import com.yl.myapp.MainActivity;
import com.yl.myapp.R;

import com.yl.myapp.bean.ControlBean;
import com.yl.myapp.manager.DataViewModel;
import com.yl.myapp.manager.ListManager;

import org.litepal.LitePal;

import java.util.List;

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
