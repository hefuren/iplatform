package com.bluesky.iplatform.component.profile.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * StUserlogininfo entity. @author ElwinHe
 */
@Data
@Component(value = "UserLoginInfo")
@Scope(value = "prototype")
@Entity
@Table(name = "st_userlogininfo", schema = "public")
public class UserLoginInfo implements java.io.Serializable {

	private static final long serialVersionUID = -6702843292314551287L;
	
	// Fields
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "userid")
	private Integer userid;
	
	@Column(name = "logintimes")
	private Integer logintimes;
	
	@Column(name = "sessiontime")
	private Date sessiontime;
	
	@Column(name = "lastlogintime")
	private Date lastlogintime;
	
	@Column(name = "currentlogintime")
	private Date currentlogintime;
	
	@Column(name = "companyID", nullable = false)
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


}