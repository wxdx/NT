package com.bjpowernode.springboot.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName:Pagination
 * Package:com.bjpowernode.springboot.model.vo
 * Description:
 *
 * @Date:2018/10/11 20:10
 * @Author:hiwangxiaodong@hotmail.com
 */
public class Pagination<T> implements Serializable {
    private Long totalRows;
    private List<T> dataList;

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
