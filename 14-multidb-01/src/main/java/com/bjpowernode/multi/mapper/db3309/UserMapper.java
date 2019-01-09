package com.bjpowernode.multi.mapper.db3309;

import com.bjpowernode.multi.model.User;
import org.springframework.stereotype.Repository;


@Repository("userMapper3309")
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}