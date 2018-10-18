package com.yl.myapp.api;

import com.standards.library.model.ListData;
import com.standards.library.model.Response;

import java.util.List;


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
    public static Observable<ListData<String>> getTicketList() {
        return Dao.getApiService().getTicketList(null)
                .flatMap(newEntityData())
                .compose(applySchedulersWithToken());
    }


}
