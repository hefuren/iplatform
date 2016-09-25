package com.bluesky.iplatform.component.function.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.hierarchy.Hierarchyable;

/**
 * Function entity.
 *  @author ElwinHe
 */
@Data
@Component(value = "Function")
@Scope(value = "prototype")
@Entity
@Table(name = "st_function", schema = "public")
public class Function implements Hierarchyable,java.io.Serializable {

	private static final long serialVersionUID = -4909557358691726718L;

	// Fields
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	/**
	 * 权限Key
	 */
	@Column(name = "name", unique = true, length = 50)
	private String name;
	
	@Column(name = "parentID", nullable = false)
	private Integer parentID;
	
	@Column(name = "functionname", length = 50)
	private String functionname;
	
	@Column(name = "description", length = 200)
	private String description;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "seqno")
	private Integer seqno;
	
	/**
	 * 应用模块ID
	 */
	@Column(name = "applicationid")
	private Integer applicationid;

	@Column(name = "policy", length = 10)
	private String policy;
	
	@Column(name = "clienturl", length = 100)
	private String clienturl;
	
	@Column(name = "folderurl", length = 100)
	private String folderurl;
	
	@Column(name = "imageurl", length = 100)
	private String imageurl;
	
	@Column(name = "companyID", nullable = false)
	private Integer companyID;
	
	@Transient
	private Set<FunctionRelation> roleFunctions = new HashSet<FunctionRelation>(0);

	// Constructors

	/** default constructor */
	public Function() {
	}

	/** minimal constructor */
	public Function(Integer id, Integer companyID) {
		this.id = id;
		this.companyID = companyID;
	}

	/** full constructor */
	public Function(Integer id, String name, String functionname,
			String description, Integer type, Integer status, Integer seqno,
			Integer applicationid, String policy, String clienturl,
			String folderurl, String imageurl, Integer companyID,
			Set<FunctionRelation> roleFunctions) {
		this.id = id;
		this.name = name;
		this.functionname = functionname;
		this.description = description;
		this.type = type;
		this.status = status;
		this.seqno = seqno;
		this.applicationid = applicationid;
		this.policy = policy;
		this.clienturl = clienturl;
		this.folderurl = folderurl;
		this.imageurl = imageurl;
		this.companyID = companyID;
		this.roleFunctions = roleFunctions;
	}

	// Property accessors
	@Transient
	public Set<FunctionRelation> getRoleFunctions() {
		return this.roleFunctions;
	}

	public void setRoleFunctions(Set<FunctionRelation> rolefunctions) {
		this.roleFunctions = rolefunctions;
	}

	

}