package com.yl.markremember.net;

import androidx.lifecycle.LiveData;



import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {


//
//    /**
//     * 验证当前用户信息
//     * @return 用户信息
//     */
//    @POST(Api.judgeUserStatus)
//    @FormUrlEncoded
//    LiveData<Result<UserInfo>> judgeUserStatus(@Field("userName") String userName, @Field("userNum") String userNum);
//    /**
//     * 获取当前app上线状态
//     * @return 上线状态
//     */
//    @GET(Api.appStatus)
//    LiveData<Result<AppStatus>> getAppStatusInfo();
//    /**
//     * 添加出行记录数据
//     * @param body 请求数据
//     * @return 请求成功返回的数据
//     */
//    @POST(Api.addMargieRecord)
//    LiveData<Result<Integer>> addMargieRecord(@Body RequestBody body);
//    /**
//     * 获取酒店入住记录以及用户基本信息
//     * @param userName 姓名
//     * @param userNum 身份证号
//     * @return 请求成功返回的数据
//     */
//    @POST(Api.getHotelRecordList)
//    @FormUrlEncoded
//    LiveData<Result<MargieRecordBean>> getMargieRecordList(@Field("userName") String userName, @Field("userNum") String userNum);
//    /**
//     * 添加出行记录数据
//     * @param body 请求数据
//     * @return 请求成功返回的数据
//     */
//    @POST(Api.createTripRecord)
//    LiveData<Result<Integer>> addTravelRecord(@Body RequestBody body);
//    /**
//     * 获取酒店入住记录以及用户基本信息
//     * @param userName 姓名
//     * @param userNum 身份证号
//     * @return 请求成功返回的数据
//     */
//    @POST(Api.getTripRecordList)
//    @FormUrlEncoded
//    LiveData<Result<TravelRecordBean>> getTravelRecordList(@Field("userName") String userName, @Field("userNum") String userNum);

}
