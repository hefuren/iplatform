package com.bluesky.iplatform.component.from.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component(value = "FormView")
@Entity
@Scope(value = "prototype")
@Table(name = "fm_formview", schema = "public")
public class FormView implements java.io.Serializable{

	private static final long serialVersionUID = 8863447934524347413L;
	
	// Fields
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "schemaid", nullable = false)
	private Integer schemaID;
	
	/**
	 * 视图名称
	 */
	@Column(name = "name", length = 50)
	private String name;
	
	/**
	 * 适用角色ID（例如：1000,1001,1002,...）
	 */
	@Column(name = "applicableroleid", length = 500)
	private String applicableRoleID;
	
	/**
	 * 视图适用的状态(例如：1000,1001,...)
	 */
	@Column(name = "applicablestatus", length = 200)
	private String applicableStatus;
	
	@Column(name = "description", length = 500)
	private String description;
	
	@Column(name = "seqno")
	private Integer seqno;
	
	@Column(name = "companyID", nullable = false)
	private Integer companyID;
	
	@Transient
	private List<FormViewItem> formViewItems = new ArrayList<FormViewItem>();
}
