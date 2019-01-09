package com.bjpowernode.multidb.mapper.db3307;

import com.bjpowernode.multidb.model.User;
import org.springframework.stereotype.Repository;

@Repository("userMapper3307")
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}