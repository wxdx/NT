package com.bjpowernode.seckill.model;

import java.io.Serializable;

/**
 * ClassName:User
 * Package:com.bjpowernode.seckill.model
 * Description:
 *
 * @Date:2018/10/24 17:06
 * @Author:hiwangxiaodong@hotmail.com
 */
public class User implements Serializable {
    private static final long serialVersionUID = -6169076003445615332L;

    private Integer id;

    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
