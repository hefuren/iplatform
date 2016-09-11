package com.bluesky.iplatform.commons.object;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.bluesky.iplatform.commons.cache.CacheObject;

public class BaseObject extends BatchObject implements Serializable,
		CacheObject {

	private static final long serialVersionUID = 1946457095889908885L;
	
	public Integer createBy;
	public Integer lastUpdateBy;
	public Date createTime;
	public Date lastUpdateTime;
	public Integer companyID;
	public Integer objectID;
	public Integer objectType;
	
	// Property accessors
	
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
	
	public Integer getObjectID() {
		return objectID;
	}
	public void setObjectID(Integer objectID) {
		this.objectID = objectID;
	}
	public Integer getObjectType() {
		return objectType;
	}
	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}
	

}
