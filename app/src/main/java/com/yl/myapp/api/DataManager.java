package com.yl.myapp.api;

import com.standards.library.model.ListData;
import com.standards.library.model.Response;
import com.yl.myapp.bean.TestBean;
//import com.yl.myapp.ui.mvp.model.LoginStatus;

import java.util.List;
import java.util.Map;


import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import rx.Observable;

/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/7 13:55
 * @version: V1.0
 */
public class DataManager extends ResponseHandle {


    //获取所有彩票列表
    public static   Observable<List<TestBean>> queryData(){
        return Dao.getApiService().queryData()
                .flatMap(newEntityData())
                .compose(applySchedulersWithToken());
    }


}
