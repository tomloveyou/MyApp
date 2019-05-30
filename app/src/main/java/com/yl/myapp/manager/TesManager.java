package com.yl.myapp.manager;

import android.content.Context;

import com.standards.library.listview.manager.BaseGroupListManager;
import com.standards.library.model.ListData;
import com.yl.myapp.api.DataManager;
import com.yl.myapp.bean.ContentBean;
import com.yl.myapp.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class TesManager extends BaseGroupListManager<ContentBean> {
    @Override
    protected Observable<ListData<ContentBean>> getData(Context context) {
      //  return getTestData().flatMap(this::translateToListData);
        return DataManager.queryData().flatMap(this::translateToListData);
    }

    private Observable<ListData<ContentBean>>translateToListData(List<TestBean> eventheadBean) {
        ListData<ContentBean> listData = new ListData();
        List<ContentBean>data=new ArrayList<>();
        for (TestBean testBean:eventheadBean){
            List<ContentBean>da=testBean.getContent();
            if (da!=null){
                data.addAll(da);
            }
        }
        listData.list = data;
        return Observable.just(listData);
    }

    private Observable<List<String>> getTestData() {
        return Observable.create(subscriber -> {
            List<String> da = new ArrayList<>();
            for (int i = 1; i < 12; i++) {

                da.add("测试事件（" + i + ")");
            }
            subscriber.onNext(da);
            subscriber.onCompleted();
        });
    }
    private Observable<List<String>> getEmptyTestData() {
        return Observable.create(subscriber -> {
            List<String> da = new ArrayList<>();

            subscriber.onNext(da);
            subscriber.onCompleted();
        });
    }
}
