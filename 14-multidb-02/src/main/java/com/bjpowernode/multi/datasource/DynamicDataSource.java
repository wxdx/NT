package com.bjpowernode.multi.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * ClassName:DynamicDataSource
 * Package:com.bjpowernode.multi.datasource
 * Description:
 *      实现动态数据源,在程序运行中,决定使用哪个数据源
 * @Date:2018/10/17 10:40
 * @Author:hiwangxiaodong@hotmail.com
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return ThreadLocalHolder.getDataSourceKey();
    }
}
