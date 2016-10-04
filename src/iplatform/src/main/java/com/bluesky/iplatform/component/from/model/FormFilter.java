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
@Component(value = "FormFilter")
@Entity
@Scope(value = "prototype")
@Table(name = "fm_formfiter", schema = "public")
public class FormFilter {
	

	//Field
	@Id
	@Column(name = "id", unique = true, nullable = false)	
	private Integer id;
	
	@Column(name = "schemaid", nullable = false)	
	private Integer schemaID;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "referenceid")
	private Integer referenceID;
	
	@Column(name = "description", length = 200)
	private String description;
	
	@Column(name = "companyid", nullable = false)	
	private Integer companyID;
	
	@Transient
	private List<FormFilterItem> formFilterItems = new ArrayList<FormFilterItem>();
}
