package com.mvp.demo.ui.iview;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CombinedData;
import com.mvp.demo.model.bean.DataParse;
import com.mvp.demo.model.bean.KLineBean;
import com.mvp.demo.presenter.SharesPresenter;
import com.mvp.library.base.IBaseView;

import java.util.ArrayList;

/**
 * 创建人：
 * 创建时间： 2016/8/9 15
 * 功能概述:
 * 修改人：
 * 修改时间：
 */
public interface ISharesView extends IBaseView<SharesPresenter>{

    void showLoading();

    void dismissLoading();

    void setChartData(CandleData data);

    void setChartData(int u, BarData mBarData, CombinedData mCombinedData, ArrayList<KLineBean> mKLineDatas, int number);

}
