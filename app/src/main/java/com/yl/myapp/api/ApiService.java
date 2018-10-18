package com.yl.myapp.api;


import com.standards.library.model.ListData;
import com.standards.library.model.Response;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
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
     * 查询支持彩票类别
     *
     * @param extra
     * @return
     */
    @FormUrlEncoded
    @POST("44-6")
    Observable<Response<ListData<String>>> getTicketList(@Field("extra") String extra);


}

