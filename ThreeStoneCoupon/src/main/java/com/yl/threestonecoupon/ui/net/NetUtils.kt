package com.yl.threestonecoupon.ui.net

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import org.json.JSONObject
import java.util.*

fun <T> ViewModel.BaseReq(data: T): TreeMap<String, String?> {
    val jsonstr = Gson().toJson(data)
    val jsonObject = JSONObject(jsonstr)
    val map = TreeMap<String, String?>()
    jsonObject.keys().forEach {
        val v = jsonObject.opt(it)?.toString()
        map[it] = v
    }
    map["sign"] = SignMD5Util.getSignStr(map)
    return map;
}