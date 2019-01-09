package com.bjpowernode.p2p.user.mapper;

import com.bjpowernode.p2p.user.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectAllRole();

    void deletePermissionByRoleId(Integer roleId);

    void insertByPermissionIdAndRoleId(@Param("roleId") Integer roleId, @Param("permission_id") Integer integer);

    List<Role> selectRoleByUserId(Integer userId);
}