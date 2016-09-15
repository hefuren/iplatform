package com.bluesky.iplatform.component.function.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.object.BatchObject;

/**
 * StRolefunction entity. 
 * @author ElwinHe
 */
@Data
@Component(value = "FunctionRelation")
@Scope(value = "prototype")
@Entity
@Table(name = "st_functionRelation", schema = "public")
public class FunctionRelation extends BatchObject implements java.io.Serializable {
	
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
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid", nullable = false)
	private Integer roleID;
	
	@JoinColumn(name = "functionid", nullable = false)
	private Integer functionID;
	
	@Column(name = "objectid")
	private Integer objectid;
	
	@Column(name = "objecttype")
	private Integer objecttype;
	
	@Column(name = "groupid")
	private Integer groupid;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "companyID", nullable = false)
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

}