package com.bjpowernode.queues.domain;

import java.io.Serializable;

/**
 * ClassName:Student
 * Package:com.bjpowernode.queues.domain
 * Description:
 *
 * @Date:2018/10/16 15:08
 * @Author:hiwangxiaodong@hotmail.com
 */
public class Student implements Serializable {


    private static final long serialVersionUID = 2374909252964541613L;
    private String name;
    private int age;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
