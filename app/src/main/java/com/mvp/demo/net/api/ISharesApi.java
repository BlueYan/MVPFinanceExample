package com.mvp.demo.net.api;

import com.mvp.demo.model.bean.SharesEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 创建人：
 * 创建时间： 2016/8/9 14
 * 功能概述:
 * 修改人：
 * 修改时间：
 */
public interface ISharesApi {

    /**
     * 访问路径：http://apis.baidu.com/apistore/stockservice/usastock
     * 请求方式：GET
     * 访问参数：   key                                value
     *          stockid                               bidu,jd,wb
     *            list                                  1
     * 访问头部：apikey = 797f890af64d6a3cdbbbc84ebe216b45
     */
    @GET("apistore/stockservice/usastock")
    Observable<String> toGetShares(@Query("stockid") String stockId, @Query("list") String list);
}
