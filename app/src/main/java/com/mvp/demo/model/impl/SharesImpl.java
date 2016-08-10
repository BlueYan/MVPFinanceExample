package com.mvp.demo.model.impl;

import android.graphics.Color;
import android.graphics.Paint;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.mvp.demo.base.AppConstant;
import com.mvp.demo.model.IShares;
import com.mvp.demo.model.bean.DataParse;
import com.mvp.demo.model.bean.KLineBean;
import com.mvp.demo.model.bean.SharesEntity;
import com.mvp.demo.net.RetrofitManager;
import com.mvp.demo.net.api.ISharesApi;
import com.mvp.demo.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * 创建人：
 * 创建时间： 2016/8/9 14
 * 功能概述: M层实现类
 * 修改人：
 * 修改时间：
 */
public class SharesImpl implements IShares {

    private DataParse mDataParse;

    private int sum = 0;

    private BarData mBarData; //柱状图数据源

    private CombinedData mCombinedData; //K线图数据源 包括K线图和折线图 组合图数据源

    private ArrayList<String> xVals = new ArrayList<>();

    private ArrayList<BarEntry> barEntries = new ArrayList<>(); //柱状图

    private ArrayList<CandleEntry> candleEntries = new ArrayList<>();

    private ArrayList<Entry> line5Entries = new ArrayList<>();  //折线

    private ArrayList<Entry> line10Entries = new ArrayList<>();

    private ArrayList<Entry> line30Entries = new ArrayList<>();

    private ArrayList<KLineBean> mKLineData;

    @Override
    public Observable<String> getSharesInfo() {
        ISharesApi mApi = RetrofitManager.mRetrofit.create(ISharesApi.class);
        return mApi.toGetShares("bidu,jd,wb", "1");
    }

    /**
     * 获取离线数据，进行解析，处理
     */
    @Override
    public int getOffKLineData() {
        mDataParse = new DataParse();
        JSONObject object = null;
        try {
            object = new JSONObject(AppConstant.KLINEURL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mDataParse.parseKLine(object);
        mKLineData = mDataParse.getKLineDatas();
        return parseData();
    }

    @Override
    public BarData getBarData() {
        return mBarData;
    }

    @Override
    public CombinedData getCombinedData() {
        return mCombinedData;
    }

    @Override
    public ArrayList<KLineBean> getKLineData() {
        return mKLineData;
    }

    @Override
    public int getXaisSize() {
        return xVals.size();
    }

    private int parseData() {
        String unit = Utils.getVolUnit(mDataParse.getVolmax());
        int u = 1;
        if (unit.equals("万手")) {
            u = 4;
        } else if (unit.equals("亿手")) {
            u = 8;
        }


        /**
         * 解析数据
         */
        for (int i = 0, j = 0; i < mDataParse.getKLineDatas().size(); i++, j++) {
            xVals.add(mDataParse.getKLineDatas().get(i).date + "");  //获取日期
            barEntries.add(new BarEntry(mDataParse.getKLineDatas().get(i).vol, i)); //添加柱状图数据
            candleEntries.add(new CandleEntry(i, mDataParse.getKLineDatas().get(i).high, mDataParse.getKLineDatas()
                    .get(i).low, mDataParse.getKLineDatas().get(i).open, mDataParse.getKLineDatas().get(i).close)); //添加K线图数据
            if (i >= 4) {
                sum = 0;
                line5Entries.add(new Entry(getSum(i - 4, i) / 5, i));
            }
            if (i >= 9) {
                sum = 0;
                line10Entries.add(new Entry(getSum(i - 9, i) / 10, i));
            }
            if (i >= 29) {
                sum = 0;
                line30Entries.add(new Entry(getSum(i - 29, i) / 30, i));
            }

        }


        List<Integer> mColors=new ArrayList<>();
        mColors.add(Color.RED);
        mColors.add(Color.GREEN);
        BarDataSet mBarDataSet = new BarDataSet(barEntries, "成交量");
        mBarDataSet.setBarSpacePercent(50); //bar空隙
        mBarDataSet.setHighlightEnabled(true);
        mBarDataSet.setHighLightAlpha(255);
        mBarDataSet.setHighLightColor(Color.BLACK);
        mBarDataSet.setDrawValues(false);
        mBarDataSet.setColor(Color.RED);  //设置红色
        mBarDataSet.setColors(mColors);
        mBarData = new BarData(xVals, mBarDataSet); //设置柱状图数据源
        //由view设置数据源，刷新界面 barchart

        //K线图数据实体类
        CandleDataSet candleDataSet = new CandleDataSet(candleEntries, "KLine");
        candleDataSet.setDrawHorizontalHighlightIndicator(false);
        candleDataSet.setHighlightEnabled(true);  //允许高亮
        candleDataSet.setHighLightColor(Color.BLACK);
        candleDataSet.setValueTextSize(10f);
        candleDataSet.setDrawValues(false);
        candleDataSet.setDecreasingColor(Color.RED);  //设置上涨颜色为红色
        candleDataSet.setDecreasingPaintStyle(Paint.Style.FILL); //设置上涨实体块为实心
        candleDataSet.setShadowWidth(1f);
        candleDataSet.setIncreasingColor(Color.rgb(122, 242, 84)); //设置下跌颜色为绿色
        candleDataSet.setIncreasingPaintStyle(Paint.Style.STROKE); //设置下跌块为空心
        candleDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        CandleData mCandleData = new CandleData(xVals, candleDataSet);

        //折线图数据实体
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(setMaLine(5, xVals, line5Entries));
        sets.add(setMaLine(10, xVals, line10Entries));
        sets.add(setMaLine(30, xVals, line30Entries));

        mCombinedData = new CombinedData(xVals);
        LineData lineData = new LineData(xVals, sets); //折线图数据源
        mCombinedData.setData(mCandleData);
        mCombinedData.setData(lineData);
        //由combinedChart设置数据源，刷新界面

        return u;

    }

    private LineDataSet setMaLine(int ma, ArrayList<String> xVals, ArrayList<Entry> lineEntries) {
        LineDataSet lineDataSetMa = new LineDataSet(lineEntries, "ma" + ma);
        if (ma == 5) {
            lineDataSetMa.setHighlightEnabled(true);
            lineDataSetMa.setDrawHorizontalHighlightIndicator(false);
            lineDataSetMa.setHighLightColor(Color.BLACK);
        } else {/*此处必须得写*/
            lineDataSetMa.setHighlightEnabled(false);
        }
        lineDataSetMa.setDrawValues(false);
        if (ma == 5) {
            lineDataSetMa.setColor(Color.GREEN);
        } else if (ma == 10) {
            lineDataSetMa.setColor(Color.GRAY);
        } else {
            lineDataSetMa.setColor(Color.YELLOW);
        }
        lineDataSetMa.setLineWidth(1f);
        lineDataSetMa.setDrawCircles(false);
        lineDataSetMa.setAxisDependency(YAxis.AxisDependency.LEFT);
        return lineDataSetMa;
    }

    private float getSum(Integer a, Integer b) {

        for (int i = a; i <= b; i++) {
            sum += mDataParse.getKLineDatas().get(i).close;
        }
        return sum;
    }
}
