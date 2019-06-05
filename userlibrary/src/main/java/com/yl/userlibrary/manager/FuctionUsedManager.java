package com.yl.userlibrary.manager;

import android.content.Context;

import com.standards.library.app.ReturnCode;
import com.standards.library.listview.manager.BaseGroupListManager;
import com.standards.library.model.BaseInfo;
import com.standards.library.model.ListData;
import com.standards.library.model.Response;
import com.standards.library.rx.ErrorThrowable;

import com.yl.userlibrary.mvp.module.UsedLogBean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import rx.Observable;
import rx.Observer;
import rx.functions.Func1;
import rx.observables.AsyncOnSubscribe;

public class FuctionUsedManager extends BaseGroupListManager<UsedLogBean> {
    @Override
    protected Observable<ListData<UsedLogBean>> getData(Context context) {
        return getData().flatMap(this::translateToListData);


    }

    private Observable<ListData<UsedLogBean>>translateToListData(List<UsedLogBean> eventheadBean) {
        ListData<UsedLogBean> listData = new ListData();
        listData.list = eventheadBean;
        return Observable.just(listData);
    }


    private Observable<List<UsedLogBean>> getData() {
        return Observable.create(subscriber -> {
            BmobQuery<UsedLogBean>query=new BmobQuery<>();
            List<UsedLogBean> da = new ArrayList<>();
            query.findObjects(new FindListener<UsedLogBean>() {
                @Override
                public void done(List<UsedLogBean> list, BmobException e) {
                    da.addAll(list);
                    subscriber.onNext(da);
                    subscriber.onCompleted();
                }
            });

        });
    }
    private Observable<List<UsedLogBean>> getEmptyTestData() {
        return Observable.create(subscriber -> {
            List<UsedLogBean> da = new ArrayList<>();
            for (int i=0;i<10;i++){
                UsedLogBean logBean=new UsedLogBean();
                logBean.setUsed_content("da"+i);
                da.add(logBean);
            }

            subscriber.onNext(da);
            subscriber.onCompleted();
        });
    }
}
