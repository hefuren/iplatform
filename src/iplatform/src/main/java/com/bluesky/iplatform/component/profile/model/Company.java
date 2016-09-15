package com.bluesky.iplatform.component.profile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Company entity.
 *  
 * @author ElwinHe  
 */
@Data
@Component(value = "Company")
@Scope(value = "prototype")
@Entity
@Table(name = "st_company", schema = "public")
public class Company implements java.io.Serializable {

	
	private static final long serialVersionUID = 7946163610150676020L;
	
	/**
	 * 状态：激活的
	 */
	public static final int STATUS_ACTIVATED = 1;

	/**
	 * 状态：未激活的
	 */
	public static final int STATUS_UNACTIVATED = 0;
	
	
	// Fields
	@Id
	@OrderBy
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "description", length = 500)
	private String description;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "contactname", length = 50)
	private String contactname;
	
	@Column(name = "contactemail", length = 50)
	private String contactemail;
	
	@Column(name = "contactphone", length = 50)
	private String contactphone;
	
	@Column(name = "contactfax", length = 50)
	private String contactfax;
	
	@Column(name = "zip", length = 20)
	private String zip;
	
	/**
	 * 公司网址
	 */
	@Column(name = "url", length = 100)
	private String url;
	
	/**
	 * 雇员数量（后续扩展20,50,100,100~200,500,500+）
	 */
	@Column(name = "employess")
	private Integer employess;
	
	@Column(name = "adress", length = 500)
	private String adress;

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Company(Integer id, String name, String description,
			Integer status, Integer type, String contactname,
			String contactemail, String contactphone, String contactfax,
			String zip, String url, Integer employess, String adress) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.type = type;
		this.contactname = contactname;
		this.contactemail = contactemail;
		this.contactphone = contactphone;
		this.contactfax = contactfax;
		this.zip = zip;
		this.url = url;
		this.employess = employess;
		this.adress = adress;
	}

	// Property accessors
//	@Id
//	@OrderBy
//	@Column(name = "id", unique = true, nullable = false)
//	public Integer getId() {
//		return this.id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

//	@Column(name = "name", length = 50)
//	public String getName() {
//		return this.name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

//	@Column(name = "description", length = 500)
//	public String getDescription() {
//		return this.description;
//	}

//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	@Column(name = "status")
//	public Integer getStatus() {
//		return this.status;
//	}
//
//	public void setStatus(Integer status) {
//		this.status = status;
//	}
//
//	@Column(name = "type")
//	public Integer getType() {
//		return this.type;
//	}
//
//	public void setType(Integer type) {
//		this.type = type;
//	}

//	@Column(name = "contactname", length = 50)
//	public String getContactname() {
//		return this.contactname;
//	}
//
//	public void setContactname(String contactname) {
//		this.contactname = contactname;
//	}
//
//	@Column(name = "contactemail", length = 50)
//	public String getContactemail() {
//		return this.contactemail;
//	}
//
//	public void setContactemail(String contactemail) {
//		this.contactemail = contactemail;
//	}

//	@Column(name = "contactphone", length = 50)
//	public String getContactphone() {
//		return this.contactphone;
//	}
//
//	public void setContactphone(String contactphone) {
//		this.contactphone = contactphone;
//	}
//
//	@Column(name = "contactfax", length = 50)
//	public String getContactfax() {
//		return this.contactfax;
//	}
//
//	public void setContactfax(String contactfax) {
//		this.contactfax = contactfax;
//	}

//	@Column(name = "zip", length = 20)
//	public String getZip() {
//		return this.zip;
//	}
//
//	public void setZip(String zip) {
//		this.zip = zip;
//	}
//
//	@Column(name = "url", length = 100)
//	public String getUrl() {
//		return this.url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	@Column(name = "employess")
//	public Integer getEmployess() {
//		return this.employess;
//	}
//
//	public void setEmployess(Integer employess) {
//		this.employess = employess;
//	}
//
//	@Column(name = "adress", length = 500)
//	public String getAdress() {
//		return this.adress;
//	}
//
//	public void setAdress(String adress) {
//		this.adress = adress;
//	}

}