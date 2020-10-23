package com.yl.threestonecoupon.bean.rep

data class BaseRep<T>(val status:Int,
                 val msg:String,
                 val data:BaseResponse<T>
                 ) {
}
