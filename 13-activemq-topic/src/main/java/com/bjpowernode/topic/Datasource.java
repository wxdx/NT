package com.bjpowernode.topic;

/**
 * ClassName:Datasource
 * Package:com.bjpowernode.topic
 * Description:
 *
 * @Date:2018/10/16 20:25
 * @Author:hiwangxiaodong@hotmail.com
 */
public enum Datasource {
    DB3307("db3307"), DB3308("db3308"), DB3309("db3309"), DB3310("db3310");

    private String value;

    Datasource(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
