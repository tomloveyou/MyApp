package com.yl.threestonecoupon.ui.net

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.lzy.okgo.callback.AbsCallback
import com.yl.threestonecoupon.bean.rep.BaseResponse
import okhttp3.Response
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class JsonCallback<T> : AbsCallback<T> {
    private var type: Type? = null
    private var clazz: Class<T>? = null

    constructor() {}
    constructor(type: Type?) {
        this.type = type
    }

    constructor(clazz: Class<T>?) {
        this.clazz = clazz
    }

    @Throws(Throwable::class)
    override fun convertResponse(response: Response): T? {
        val body = response.body() ?: return null
        var data: T? = null
        val gson = Gson()
        val jsonReader = JsonReader(body.charStream())
        data = if (type != null) {
            gson.fromJson(jsonReader, type)
        } else if (clazz != null) {
            gson.fromJson(jsonReader, clazz)
        } else {
            val genType: Type? = javaClass.genericSuperclass
            val type: Type = (genType as ParameterizedType).getActualTypeArguments().get(0)
            gson.fromJson(jsonReader, type)
        }
        return data
    }
}