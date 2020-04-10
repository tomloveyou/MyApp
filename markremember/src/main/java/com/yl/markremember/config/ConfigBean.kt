package com.yl.markremember.config

/**
 * 分页 bean
 * 关键字
 * T 类型不改变使用 out 关键字
 * 如果 T 类型发生操作，那么使用 in 关键字
 */
data class PageData<out T>(val page: Int, val result: List<T>)