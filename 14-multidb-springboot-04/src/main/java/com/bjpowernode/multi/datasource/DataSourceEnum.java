package com.bjpowernode.multi.datasource;

/**
 * ClassName:DataSourceEnum
 * Package:com.bjpowernode.multi.datasource
 * Description:
 *
 * @Date:2018/10/17 16:50
 * @Author:hiwangxiaodong@hotmail.com
 */
public enum DataSourceEnum {
    DB3307("db3307"), DB3308("db3308"), DB3309("db3309"), DB3310("db3310");

    private String key;

    DataSourceEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
