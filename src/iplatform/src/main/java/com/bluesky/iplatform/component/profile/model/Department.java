package com.bluesky.iplatform.component.profile.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.hierarchy.Hierarchyable;
import com.bluesky.iplatform.commons.hierarchy.SerializableComparator;
import com.bluesky.iplatform.commons.object.BaseObject;

/**
 * StDepartment entity. 
 * @author ElwinHe
 */
@Data
@Component(value = "Department")
@Scope(value = "prototype")
@Entity
@Table(name = "st_department", schema = "public")
public class Department extends BaseObject implements Hierarchyable, Serializable {
	
	private static final long serialVersionUID = 1766362179794549189L;

	/**
	 * 部门根节点 （公司节点）
	 */
	public static final int ROOTNODE = 100;

	// Fields
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "description", length = 500)
	private String description;
	
	@Column(name = "parentid")
	private Integer parentID;
	
	@Column(name = "level")
	private Integer level;
	
	@OrderBy
	@Column(name = "seqno")
	private Integer seqno;
	
	@Column(name = "companyID", nullable = false)
	private Integer companyID;
	
	/**
	 * 部门经理IDs
	 * （如果一个部门有多个部门经理时，显示：1000,1001,...）
	 */
	@Column(name = "managerids", length = 200)
	private String managerids;
	
	@Column(name = "sourcetype")
	private Integer sourcetype;

	@Column(name = "sourcename", length = 50)
	private String sourcename;
	
	@Column(name = "sourceid")
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
			String managerids, Integer sourcetype, String sourcename,
			Integer sourceid) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.parentID = parentID;
		this.level = level;
		this.seqno = seqno;
		this.companyID = companyID;
		this.managerids = managerids;
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

}