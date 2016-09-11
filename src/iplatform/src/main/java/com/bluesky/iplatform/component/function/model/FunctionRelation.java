package com.bluesky.iplatform.component.function.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * StRolefunction entity. 
 * @author ElwinHe
 */
@Component(value = "FunctionRelation")
@Scope(value = "prototype")
@Entity
@Table(name = "st_functionRelation", schema = "public")
public class FunctionRelation implements java.io.Serializable {
	
	private static final long serialVersionUID = -968987862346300800L;
	
	/**
	 * 权限分配到角色
	 */
	public static final int TYPE_FUNCTION_TO_ROLE = 0;

	/**
	 * 权限分配到用户
	 */
	public static final int TYPE_FUNCTION_TO_USER = 1;
	
	
	// Fields
	private Integer id;
	private Integer roleID;
	private Integer functionID;
	private Integer objectid;
	private Integer objecttype;
	private Integer groupid;
	private Integer status;
	private Integer type;
	private Integer companyID;

	// Constructors

	/** default constructor */
	public FunctionRelation() {
	}

	/** minimal constructor */
	public FunctionRelation(Integer id, Integer roleID, Integer functionID,
			Integer companyID) {
		this.id = id;
		this.roleID = roleID;
		this.functionID = functionID;
		this.companyID = companyID;
	}

	/** full constructor */
	public FunctionRelation(Integer id, Integer roleID, Integer functionID,
			Integer objectid, Integer objecttype, Integer groupid,
			Integer status, Integer type, Integer companyID) {
		this.id = id;
		this.roleID = roleID;
		this.functionID = functionID;
		this.objectid = objectid;
		this.objecttype = objecttype;
		this.groupid = groupid;
		this.status = status;
		this.type = type;
		this.companyID = companyID;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid", nullable = false)
	public Integer getRoleID() {
		return this.roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "functionid", nullable = false)
	public Integer getFunctionID() {
		return this.functionID;
	}

	public void setFunctionID(Integer functionID) {
		this.functionID = functionID;
	}

	@Column(name = "objectid")
	public Integer getObjectid() {
		return this.objectid;
	}

	public void setObjectid(Integer objectid) {
		this.objectid = objectid;
	}

	@Column(name = "objecttype")
	public Integer getObjecttype() {
		return this.objecttype;
	}

	public void setObjecttype(Integer objecttype) {
		this.objecttype = objecttype;
	}

	@Column(name = "groupid")
	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "companyID", nullable = false)
	public Integer getCompanyID() {
		return this.companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

}