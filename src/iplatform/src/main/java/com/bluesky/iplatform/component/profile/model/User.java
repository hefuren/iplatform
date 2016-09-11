package com.bluesky.iplatform.component.profile.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.object.BaseObject;

/**
 * User entity. 
 * @author ElwinHe
 */
@Component(value = "User")
@Scope(value = "prototype")
@Entity
@Table(name = "st_user", schema = "public")
public class User extends BaseObject implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6527492586997280301L;

	public static final int SYS_ADMIN_ID = 1000;
	
	public static final int STATUS_ACTIVE = 1; // 可用，激活的
	public static final int STATUS_UNACTIVE = 0; // 不可用，激活的
	public static final int STATUS_DELETED = -1;//被删除的

	// Fields

	private Integer id;
	private String name;
	private String password;
	private String displayname;
	private String firstname;
	private String lastname;
	private Integer seqno;
	private Integer type;
	private Integer status;
	private String email;
	private String mobile;
	private String tel;
	private String zip;
	private String jobnumber;
	private Integer sex;
	private Timestamp birthday;
	private Integer departmentid;
	private Integer sourcetype;
	private String sourcename;
	private Integer sourceid;
	public Integer createBy;
	public Integer lastUpdateBy;
	public Date createTime;
	public Date lastUpdateTime;
	public Integer companyID;
	private UserProfile userProfile;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Integer id, String name, Integer companyID) {
		this.id = id;
		this.name = name;
		this.companyID = companyID;
	}

	/** full constructor */
	public User(Integer id, String name, String password, String displayname,
			String firstname, String lastname, Integer seqno, Integer type,
			Integer status, String email, String mobile, String tel,
			String zip, String jobnumber, Integer sex, Timestamp birthday,
			Integer departmentid, Integer sourcetype, String sourcename,
			Integer sourceid, Integer createby, Timestamp createtime,
			Integer lastupdateby, Timestamp lastupdatetime, Integer companyID) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.displayname = displayname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.seqno = seqno;
		this.type = type;
		this.status = status;
		this.email = email;
		this.mobile = mobile;
		this.tel = tel;
		this.zip = zip;
		this.jobnumber = jobnumber;
		this.sex = sex;
		this.birthday = birthday;
		this.departmentid = departmentid;
		this.sourcetype = sourcetype;
		this.sourcename = sourcename;
		this.sourceid = sourceid;
		this.createBy = createby;
		this.createTime = createtime;
		this.lastUpdateBy = lastupdateby;
		this.lastUpdateTime = lastupdatetime;
		this.companyID = companyID;
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

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "displayname", length = 50)
	public String getDisplayname() {
		return this.displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	@Column(name = "firstname", length = 20)
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Column(name = "lastname", length = 20)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "mobile", length = 30)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "tel", length = 30)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "zip", length = 20)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "jobnumber", length = 30)
	public String getJobnumber() {
		return this.jobnumber;
	}

	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "birthday")
	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	@Column(name = "departmentid")
	public Integer getDepartmentid() {
		return this.departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
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

	@Column(name = "createby")
	public Integer getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	@Column(name = "createtime")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "lastupdateby")
	public Integer getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	public void setLastUpdateBy(Integer lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	@Column(name = "lastupdatetime")
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Column(name = "companyID", nullable = false)
	public Integer getCompanyID() {
		return this.companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Transient
	public String getStatusName(){
		String statusName = "";
		if(this.status == User.STATUS_ACTIVE){
			statusName = "激活";
		} else if(this.status == User.STATUS_UNACTIVE){
			statusName = "未激活";
		} else{
			statusName = "已禁用";
		}
		
		return statusName;
	}
	

}