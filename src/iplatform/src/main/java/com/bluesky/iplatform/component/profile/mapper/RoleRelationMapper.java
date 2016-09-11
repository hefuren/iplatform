package com.bluesky.iplatform.component.profile.mapper;

import com.bluesky.iplatform.component.profile.model.RoleRelation;

public interface RoleRelationMapper {

    public int deleteByPrimaryKey(Integer id);
    
    public int deleteByRoleID(Integer roleID);

    public int insert(RoleRelation mode);

    public int insertSelective(RoleRelation mode);

    public RoleRelation selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(RoleRelation mode);

    public int updateByPrimaryKey(RoleRelation mode);
}