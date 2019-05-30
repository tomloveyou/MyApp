package com.yl.myapp.ui.widget;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String>datas=new MutableLiveData<>();

    public void setDatas(String s) {
        datas.setValue(s);
    }
}
