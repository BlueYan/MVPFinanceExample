package com.mvp.demo.model;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.CombinedData;
import com.mvp.demo.model.bean.DataParse;
import com.mvp.demo.model.bean.KLineBean;
import com.mvp.demo.model.bean.SharesEntity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * 创建人：
 * 创建时间： 2016/8/9 14
 * 功能概述:
 * 修改人：
 * 修改时间：
 */
public interface IShares {

    Observable<String> getSharesInfo();

    /**
     * 获取K线图的数据源
     * @return
     */
    int getOffKLineData();

    BarData getBarData();

    CombinedData getCombinedData();

    ArrayList<KLineBean> getKLineData();

    int getXaisSize();


}
