package com.bluesky.iplatform.component.codetable.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * CodeTableField entity. 
 * @author ElwinHe
 */
@Data
@Component(value="CodeTableField")
@Entity
@Scope(value="prototype")
@Table(name = "st_codetablefield", schema = "public")
public class CodeTableField implements java.io.Serializable {

	private static final long serialVersionUID = -8357275518093118553L;

	// Fields
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tableid")
	private Integer tableid;
	
	@Column(name = "name", length = 120)
	private String name;
	
	@Column(name = "description", length = 500)
	private String description;
	
	@Column(name = "fieldid")
	private Integer fieldid;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "fieldtype")
	private Integer fieldtype;
	
	@Column(name = "datatype")
	private Integer datatype;
	
	@Column(name = "datalength")
	private Integer datalength;
	
	@Column(name = "dataprecision")
	private Integer dataprecision;
	
	@Column(name = "dataformat")
	private Integer dataformat;
	
	@Column(name = "defaultvalue", length = 500)
	private String defaultvalue;
	
	@Column(name = "seqno")
	private Integer seqno;
	
	@Column(name = "fieldlevel")
	private Integer fieldlevel;
	

	@Column(name = "usecount")
	private Integer usecount;
	
	@Column(name = "companyid", nullable = false)
	private Integer companyID;

	// Constructors

	/** default constructor */
	public CodeTableField() {
	}

	/** minimal constructor */
	public CodeTableField(Integer id, Integer companyID) {
		this.id = id;
		this.companyID = companyID;
	}

	/** full constructor */
	public CodeTableField(Integer id, Integer tableid, String name,
			String description, Integer fieldid, Integer status, Integer type,
			Integer fieldtype, Integer datatype, Integer datalength,
			Integer dataprecision, Integer dataformat, String defaultvalue,
			Integer seqno, Integer fieldlevel, Integer usecount,
			Integer companyID) {
		this.id = id;
		this.tableid = tableid;
		this.name = name;
		this.description = description;
		this.fieldid = fieldid;
		this.status = status;
		this.type = type;
		this.fieldtype = fieldtype;
		this.datatype = datatype;
		this.datalength = datalength;
		this.dataprecision = dataprecision;
		this.dataformat = dataformat;
		this.defaultvalue = defaultvalue;
		this.seqno = seqno;
		this.fieldlevel = fieldlevel;
		this.usecount = usecount;
		this.companyID = companyID;
	}

	
}