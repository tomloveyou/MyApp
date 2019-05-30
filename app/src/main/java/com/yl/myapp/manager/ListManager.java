package com.yl.myapp.manager;

import android.content.Context;

import com.standards.library.listview.manager.BaseGroupListManager;
import com.standards.library.model.ListData;
import com.yl.myapp.bean.ControlBean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class ListManager extends BaseGroupListManager<ControlBean> {
    private String id;

    public ListManager(String id) {
        this.id = id;
    }


    @Override
    protected Observable<ListData<ControlBean>> getData(Context context) {
        return getTestData().flatMap(this::translateToListData);
    }
    private Observable<ListData<ControlBean>> translateToListData(List<ControlBean> eventheadBean) {
        ListData<ControlBean> listData = new ListData();
        listData.list = eventheadBean;
        return Observable.just(listData);
    }

    private Observable<List<ControlBean>> getTestData() {
        return Observable.create(subscriber -> {
            List<ControlBean> cc = LitePal.where("PID = ?", id).find(ControlBean.class);

            subscriber.onNext(cc);
            subscriber.onCompleted();
        });
    }
}
