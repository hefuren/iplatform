package com.bluesky.iplatform.component.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * RoleUser 角色用户分配对象
 * @author ElwinHe
 *
 */
@Component(value = "RoleRelation")
@Scope(value = "prototype")
@Entity
@Table(name = "st_rolerelation" ,schema = "public")
public class RoleRelation {
	
	/**
	 * status = 0 有效
	 */
	public static final int STATUS_VALID = 0;
	
	/**
	 * status = -1 无效
	 */
	public static final int STATUS_INVALID = -1;
	
	/**
	 * 对象ID（唯一标识）
	 */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userID;

    /**
     * 角色ID
     */
    private Integer roleID;

    private Integer status;

    private Integer type;

    /**
     * 公司（租户）ID，实现SaaS需要
     */
    private Integer companyID;

    @Id
    @OrderBy
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "userid", nullable = false)
	public Integer getUserID() {
		return userID;
	}
	
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	@Column(name = "roleid", nullable = false)
	public Integer getRoleID() {
		return roleID;
	}
	
	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "type")
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name = "companyID", nullable = false)
	public Integer getCompanyID() {
		return companyID;
	}
	
	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}
}