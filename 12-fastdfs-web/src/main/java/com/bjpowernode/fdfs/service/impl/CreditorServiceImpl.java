package com.bjpowernode.fdfs.service.impl;

import com.bjpowernode.fdfs.mapper.CreditorMapper;
import com.bjpowernode.fdfs.model.Creditor;
import com.bjpowernode.fdfs.service.CreditorService;
import com.bjpowernode.fdfs.util.FastDfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:CreditorServiceImpl
 * Package:com.bjpowernode.fdfs.service.impl
 * Description:
 *
 * @Date:2018/10/13 16:28
 * @Author:hiwangxiaodong@hotmail.com
 */
@Service
public class CreditorServiceImpl implements CreditorService {
    @Autowired
    private CreditorMapper creditorMapper;
    @Override
    public List<Creditor> findAll() {
        return creditorMapper.selectAll();
    }

    @Override
    public Creditor findOne(Integer id) {
        return creditorMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer modify(Creditor creditor) {
       return creditorMapper.updateByPrimaryKeySelective(creditor);
    }

    @Override
    @Transactional
    public Integer deleteRemoteFile(Integer id) {
        Creditor creditor = creditorMapper.selectByPrimaryKey(id);
        String groupName = creditor.getGroupName();
        String remoteFileName = creditor.getRemoteFileName();

        //1:删除失败
        //0:删除成功
        int result = 1;
        //清空数据库中相关字段信息
        Integer count = creditorMapper.updateByRemoteFileName(id);
        if (count > 0){
            //删除FastDFS中的文件
            int deleteResult = FastDfsUtil.delete(groupName, remoteFileName);
            if (deleteResult != 0){
                throw new RuntimeException("合同删除失败");
            } else {
                result = 0;
            }
        }
        return result;
    }
}
