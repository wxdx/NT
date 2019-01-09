package com.bjpowernode.p2p.user.model;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    private Integer id;

    private String name;

    private String type;

    private String url;

    private String code;

    private Integer parentId;

    private Double sort;

    private Integer available;

    private List<Permission> permissionInfoList = new ArrayList<>();

    public List<Permission> getPermissionInfoList() {
        return permissionInfoList;
    }

    public void setPermissionInfoList(List<Permission> permissionInfoList) {
        this.permissionInfoList = permissionInfoList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Double getSort() {
        return sort;
    }

    public void setSort(Double sort) {
        this.sort = sort;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}