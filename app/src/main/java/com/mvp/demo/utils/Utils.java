package com.mvp.demo.utils;

/**
 * 创建人：
 * 创建时间： 2016/8/10 13
 * 功能概述:
 * 修改人：
 * 修改时间：
 */
public class Utils {

    public static String getVolUnit(float num) {
        int e = (int) Math.floor(Math.log10(num));

        if (e >= 8) {
            return "亿手";
        } else if (e >= 4) {
            return "万手";
        } else {
            return "手";
        }
    }

}
