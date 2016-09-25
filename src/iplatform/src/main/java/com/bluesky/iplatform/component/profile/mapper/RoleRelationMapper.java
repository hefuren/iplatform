package com.bluesky.iplatform.component.profile.mapper;

import java.util.List;

import com.bluesky.iplatform.component.profile.model.Role;
import com.bluesky.iplatform.component.profile.model.RoleRelation;
import com.bluesky.iplatform.component.profile.model.User;

public interface RoleRelationMapper {

    public int deleteByPrimaryKey(Integer id);
    
    public int deleteByRoleID(Integer roleID);

    public int insert(RoleRelation mode);

    public int insertSelective(RoleRelation mode);

    public RoleRelation selectByPrimaryKey(Integer id);
    
    /**
     * 根据用户查找对应的角色
     * @param user
     * @return
     */
    public List<Role> selectRolesByUser(User user);

    public int updateByPrimaryKeySelective(RoleRelation mode);

    public int updateByPrimaryKey(RoleRelation mode);
}