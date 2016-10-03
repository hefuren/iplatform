package com.bluesky.iplatform.commons.object;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

import lombok.Data;

import com.bluesky.iplatform.commons.cache.CacheObject;

@Data
public class BaseObject extends BatchObject implements Serializable,
		CacheObject {

	private static final long serialVersionUID = 1946457095889908885L;
	
	@Column(name = "createby")
	protected Integer createBy;
	
	@Column(name = "lastupdateby")
	protected Integer lastUpdateBy;
	
	@Column(name = "createtime")
	protected Date createTime;

	@Column(name = "lastupdatetime")
	protected Date lastUpdateTime;
	
	@Column(name = "companyid", nullable = false)
	protected Integer companyID;
	
	@Transient
	protected Integer objectID;
	
	@Transient
	protected Integer objectType;
	
	// Property accessors

}
