package org.goaler.druid.config;

import com.alibaba.druid.pool.DruidDataSourceStatLoggerAdapter;
import com.alibaba.druid.pool.DruidDataSourceStatValue;

/**
 * 可以通过该log,保存Druid的监控记录。如：将监控数据打印到日志、发送到监控中心
 *
 * druidDataSource.setTimeBetweenLogStatsMillis
 * 设置保存时间间隔
 *
 * druidDataSource.setStatLogger
 * 设置DruidDataSourceStatLogger的实现类
 */
public class MyStatLoggerImpl extends DruidDataSourceStatLoggerAdapter {

    @Override
    public void log(DruidDataSourceStatValue statValue) {

    }
}
