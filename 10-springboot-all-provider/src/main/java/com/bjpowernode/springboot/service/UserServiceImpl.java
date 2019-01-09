package com.bjpowernode.springboot.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.springboot.mapper.UserMapper;
import com.bjpowernode.springboot.model.User;
import com.bjpowernode.springboot.model.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName:UserServiceImpl
 * Package:com.bjpowernode.springboot.service
 * Description:
 *
 * @Date:2018/10/11 16:03
 * @Author:hiwangxiaodong@hotmail.com
 */
@Service(interfaceClass = UserService.class)
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public List<User> findAll() {
        List<User> list = (List<User>) redisTemplate.opsForValue().get("userAll");
        synchronized (this){
            if (null == list) {
                list = userMapper.selectAll();
                redisTemplate.opsForValue().set("userAll", list);
            }
        }
        return list;
    }

    @Override
    public User findOne(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void modify(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        redisTemplate.delete("userAll");
    }

    @Override
    public void removeById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
        redisTemplate.delete("userAll");
    }

    @Override
    public Pagination<User> findAllByPage(Integer p, Integer pageSize) {

        Pagination<User> pagination = new Pagination<>();

        pagination.setDataList(userMapper.selectByPage(p, pageSize));

        pagination.setTotalRows(userMapper.selectTotal(p, pageSize));

        return pagination;
    }
}
