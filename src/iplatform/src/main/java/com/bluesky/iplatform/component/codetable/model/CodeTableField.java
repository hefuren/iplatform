package com.bluesky.iplatform.component.codetable.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * CodeTableField entity. 
 * @author ElwinHe
 */
@Component(value="CodeTableField")
@Entity
@Scope(value="prototype")
@Table(name = "st_codetablefield", schema = "public")
public class CodeTableField implements java.io.Serializable {

	// Fields

	private Integer id;
	private CodeTable codeTable;
	private String name;
	private String description;
	private Integer fieldid;
	private Integer status;
	private Integer type;
	private Integer fieldtype;
	private Integer datatype;
	private Integer datalength;
	private Integer dataprecision;
	private Integer dataformat;
	private String defaultvalue;
	private Integer seqno;
	private Integer fieldlevel;
	private Integer usecount;
	private Integer companyid;

	// Constructors

	/** default constructor */
	public CodeTableField() {
	}

	/** minimal constructor */
	public CodeTableField(Integer id, Integer companyid) {
		this.id = id;
		this.companyid = companyid;
	}

	/** full constructor */
	public CodeTableField(Integer id, CodeTable codeTable, String name,
			String description, Integer fieldid, Integer status, Integer type,
			Integer fieldtype, Integer datatype, Integer datalength,
			Integer dataprecision, Integer dataformat, String defaultvalue,
			Integer seqno, Integer fieldlevel, Integer usecount,
			Integer companyid) {
		this.id = id;
		this.codeTable = codeTable;
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
		this.companyid = companyid;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tableid")
	public CodeTable getCodeTable() {
		return this.codeTable;
	}

	public void setCodeTable(CodeTable codeTable) {
		this.codeTable = codeTable;
	}

	@Column(name = "name", length = 120)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "fieldid")
	public Integer getFieldid() {
		return this.fieldid;
	}

	public void setFieldid(Integer fieldid) {
		this.fieldid = fieldid;
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

	@Column(name = "fieldtype")
	public Integer getFieldtype() {
		return this.fieldtype;
	}

	public void setFieldtype(Integer fieldtype) {
		this.fieldtype = fieldtype;
	}

	@Column(name = "datatype")
	public Integer getDatatype() {
		return this.datatype;
	}

	public void setDatatype(Integer datatype) {
		this.datatype = datatype;
	}

	@Column(name = "datalength")
	public Integer getDatalength() {
		return this.datalength;
	}

	public void setDatalength(Integer datalength) {
		this.datalength = datalength;
	}

	@Column(name = "dataprecision")
	public Integer getDataprecision() {
		return this.dataprecision;
	}

	public void setDataprecision(Integer dataprecision) {
		this.dataprecision = dataprecision;
	}

	@Column(name = "dataformat")
	public Integer getDataformat() {
		return this.dataformat;
	}

	public void setDataformat(Integer dataformat) {
		this.dataformat = dataformat;
	}

	@Column(name = "defaultvalue", length = 500)
	public String getDefaultvalue() {
		return this.defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	@Column(name = "seqno")
	public Integer getSeqno() {
		return this.seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	@Column(name = "fieldlevel")
	public Integer getFieldlevel() {
		return this.fieldlevel;
	}

	public void setFieldlevel(Integer fieldlevel) {
		this.fieldlevel = fieldlevel;
	}

	@Column(name = "usecount")
	public Integer getUsecount() {
		return this.usecount;
	}

	public void setUsecount(Integer usecount) {
		this.usecount = usecount;
	}

	@Column(name = "companyid", nullable = false)
	public Integer getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

}