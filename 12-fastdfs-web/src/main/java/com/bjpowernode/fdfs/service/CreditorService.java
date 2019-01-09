package com.bjpowernode.fdfs.service;

import com.bjpowernode.fdfs.model.Creditor;

import java.util.List;

/**
 * ClassName:CreditorService
 * Package:com.bjpowernode.fdfs.service
 * Description:
 *
 * @Date:2018/10/13 16:27
 * @Author:hiwangxiaodong@hotmail.com
 */
public interface CreditorService {
    List<Creditor> findAll();

    Creditor findOne(Integer id);

    Integer modify(Creditor creditor);

    Integer deleteRemoteFile(Integer id);
}
