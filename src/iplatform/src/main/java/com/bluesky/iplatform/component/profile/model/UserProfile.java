package com.bluesky.iplatform.component.profile.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.object.BaseObject;

/**
 * Userprofile entity. 
 * @author ElwinHe
 */
@Component(value = "UserProfile")
@Scope(value = "prototype")
@Entity
@Table(name = "st_userprofile", schema = "public")
public class UserProfile extends BaseObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -754787446191246802L;
	
	public static final int STATUS_ACTIVE = 1; // 可用，激活的
	public static final int STATUS_DELETED = -1;//被删除的
	
	// Fields

	private Integer id;
	private Integer userID;
	private Integer position;
	private Integer status;
	private Date jointime;
	private Date leavetime;

	@Transient
	private User user;
	// Constructors

	/** default constructor */
	public UserProfile() {
	}

	/** minimal constructor */
	public UserProfile(Integer id, User user, Integer companyID) {
		this.id = id;
		this.user = user;
		this.companyID = companyID;
	}

	/** full constructor */
	public UserProfile(Integer id, User user, Integer companyID,
			Integer position, Date jointime, Date leavetime,
			Integer createby, Date createtime, Integer lastupdateby,
			Date lastupdatetime) {
		this.id = id;
		this.user = user;
		this.companyID = companyID;
		this.position = position;
		this.jointime = jointime;
		this.leavetime = leavetime;
		this.createBy = createby;
		this.createTime = createtime;
		this.lastUpdateBy = lastupdateby;
		this.lastUpdateTime = lastupdatetime;
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	@Transient
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "position")
	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Column(name = "jointime")
	public Date getJointime() {
		return this.jointime;
	}

	public void setJointime(Date jointime) {
		this.jointime = jointime;
	}

	@Column(name = "leavetime")
	public Date getLeavetime() {
		return this.leavetime;
	}

	public void setLeavetime(Date leavetime) {
		this.leavetime = leavetime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
}