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
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * <请描述这个类是干什么的>
 *
 * @author caiyk@cncn.com
 * @data: 2016/7/7 11:28
 * @version: V1.0
 */
public interface ApiService {

    /**
     * 测试
     *
     * @param
     * @return
     */
    @POST("musicRankings")
    Observable<Response<List<TestBean>>> queryData();


}

