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

import lombok.Data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.object.BaseObject;
import com.bluesky.iplatform.component.function.model.FunctionRelation;

/**
 * Role entity.
 * @author ElwinHe
 */
@Data
@Component(value = "Role")
@Scope(value = "prototype")
@Entity
@Table(name = "st_role", schema = "public")
public class Role extends BaseObject implements java.io.Serializable {

	private static final long serialVersionUID = -3602593857566728663L;
	// Fields
	@Id
	@OrderBy
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "description", length = 500)
	private String description;
	
	@Column(name = "parentid")
	private Integer parentID;
	
	@Column(name = "seqno")
	private Integer seqno;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "status")
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
	@Transient
	public Set<FunctionRelation> getRoleFunctions() {
		return this.roleFunctions;
	}

	public void setRoleFunctions(Set<FunctionRelation> roleFunctions) {
		this.roleFunctions = roleFunctions;
	}

}