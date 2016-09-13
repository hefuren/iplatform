package com.bluesky.iplatform.component.profile.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.object.BaseObject;
import com.bluesky.iplatform.component.function.model.FunctionRelation;

/**
 * StRole entity. @author ElwinHe
 */
@Component(value = "Role")
@Scope(value = "prototype")
@Entity
@Table(name = "st_role", schema = "public")
public class Role extends BaseObject implements java.io.Serializable {

	private static final long serialVersionUID = -3602593857566728663L;
	// Fields
	private Integer id;
	private String name;
	private String description;
	private Integer parentID;
	private Integer seqno;
	private Integer type;
	private Integer status;
	
	@Transient
	private Set<FunctionRelation> roleFunctions = new HashSet<FunctionRelation>(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(Integer id, Integer companyID) {
		this.id = id;
		this.companyID = companyID;
	}

	/** full constructor */
	public Role(Integer id, String name, String description,
			Integer parentID, Integer seqno, Integer companyID, Integer type,
			Integer status, Integer createby, Date ceateTime,
			Integer lastupdateby, Date lastUpdateTime,
			Set<FunctionRelation> roleFunctions) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.parentID = parentID;
		this.seqno = seqno;
		this.companyID = companyID;
		this.type = type;
		this.status = status;
		this.createBy = createby;
		this.createTime = ceateTime;
		this.lastUpdateBy = lastupdateby;
		this.lastUpdateTime = lastUpdateTime;
		this.roleFunctions = roleFunctions;
	}

	// Property accessors
	@Id
	@OrderBy
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "parentid")
	public Integer getParentID() {
		return this.parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	@Column(name = "seqno")
	public Integer getSeqno() {
		return this.seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Transient
	public Set<FunctionRelation> getRoleFunctions() {
		return this.roleFunctions;
	}

	public void setRoleFunctions(Set<FunctionRelation> roleFunctions) {
		this.roleFunctions = roleFunctions;
	}

}