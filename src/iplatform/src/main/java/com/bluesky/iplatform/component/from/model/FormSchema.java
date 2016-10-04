package com.bluesky.iplatform.component.from.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.object.BaseObject;

@Data
@Component(value = "FormSchema")
@Entity
@Scope(value = "prototype")
@Table(name = "fm_formschema", schema = "public")
public class FormSchema extends BaseObject implements java.io.Serializable{
	
	private static final long serialVersionUID = -5043067570970756389L;
	
	/**
	 * 状态：激活
	 */
	public static final int STATUS_ACTIVE = 0;
	
	
	/**
	 * 状态：不可用状态
	 */
	public static final int STATUS_INACTIVE = -1;
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "description", length = 500)
	private String description; 
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "referenceid")
	private Integer referenceid;
	
	@Transient
	private List<FormField> fields;
}
