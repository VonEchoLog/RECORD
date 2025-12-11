package com.record.recordmvc.test;

import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Echo
 * @Date: 2025/2/12 14:03
 * @Description:
 */
public class DateExecuteUtils {
    public static void main(String[] args) {
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        // 示例日期
        String date1Str = "2024-12-17";
        String date2Str = sdf.format(new Date());

        // 将字符串转换为日期对象
        Date date1 = DateUtil.parse(date1Str);
        Date date2 = DateUtil.parse(date2Str);

        // 计算两个日期之间的天数差
        long daysBetween = DateUtil.betweenDay(date1, date2, true); // true表示包括开始日期

        System.out.println("两个日期之间的天数差为: " + (++daysBetween));

        Map<String,Object> map = new HashMap<>();
        //map过滤掉key值为pagede


        for (Map.Entry<String, Object> entry : map.entrySet()) {}


    }
}
