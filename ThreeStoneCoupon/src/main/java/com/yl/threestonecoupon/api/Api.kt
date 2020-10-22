package com.yl.threestonecoupon.api

object Api {
    const val BASE_URL: String = "https://openapi.dataoke.com/"

    /**
     * 热搜记录
     */
    const val HEAT_SEARCH_RECORD: String = BASE_URL+"api/category/get-top100"
    /**
     * 商品列表
     */
    const val PRODUCT_LIST: String = BASE_URL+"api/goods/get-goods-list"
    /**
     * 各大榜单
     */
    const val MAJOR_LISTS: String = BASE_URL+"api/goods/get-ranking-list"
    /**
     * 高效转链
     */
    const val EFFICIENT_CHAIN_TRANSFER: String = BASE_URL+"api/tb-service/get-privilege-link"
    /**
     * 单品详情
     */
    const val ITEM_DETAILS: String = BASE_URL+"api/goods/get-goods-details"
    /**
     * 大淘客搜索
     */
    const val BIG_TAOKE_SEARCH: String = BASE_URL+"api/goods/get-dtk-search-goods"
    /**
     * 超级分类
     */
    const val SUPER_CLASSIFICATION: String = BASE_URL+"api/category/get-super-category"
    /**
     * 联盟搜索
     */
    const val ALLIANCE_SEARCH: String = BASE_URL+"api/tb-service/get-tb-service"
    /**
     * 三合一红包接口
     */
    const val THREE_IN_ONE_RED_PACKET_INTERFACE: String = BASE_URL+"api/dels/merge-red-envelopes"
    /**
     * 专题商品
     */
    const val SPECIAL_COMMODITIES: String = BASE_URL+"api/goods/topic/goods-list"
    /**
     * 每日爆品推荐
     */
    const val DAILY_RECOMMENDATION: String = BASE_URL+"api/goods/explosive-goods-list"
}