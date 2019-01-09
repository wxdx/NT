package com.bjpowernode.multi.datasource;

/**
 * ClassName:ThreadLocalHolder
 * Package:com.bjpowernode.multi.datasource
 * Description:
 *
 * @Date:2018/10/17 10:47
 * @Author:hiwangxiaodong@hotmail.com
 */
public class ThreadLocalHolder {
    public static ThreadLocal<String> holder = new ThreadLocal<String>();

    public static String getDataSourceKey() {
        return holder.get();
    }

    public static void setDataSourceKey(String dataSourceKey) {
        holder.set(dataSourceKey);
    }
}
