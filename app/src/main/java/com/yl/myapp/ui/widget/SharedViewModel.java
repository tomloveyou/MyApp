package com.yl.myapp.ui.widget;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> datas=new MutableLiveData<>();

    public void setDatas(String s) {
        datas.setValue(s);
    }
}
