package com.mvp.demo.ui.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.mvp.demo.R;
import com.mvp.demo.model.bean.DataParse;
import com.mvp.demo.model.bean.KLineBean;
import com.mvp.demo.presenter.SharesPresenter;
import com.mvp.demo.ui.iview.ISharesView;
import com.mvp.demo.utils.CoupleChartGestureListener;
import com.mvp.demo.utils.MyLeftMarkerView;
import com.mvp.demo.utils.VolFormatter;
import com.mvp.demo.base.BaseActivity;
import com.mvp.library.utils.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements ISharesView {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.id_combined_chart)
    CombinedChart mCombinedChart;

    @BindView(R.id.id_barchart)
    BarChart mBarChart;

    private SharesPresenter mSharePresenter = new SharesPresenter(this);  //持有P层对象

    private ProgressDialog mDialog;

    private XAxis mXAxisBar, mXAxisK;

    private YAxis mAxisLeftBar, mAxisLeftK;

    private YAxis mAxisRightBar, mAxisRightK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharePresenter.onCreate();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mBarChart.setAutoScaleMinMaxEnabled(true);
            mCombinedChart.setAutoScaleMinMaxEnabled(true);

            mCombinedChart.notifyDataSetChanged();
            mBarChart.notifyDataSetChanged();

            mCombinedChart.invalidate();
            mBarChart.invalidate();

            dismissLoading();
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mBarChart.setDrawBorders(true);
        mBarChart.setBorderWidth(1);
        mBarChart.setBorderColor(Color.parseColor("#CCCCCC"));
        mBarChart.setDescription("K线柱状图");
        mBarChart.setDragEnabled(true);
        mBarChart.setScaleYEnabled(true);  //设置可以缩放Y轴

        final Legend mBarChartLegend = mBarChart.getLegend();
        mBarChartLegend.setEnabled(false);

        //柱状图X轴
        mXAxisBar = mBarChart.getXAxis(); //获取柱状图的X轴
        mXAxisBar.setDrawLabels(true); //绘制X轴文本
        mXAxisBar.setDrawGridLines(false); //设置不绘制网格线
        mXAxisBar.setDrawAxisLine(false); //设置不绘制X轴线
        mXAxisBar.setTextColor(Color.parseColor("#000000")); //设置文本颜色
        mXAxisBar.setPosition(XAxis.XAxisPosition.BOTTOM); //设置位置
        mXAxisBar.setGridColor(Color.parseColor("#CCCCCC")); //设置网络颜色

        //柱状图左边的Y轴
        mAxisLeftBar = mBarChart.getAxisLeft(); //获取左边的Y轴
        mAxisLeftBar.setAxisMinValue(0);
        mAxisLeftBar.setDrawGridLines(false);
        mAxisLeftBar.setDrawAxisLine(false);
        mAxisLeftBar.setTextColor(Color.parseColor("#000000"));
        mAxisLeftBar.setDrawLabels(true);
        mAxisLeftBar.setSpaceTop(0);
        mAxisLeftBar.setShowOnlyMinMax(true);

        //柱状图右边的Y轴
        mAxisRightBar = mBarChart.getAxisRight(); //获取右边的Y轴
        mAxisRightBar.setDrawLabels(false);
        mAxisRightBar.setDrawGridLines(false);
        mAxisRightBar.setDrawAxisLine(false);

        /*********************配置K线图******************************/
        mCombinedChart.setDrawBorders(true);
        mCombinedChart.setBorderWidth(1);
        mCombinedChart.setBorderColor(Color.parseColor("#CCCCCC"));
        mCombinedChart.setDescription("K线图");
        mCombinedChart.setDragEnabled(true);
        mCombinedChart.setScaleYEnabled(false);

        Legend mCombinedChartLegend = mCombinedChart.getLegend();
        mCombinedChartLegend.setEnabled(false);

        //K线图X轴
        mXAxisK = mCombinedChart.getXAxis();
        mXAxisK.setDrawLabels(true);
        mXAxisK.setDrawGridLines(false);
        mXAxisK.setDrawAxisLine(false);
        mXAxisK.setTextColor(Color.parseColor("#000000"));
        mXAxisK.setPosition(XAxis.XAxisPosition.BOTTOM);
        mXAxisK.setGridColor(Color.parseColor("#CCCCCC"));

        //K线图左边的Y轴
        mAxisLeftK = mCombinedChart.getAxisLeft();
        mAxisLeftK.setDrawGridLines(true);
        mAxisLeftK.setDrawAxisLine(false);
        mAxisLeftK.setDrawLabels(true);
        mAxisLeftK.setTextColor(Color.parseColor("#000000"));
        mAxisLeftK.setGridColor(Color.parseColor("#CCCCCC"));
        mAxisLeftK.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        //K线图右边的Y轴
        mAxisRightK = mCombinedChart.getAxisRight();
        mAxisRightK.setDrawLabels(false);
        mAxisRightK.setDrawGridLines(true);
        mAxisRightK.setDrawAxisLine(false);
        mAxisRightK.setGridColor(Color.parseColor("#CCCCCC"));

        mCombinedChart.setDragDecelerationEnabled(true);
        mBarChart.setDragDecelerationEnabled(true);

        mCombinedChart.setDragDecelerationFrictionCoef(0.2f);
        mBarChart.setDragDecelerationFrictionCoef(0.2f);

        //将K线控件的滑动事件传递给交易量的控件
        mCombinedChart.setOnChartGestureListener(new CoupleChartGestureListener(mCombinedChart, new
                Chart[]{mBarChart}));
        //将交易量控件的滑动事件传递给K线控件
        mBarChart.setOnChartGestureListener(new CoupleChartGestureListener(mBarChart, new Chart[]{mCombinedChart}));

        mBarChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                LogUtils.d("h = " + h.getXIndex());
                mCombinedChart.highlightValues(new Highlight[]{h});
            }

            @Override
            public void onNothingSelected() {
                mCombinedChart.highlightValue(null);
            }
        });

        mCombinedChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                mBarChart.highlightValues(new Highlight[]{h});
            }

            @Override
            public void onNothingSelected() {
                mBarChart.highlightValue(null);
            }
        });

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSharePresenter.onDestory();
    }

    @Override
    public void showLoading() {
        if (mDialog == null) {
            mDialog = ProgressDialog.show(this, null, "正在加载中...");
        } else {
            mDialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void setChartData(CandleData data) {
    }

    /**
     * @param u
     * @param mBarData
     * @param mCombinedData
     * @param mKLineDatas
     */
    @Override
    public void setChartData(int u, BarData mBarData, CombinedData mCombinedData, ArrayList<KLineBean> mKLineDatas,
                             int number) {
        //setMarkView();
        mAxisLeftBar.setValueFormatter(new VolFormatter((int) Math.pow(10, u)));
        mBarChart.setData(mBarData); //设置数据源
        final ViewPortHandler viewPortHandlerBar = mBarChart.getViewPortHandler();
        viewPortHandlerBar.setMaximumScaleX(culcMaxscale(number));
        Matrix touchmatrix = viewPortHandlerBar.getMatrixTouch();
        final float xscale = 3;
        touchmatrix.postScale(xscale, 1f);

        //K线图设置数据源
        mCombinedChart.setData(mCombinedData);
        mCombinedChart.moveViewToX(mKLineDatas.size() - 1);
        final ViewPortHandler viewPortHandlerCombin = mCombinedChart.getViewPortHandler();
        viewPortHandlerCombin.setMaximumScaleX(culcMaxscale(number));
        Matrix matrixCombin = viewPortHandlerCombin.getMatrixTouch();
        matrixCombin.postScale(xscale, 1f);

        mCombinedChart.moveViewToX(mKLineDatas.size() - 1);
        mBarChart.moveViewToX(mKLineDatas.size() - 1);
        setOffset();

        handler.sendEmptyMessageDelayed(0, 300);

    }

    private void setMarkView() {
        MarkerView markerView = new MyLeftMarkerView(this, R.layout.mymarkerview);
        mCombinedChart.setMarkerView(markerView);
    }

    /*设置量表对齐*/
    private void setOffset() {
        float lineLeft = mCombinedChart.getViewPortHandler().offsetLeft();
        float barLeft = mBarChart.getViewPortHandler().offsetLeft();
        float lineRight = mCombinedChart.getViewPortHandler().offsetRight();
        float barRight = mBarChart.getViewPortHandler().offsetRight();
        float barBottom = mBarChart.getViewPortHandler().offsetBottom();
        float offsetLeft, offsetRight;
        float transLeft = 0, transRight = 0;
 /*注：setExtraLeft...函数是针对图表相对位置计算，比如A表offLeftA=20dp,B表offLeftB=30dp,则A.setExtraLeftOffset(10),并不是30，还有注意单位转换*/
        if (barLeft < lineLeft) {
           /* offsetLeft = Utils.convertPixelsToDp(lineLeft - barLeft);
            barChart.setExtraLeftOffset(offsetLeft);*/
            transLeft = lineLeft;
        } else {
            offsetLeft = Utils.convertPixelsToDp(barLeft - lineLeft);
            mCombinedChart.setExtraLeftOffset(offsetLeft);
            transLeft = barLeft;
        }
  /*注：setExtraRight...函数是针对图表绝对位置计算，比如A表offRightA=20dp,B表offRightB=30dp,则A.setExtraLeftOffset(30),并不是10，还有注意单位转换*/
        if (barRight < lineRight) {
          /*  offsetRight = Utils.convertPixelsToDp(lineRight);
            barChart.setExtraRightOffset(offsetRight);*/
            transRight = lineRight;
        } else {
            offsetRight = Utils.convertPixelsToDp(barRight);
            mCombinedChart.setExtraRightOffset(offsetRight);
            transRight = barRight;
        }
        mBarChart.setViewPortOffsets(transLeft, 15, transRight, barBottom);
    }

    @Override
    public void setPresenter(SharesPresenter presenter) {

    }


    private float culcMaxscale(int count) {
        float max = 1;
        max = count / 127 * 5;
        return max;
    }

}
