package com.bjpowernode.multi.mapper.db3308;

import com.bjpowernode.multi.model.User;
import org.springframework.stereotype.Repository;

@Repository("userMapper3308")
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}