package com.bluesky.iplatform.component.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.bluesky.iplatform.commons.hierarchy.Hierarchyable;
import com.bluesky.iplatform.commons.hierarchy.SerializableComparator;

/**
 * Menu entity.
 * 
 * @author Elwin
 */
@Repository(value = "Menu")
@Scope(value = "prototype")
@Entity
@Table(name = "st_menu", schema = "public")
public class Menu implements Hierarchyable, java.io.Serializable {

	/**
	 * 菜单生效 status = 0
	 */
	public static final int STATUS_VALID = 0;

	/**
	 * 菜单无效 status = -1
	 */
	public static final int STATUS_INVALID = -1;

	public static final int APP_SYSTEM = 100;// 系统管理模块
	public static final int APP_PROJECT = 200;// 项目管理模块

	/** 菜单关键字定义 */
	public static final int M_SYS_STRUCTURELIST = 101;// 组织结构Structure
	public static final int M_SYS_USERLIST = 102;// 用户管理
	public static final int M_SYS_CODETABLE = 103;// 系统代码表
	
	
//	public static final int M_PROJECT_PROJECTLIST = 201;// 项目沙盘

	// Fields

	private Integer id;
	private Integer parentID;
	private String name;// 菜单名称
	private String menukey;// 菜单的关键字，唯一
	private String description;
	private Integer status;
	private Integer type;
	private Integer policy;
	private Integer seqNo;
	private String folderurl;
	private String clienturl;
	private String imageurl;
	private Integer applicationid;// 应用模块ID
	private Integer companyID;

	// Constructors

	/** default constructor */
	public Menu() {
	}

	/** minimal constructor */
	public Menu(Integer id, Integer parentID, String name, String menukey,
			Integer companyID) {
		this.id = id;
		this.parentID = parentID;
		this.name = name;
		this.menukey = menukey;
		this.companyID = companyID;
	}

	/** full constructor */
	public Menu(Integer id, Integer parentID, String name, String menukey,
			String description, Integer status, Integer type, Integer policy,
			Integer seqNo, String folderurl, String clienturl, String imageurl,
			Integer applicationid, Integer companyID) {
		this.id = id;
		this.parentID = parentID;
		this.name = name;
		this.menukey = menukey;
		this.description = description;
		this.status = status;
		this.type = type;
		this.policy = policy;
		this.seqNo = seqNo;
		this.folderurl = folderurl;
		this.clienturl = clienturl;
		this.imageurl = imageurl;
		this.applicationid = applicationid;
		this.companyID = companyID;
	}

	public static SerializableComparator SeqComparator = new SerializableComparator() {
		private static final long serialVersionUID = 1163795653927990202L;

		public int compare(Object obj1, Object obj2) {
			Menu menu1 = (Menu) obj1;
			Menu menu2 = (Menu) obj2;
			return menu1.getSeqNo() - menu2.getSeqNo();
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

	@Column(name = "parentid", nullable = false)
	public Integer getParentID() {
		return this.parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "menukey", nullable = false, length = 50)
	public String getMenukey() {
		return this.menukey;
	}

	public void setMenukey(String menukey) {
		this.menukey = menukey;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Column(name = "policy")
	public Integer getPolicy() {
		return this.policy;
	}

	public void setPolicy(Integer policy) {
		this.policy = policy;
	}

	@Column(name = "seqno")
	public Integer getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	@Column(name = "folderurl", length = 200)
	public String getFolderurl() {
		return this.folderurl;
	}

	public void setFolderurl(String folderurl) {
		this.folderurl = folderurl;
	}

	@Column(name = "clienturl", length = 200)
	public String getClienturl() {
		return this.clienturl;
	}

	public void setClienturl(String clienturl) {
		this.clienturl = clienturl;
	}

	@Column(name = "imageurl", length = 200)
	public String getImageurl() {
		return this.imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	@Column(name = "applicationid")
	public Integer getApplicationid() {
		return this.applicationid;
	}

	public void setApplicationid(Integer applicationid) {
		this.applicationid = applicationid;
	}

	@Column(name = "companyID", nullable = false)
	public Integer getCompanyID() {
		return this.companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

}