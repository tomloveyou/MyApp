package com.yl.threestonecoupon.bean.rep

data class BaseResponse<T>(val code: Int,
                      val msg: String,
                      val time: Long,
                      val data: T?)