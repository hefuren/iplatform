package com.bluesky.iplatform.component.profile.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * StUserlogininfo entity. @author ElwinHe
 */
@Component(value = "UserLoginInfo")
@Scope(value = "prototype")
@Entity
@Table(name = "st_userlogininfo", schema = "public")
public class UserLoginInfo implements java.io.Serializable {

	private static final long serialVersionUID = -6702843292314551287L;
	
	// Fields
	private Integer id;
	private Integer userid;
	private Integer logintimes;
	private Date sessiontime;
	private Date lastlogintime;
	private Date currentlogintime;
	private Integer companyID;

	// Constructors

	/** default constructor */
	public UserLoginInfo() {
	}

	/** minimal constructor */
	public UserLoginInfo(Integer id, Integer companyID) {
		this.id = id;
		this.companyID = companyID;
	}

	/** full constructor */
	public UserLoginInfo(Integer id, Integer userid, Integer logintimes,
			Date sessiontime, Date lastlogintime,
			Date currentlogintime, Integer companyID) {
		this.id = id;
		this.userid = userid;
		this.logintimes = logintimes;
		this.sessiontime = sessiontime;
		this.lastlogintime = lastlogintime;
		this.currentlogintime = currentlogintime;
		this.companyID = companyID;
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

	@Column(name = "userid")
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "logintimes")
	public Integer getLogintimes() {
		return this.logintimes;
	}

	public void setLogintimes(Integer logintimes) {
		this.logintimes = logintimes;
	}

	@Column(name = "sessiontime")
	public Date getSessiontime() {
		return this.sessiontime;
	}

	public void setSessiontime(Date sessiontime) {
		this.sessiontime = sessiontime;
	}

	@Column(name = "lastlogintime")
	public Date getLastlogintime() {
		return this.lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	@Column(name = "currentlogintime")
	public Date getCurrentlogintime() {
		return this.currentlogintime;
	}

	public void setCurrentlogintime(Date currentlogintime) {
		this.currentlogintime = currentlogintime;
	}

	@Column(name = "companyID", nullable = false)
	public Integer getCompanyID() {
		return this.companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

}