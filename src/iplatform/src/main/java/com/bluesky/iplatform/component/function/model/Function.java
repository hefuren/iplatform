package com.bluesky.iplatform.component.function.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.hierarchy.Hierarchyable;

/**
 * Function entity.
 *  @author ElwinHe
 */
@Component(value = "Function")
@Scope(value = "prototype")
@Entity
@Table(name = "st_function", schema = "public")
public class Function implements Hierarchyable,java.io.Serializable {

	// Fields

	private Integer id;
	
	/**
	 * 权限Key
	 */
	private String name;
	private Integer parentID;
	private String functionname;
	private String description;
	private Integer type;
	private Integer status;
	private Integer seqno;
	private Integer applicationid;
	private String policy;
	private String clienturl;
	private String folderurl;
	private String imageurl;
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
	@Id
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

	@Column(name = "functionname", length = 50)
	public String getFunctionname() {
		return this.functionname;
	}

	public void setFunctionname(String functionname) {
		this.functionname = functionname;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Column(name = "seqno")
	public Integer getSeqno() {
		return this.seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	@Column(name = "applicationid")
	public Integer getApplicationid() {
		return this.applicationid;
	}

	public void setApplicationid(Integer applicationid) {
		this.applicationid = applicationid;
	}

	@Column(name = "policy")
	public String getPolicy() {
		return this.policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	@Column(name = "clienturl", length = 100)
	public String getClienturl() {
		return this.clienturl;
	}

	public void setClienturl(String clienturl) {
		this.clienturl = clienturl;
	}

	@Column(name = "folderurl", length = 100)
	public String getFolderurl() {
		return this.folderurl;
	}

	public void setFolderurl(String folderurl) {
		this.folderurl = folderurl;
	}

	@Column(name = "imageurl", length = 100)
	public String getImageurl() {
		return this.imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Column(name = "companyID", nullable = false)
	public Integer getCompanyID() {
		return this.companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	@Transient
	public Set<FunctionRelation> getRoleFunctions() {
		return this.roleFunctions;
	}

	public void setRoleFunctions(Set<FunctionRelation> rolefunctions) {
		this.roleFunctions = rolefunctions;
	}

	@Column(name = "parentID", nullable = false)
	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	

}