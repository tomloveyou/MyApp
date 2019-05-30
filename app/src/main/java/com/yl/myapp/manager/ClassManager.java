package com.yl.myapp.manager;

import android.content.Context;


import com.standards.library.listview.manager.BaseGroupListManager;
import com.standards.library.model.ListData;
import com.yl.myapp.ListTestActivity;
import com.yl.myapp.api.DataManager;
import com.yl.myapp.bean.ContentBean;
import com.yl.myapp.bean.TestBean;
import com.yl.myapp.ui.ListStyleActivity;


import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class ClassManager extends BaseGroupListManager<Class> {
    @Override
    protected Observable<ListData<Class>> getData(Context context) {
      //  return getTestData().flatMap(this::translateToListData);
        return getTestData().flatMap(this::translateToListData);
    }

    private Observable<ListData<Class>>translateToListData(List<Class> eventheadBean) {
        ListData<Class> listData = new ListData();
        listData.list=eventheadBean;

        return Observable.just(listData);
    }

    private Observable<List<Class>> getTestData() {
        return Observable.create(subscriber -> {
            List<Class> da = new ArrayList<>();
           da.add(ListTestActivity.class);
           da.add(ListStyleActivity.class);
            subscriber.onNext(da);
            subscriber.onCompleted();
        });
    }
}
