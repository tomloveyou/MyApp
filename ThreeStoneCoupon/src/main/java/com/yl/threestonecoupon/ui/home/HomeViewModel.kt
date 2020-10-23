package com.yl.threestonecoupon.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.yl.threestonecoupon.api.Api.PRODUCT_LIST
import com.yl.threestonecoupon.bean.rep.BaseRep
import com.yl.threestonecoupon.bean.rep.BaseResponse
import com.yl.threestonecoupon.bean.rep.PgeRep
import com.yl.threestonecoupon.bean.rep.RepGoodListBean
import com.yl.threestonecoupon.bean.req.ReqGoodListBean
import com.yl.threestonecoupon.ui.net.BaseReq
import com.yl.threestonecoupon.ui.net.JsonCallback
import java.lang.reflect.Type


class HomeViewModel : ViewModel() {
    val goods: MutableLiveData<List<RepGoodListBean>> = MutableLiveData<List<RepGoodListBean>>()

    init {
        queryGoodListByCondition()
    }

    public fun queryGoodListByCondition(condition: ReqGoodListBean? = ReqGoodListBean()) {
        OkGo.get<BaseResponse<PgeRep<RepGoodListBean>?>?>(PRODUCT_LIST)
                .params(BaseReq(condition))
                .execute(object : JsonCallback<BaseResponse<PgeRep<RepGoodListBean>?>?>() {
                    override fun onSuccess(response: Response<BaseResponse<PgeRep<RepGoodListBean>?>?>?) {
                        goods.postValue(response?.body()?.data?.list)
                    }

                })
    }


}