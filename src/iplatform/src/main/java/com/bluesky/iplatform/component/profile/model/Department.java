package com.bluesky.iplatform.component.profile.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.hierarchy.Hierarchyable;
import com.bluesky.iplatform.commons.hierarchy.SerializableComparator;

/**
 * StDepartment entity. 
 * @author ElwinHe
 */
@Component(value = "Department")
@Scope(value = "prototype")
@Entity
@Table(name = "st_department", schema = "public")
public class Department implements Hierarchyable, Serializable {
	
	private static final long serialVersionUID = 1766362179794549189L;

	/**
	 * 部门根节点 （公司节点）
	 */
	public static final int ROOTNODE = 100;

	// Fields

	private Integer id;
	private String name;
	private String description;
	private Integer parentID;
	private Integer level;
	private Integer seqno;
	private Integer companyID;
	private String managers;
	private Integer sourcetype;
	private String sourcename;
	private Integer sourceid;

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** minimal constructor */
	public Department(Integer id, Integer companyID) {
		this.id = id;
		this.companyID = companyID;
	}

	/** full constructor */
	public Department(Integer id, String name, String description,
			Integer parentID, Integer level, Integer seqno, Integer companyID,
			String managers, Integer sourcetype, String sourcename,
			Integer sourceid) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.parentID = parentID;
		this.level = level;
		this.seqno = seqno;
		this.companyID = companyID;
		this.managers = managers;
		this.sourcetype = sourcetype;
		this.sourcename = sourcename;
		this.sourceid = sourceid;
	}
	
	public static SerializableComparator SeqComparator = new SerializableComparator() {
		private static final long serialVersionUID = 1163795653927990202L;

		public int compare(Object obj1, Object obj2) {
			Department dept1 = (Department) obj1;
			Department dept2 = (Department) obj2;
			return dept1.getSeqno() - dept2.getSeqno();
		}
	};

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

	@Column(name = "level")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@OrderBy
	@Column(name = "seqno")
	public Integer getSeqno() {
		return this.seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	@Column(name = "companyID", nullable = false)
	public Integer getCompanyID() {
		return this.companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	@Column(name = "managers", length = 200)
	public String getManagers() {
		return this.managers;
	}

	public void setManagers(String managers) {
		this.managers = managers;
	}

	@Column(name = "sourcetype")
	public Integer getSourcetype() {
		return this.sourcetype;
	}

	public void setSourcetype(Integer sourcetype) {
		this.sourcetype = sourcetype;
	}

	@Column(name = "sourcename", length = 50)
	public String getSourcename() {
		return this.sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	@Column(name = "sourceid")
	public Integer getSourceid() {
		return this.sourceid;
	}

	public void setSourceid(Integer sourceid) {
		this.sourceid = sourceid;
	}

}