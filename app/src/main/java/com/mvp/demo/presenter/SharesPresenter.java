package com.mvp.demo.presenter;

import android.graphics.Color;
import android.graphics.Paint;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.mvp.demo.model.IShares;
import com.mvp.demo.model.bean.SharesEntity;
import com.mvp.demo.model.impl.SharesImpl;
import com.mvp.demo.ui.iview.ISharesView;
import com.mvp.library.base.BasePresenterImpl;
import com.mvp.library.base.IBasePresenter;
import com.mvp.library.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建人：
 * 创建时间： 2016/8/9 15
 * 功能概述: 展示K线图的P层
 * 修改人：
 * 修改时间：
 */
public class SharesPresenter extends BasePresenterImpl{

    private static final String TAG = SharesPresenter.class.getSimpleName();

    private ISharesView mSharesView; //持有V层接口对象 针对接口编程

    private IShares mShares; //持有M层接口对象

    public SharesPresenter(ISharesView mIShareView) {
        this.mSharesView = mIShareView;
        mShares = new SharesImpl();
    }



    //初始化数据
    @Override
    public void onCreate() {
        super.onCreate();
        mSharesView.showLoading();
       /* mShares.getSharesInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(String s) {
                        mSharesView.dismissLoading();
                        LogUtils.d("s = " + s);
                       // parseStockInfo(s);
                    }
                });*/

        int u = mShares.getOffKLineData();
        mSharesView.setChartData(u, mShares.getBarData(), mShares.getCombinedData(), mShares.getKLineData(), mShares.getXaisSize());

    }



    /**
     * 在这里需要再次对数据进行解析和加工
     * @param
     */
    /*private void parseStockInfo(String s)  {
        try {
            JSONObject mObject = new JSONObject(s);
            String flag = mObject.getString("errMsg");
            if ( flag.equals("success") ) {
                JSONArray mArray = mObject.getJSONObject("retData").getJSONArray("stockinfo");
                List<CandleEntry> mCandleEntries = new ArrayList<CandleEntry>();
                for ( int i = 0; i < mArray.length(); i++ ) {
                    //开盘价
                    float openPrice = (float) mArray.getJSONObject(i).getDouble("openningPrice");
                    //收盘价
                    float closePrice = (float) mArray.getJSONObject(i).getDouble("closingPrice");
                    //最高价
                    float hPrice = (float) mArray.getJSONObject(i).getDouble("hPrice");
                    //最低价
                    float lPrice = (float) mArray.getJSONObject(i).getDouble("lPrice");
                    CandleEntry mEntry = new CandleEntry(i, hPrice, lPrice, openPrice, closePrice);
                    mCandleEntries.add(mEntry);
                }
                CandleDataSet set1 = new CandleDataSet(mCandleEntries, "Data Set");
                set1.setAxisDependency(YAxis.AxisDependency.LEFT);
                set1.setShadowColor(Color.DKGRAY);
                set1.setShadowWidth(0.7f);
                set1.setDecreasingColor(Color.RED);
                set1.setDecreasingPaintStyle(Paint.Style.FILL);
                set1.setIncreasingColor(Color.rgb(122, 242, 84));
                set1.setIncreasingPaintStyle(Paint.Style.STROKE);
                set1.setNeutralColor(Color.BLUE);
                CandleData data = new CandleData(set1);
                mSharesView.setChartData(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    //释放资源
    @Override
    public void onDestroy() {
        super.onDestroy(); //一定要调用父类的onDestroy();
    }
}
