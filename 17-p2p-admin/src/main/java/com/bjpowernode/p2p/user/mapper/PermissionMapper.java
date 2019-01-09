package com.bjpowernode.p2p.user.mapper;

import com.bjpowernode.p2p.user.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectMenuListByUserId(@Param("userId") Integer id, @Param("parentId") Integer i);

    List<String> selectURLByUserId(Integer id);

    List<Permission> selectAll();

    List<Permission> selectByRoleId(Integer roleId);
}