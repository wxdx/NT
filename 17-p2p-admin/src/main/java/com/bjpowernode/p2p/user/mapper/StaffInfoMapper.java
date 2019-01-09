package com.bjpowernode.p2p.user.mapper;

import com.bjpowernode.p2p.user.model.StaffInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StaffInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StaffInfo record);

    int insertSelective(StaffInfo record);

    StaffInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StaffInfo record);

    int updateByPrimaryKey(StaffInfo record);
}