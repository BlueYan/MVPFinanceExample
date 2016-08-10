package com.mvp.demo.model.bean;

import java.util.List;

/**
 * 创建人：
 * 创建时间： 2016/8/9 14
 * 功能概述:
 * 修改人：
 * 修改时间：
 */
public class SharesEntity {


    /**
     * errNum : 0
     * errMsg : success
     * retData : {"stockinfo":[{"name":"百度","code":"bidu","date":"2016-08-09 08:19:04","currentPrice":166.45,"growthPercent":-0.55,"growth":-0.93,"openningPrice":167.15,"closingPrice":167.38,"hPrice":169.49,"lPrice":165.73,"52hPrice":217.97,"52lPrice":100,"totalNumber":1769609,"tenTotalNumber":3160065,"marketValue":57728169025,"eps":13.62,"pe":12.22,"beta":1.83,"bonus":0,"yields":0,"stockNum":346819880,"after-hoursTradePrice":166.44,"after-hoursTradeGrowth":-0.01,"after-hoursTradeGrowthPercent":-0.01,"after-hoursTradeNum":6023},{"name":"京东","code":"jd","date":"2016-08-09 08:20:12","currentPrice":22.35,"growthPercent":2.29,"growth":0.5,"openningPrice":21.82,"closingPrice":21.85,"hPrice":22.37,"lPrice":21.55,"52hPrice":33.48,"52lPrice":19.51,"totalNumber":11757581,"tenTotalNumber":7073898,"marketValue":30647381625,"eps":-1.05,"pe":0,"beta":0,"bonus":0,"yields":0,"stockNum":1371247500,"after-hoursTradePrice":22.4,"after-hoursTradeGrowth":0.22,"after-hoursTradeGrowthPercent":0.05,"after-hoursTradeNum":24298},{"name":"微博","code":"wb","date":"2016-08-09 08:20:13","currentPrice":37.17,"growthPercent":3.65,"growth":1.31,"openningPrice":36.71,"closingPrice":35.86,"hPrice":37.3,"lPrice":35.92,"52hPrice":37.3,"52lPrice":8.78,"totalNumber":1957438,"tenTotalNumber":909001,"marketValue":8163498420,"eps":0.2,"pe":185.85,"beta":0,"bonus":0,"yields":0,"stockNum":219626000,"after-hoursTradePrice":37.68,"after-hoursTradeGrowth":1.37,"after-hoursTradeGrowthPercent":0.51,"after-hoursTradeNum":35084}],"market":{"shanghai":{"name":"上证指数","curdot":3016.6444,"curprice":12.3677,"rate":0.41,"dealnumber":1324436,"turnover":14525523},"shenzhen":{"name":"深证成指","curdot":10523.62,"curprice":54.739,"rate":0.52,"dealnumber":145768016,"turnover":21620552},"DJI":{"name":"道琼斯","date":"2016-08-09 04:04:05","curdot":18529.35,"rate":-0.08,"growth":-14.18,"startdot":18540.65,"closedot":18543.53,"hdot":18569.31,"ldot":18502.03,"turnover":0},"IXIC":{"name":"纳斯达克","date":"2016-08-09 05:40:00","curdot":5213.14,"rate":-0.15,"growth":-7.98,"startdot":5223.54,"closedot":5221.12,"hdot":5228.4,"ldot":5202.18,"turnover":1574308503},"INX":{"name":"纳斯达克","date":"2016-08-09 05:40:00","curdot":2180.89,"rate":-0.09,"growth":-1.98,"startdot":2183.76,"closedot":2182.87,"hdot":2185.44,"ldot":2177.85,"turnover":0},"HSI":{"name":"恒生指数","date":"2016/08/09 14:13","curdot":22450.76,"rate":-0.2,"growth":-44,"startdot":22464.1,"closedot":22494.76,"hdot":22496.75,"ldot":22402.91,"turnover":37933657,"52hdot":24924.07,"52ldot":18278.8}}}
     */

    private int errNum;
    private String errMsg;
    /**
     * stockinfo : [{"name":"百度","code":"bidu","date":"2016-08-09 08:19:04","currentPrice":166.45,"growthPercent":-0.55,"growth":-0.93,"openningPrice":167.15,"closingPrice":167.38,"hPrice":169.49,"lPrice":165.73,"52hPrice":217.97,"52lPrice":100,"totalNumber":1769609,"tenTotalNumber":3160065,"marketValue":57728169025,"eps":13.62,"pe":12.22,"beta":1.83,"bonus":0,"yields":0,"stockNum":346819880,"after-hoursTradePrice":166.44,"after-hoursTradeGrowth":-0.01,"after-hoursTradeGrowthPercent":-0.01,"after-hoursTradeNum":6023},{"name":"京东","code":"jd","date":"2016-08-09 08:20:12","currentPrice":22.35,"growthPercent":2.29,"growth":0.5,"openningPrice":21.82,"closingPrice":21.85,"hPrice":22.37,"lPrice":21.55,"52hPrice":33.48,"52lPrice":19.51,"totalNumber":11757581,"tenTotalNumber":7073898,"marketValue":30647381625,"eps":-1.05,"pe":0,"beta":0,"bonus":0,"yields":0,"stockNum":1371247500,"after-hoursTradePrice":22.4,"after-hoursTradeGrowth":0.22,"after-hoursTradeGrowthPercent":0.05,"after-hoursTradeNum":24298},{"name":"微博","code":"wb","date":"2016-08-09 08:20:13","currentPrice":37.17,"growthPercent":3.65,"growth":1.31,"openningPrice":36.71,"closingPrice":35.86,"hPrice":37.3,"lPrice":35.92,"52hPrice":37.3,"52lPrice":8.78,"totalNumber":1957438,"tenTotalNumber":909001,"marketValue":8163498420,"eps":0.2,"pe":185.85,"beta":0,"bonus":0,"yields":0,"stockNum":219626000,"after-hoursTradePrice":37.68,"after-hoursTradeGrowth":1.37,"after-hoursTradeGrowthPercent":0.51,"after-hoursTradeNum":35084}]
     * market : {"shanghai":{"name":"上证指数","curdot":3016.6444,"curprice":12.3677,"rate":0.41,"dealnumber":1324436,"turnover":14525523},"shenzhen":{"name":"深证成指","curdot":10523.62,"curprice":54.739,"rate":0.52,"dealnumber":145768016,"turnover":21620552},"DJI":{"name":"道琼斯","date":"2016-08-09 04:04:05","curdot":18529.35,"rate":-0.08,"growth":-14.18,"startdot":18540.65,"closedot":18543.53,"hdot":18569.31,"ldot":18502.03,"turnover":0},"IXIC":{"name":"纳斯达克","date":"2016-08-09 05:40:00","curdot":5213.14,"rate":-0.15,"growth":-7.98,"startdot":5223.54,"closedot":5221.12,"hdot":5228.4,"ldot":5202.18,"turnover":1574308503},"INX":{"name":"纳斯达克","date":"2016-08-09 05:40:00","curdot":2180.89,"rate":-0.09,"growth":-1.98,"startdot":2183.76,"closedot":2182.87,"hdot":2185.44,"ldot":2177.85,"turnover":0},"HSI":{"name":"恒生指数","date":"2016/08/09 14:13","curdot":22450.76,"rate":-0.2,"growth":-44,"startdot":22464.1,"closedot":22494.76,"hdot":22496.75,"ldot":22402.91,"turnover":37933657,"52hdot":24924.07,"52ldot":18278.8}}
     */

    private List<StockInfo> mStockInfo;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<StockInfo> getmStockInfo() {
        return mStockInfo;
    }

    public void setmStockInfo(List<StockInfo> mStockInfo) {
        this.mStockInfo = mStockInfo;
    }

    private static class StockInfo {

        /**
         * name : 百度
         * code : bidu
         * date : 2016-08-09 08:19:04
         * currentPrice : 166.45
         * growthPercent : -0.55
         * growth : -0.93
         * openningPrice : 167.15
         * closingPrice : 167.38
         * hPrice : 169.49
         * lPrice : 165.73
         * 52hPrice : 217.97
         * 52lPrice : 100
         * totalNumber : 1769609
         * tenTotalNumber : 3160065
         * marketValue : 57728169025
         * eps : 13.62
         * pe : 12.22
         * beta : 1.83
         * bonus : 0
         * yields : 0
         * stockNum : 346819880
         * after-hoursTradePrice : 166.44
         * after-hoursTradeGrowth : -0.01
         * after-hoursTradeGrowthPercent : -0.01
         * after-hoursTradeNum : 6023
         */

        private String name;
        private String code;
        private String date;
        private double currentPrice;
        private double growthPercent;
        private double growth;
        private double openningPrice;
        private double closingPrice;
        private double hPrice;
        private double lPrice;
        private int totalNumber;
        private int tenTotalNumber;
        private long marketValue;
        private double eps;
        private double pe;
        private double beta;
        private int bonus;
        private int yields;
        private int stockNum;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public double getGrowthPercent() {
            return growthPercent;
        }

        public void setGrowthPercent(double growthPercent) {
            this.growthPercent = growthPercent;
        }

        public double getGrowth() {
            return growth;
        }

        public void setGrowth(double growth) {
            this.growth = growth;
        }

        public double getOpenningPrice() {
            return openningPrice;
        }

        public void setOpenningPrice(double openningPrice) {
            this.openningPrice = openningPrice;
        }

        public double getClosingPrice() {
            return closingPrice;
        }

        public void setClosingPrice(double closingPrice) {
            this.closingPrice = closingPrice;
        }

        public double gethPrice() {
            return hPrice;
        }

        public void sethPrice(double hPrice) {
            this.hPrice = hPrice;
        }

        public double getlPrice() {
            return lPrice;
        }

        public void setlPrice(double lPrice) {
            this.lPrice = lPrice;
        }

        public int getTotalNumber() {
            return totalNumber;
        }

        public void setTotalNumber(int totalNumber) {
            this.totalNumber = totalNumber;
        }

        public int getTenTotalNumber() {
            return tenTotalNumber;
        }

        public void setTenTotalNumber(int tenTotalNumber) {
            this.tenTotalNumber = tenTotalNumber;
        }

        public long getMarketValue() {
            return marketValue;
        }

        public void setMarketValue(long marketValue) {
            this.marketValue = marketValue;
        }

        public double getEps() {
            return eps;
        }

        public void setEps(double eps) {
            this.eps = eps;
        }

        public double getPe() {
            return pe;
        }

        public void setPe(double pe) {
            this.pe = pe;
        }

        public double getBeta() {
            return beta;
        }

        public void setBeta(double beta) {
            this.beta = beta;
        }

        public int getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
            this.bonus = bonus;
        }

        public int getYields() {
            return yields;
        }

        public void setYields(int yields) {
            this.yields = yields;
        }

        public int getStockNum() {
            return stockNum;
        }

        public void setStockNum(int stockNum) {
            this.stockNum = stockNum;
        }

        @Override
        public String toString() {
            return "StockInfo{" +
                    "name='" + name + '\'' +
                    ", code='" + code + '\'' +
                    ", date='" + date + '\'' +
                    ", currentPrice=" + currentPrice +
                    ", growthPercent=" + growthPercent +
                    ", growth=" + growth +
                    ", openningPrice=" + openningPrice +
                    ", closingPrice=" + closingPrice +
                    ", hPrice=" + hPrice +
                    ", lPrice=" + lPrice +
                    ", totalNumber=" + totalNumber +
                    ", tenTotalNumber=" + tenTotalNumber +
                    ", marketValue=" + marketValue +
                    ", eps=" + eps +
                    ", pe=" + pe +
                    ", beta=" + beta +
                    ", bonus=" + bonus +
                    ", yields=" + yields +
                    ", stockNum=" + stockNum +
                    '}';
        }
    }


}
