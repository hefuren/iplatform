package com.bluesky.iplatform.component.codetable.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.CollationKey;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.hierarchy.Hierarchyable;
import com.bluesky.iplatform.commons.hierarchy.SerializableComparator;
import com.bluesky.iplatform.commons.object.BaseObject;

/**
 * CodeTable entity.
 * 
 * @author ElwinHe
 */
@Component(value = "CodeTable")
@Entity
@Scope(value = "prototype")
@Table(name = "st_codetable", schema = "public")
public class CodeTable extends BaseObject implements Hierarchyable,
		Serializable {

	public static SerializableComparator NameComparator = new SerializableComparator() {
		private static final long serialVersionUID = -5996256934150540497L;

		public int compare(Object obj1, Object obj2) {
			RuleBasedCollator collator = (RuleBasedCollator) RuleBasedCollator
					.getInstance(Locale.CHINA);
			CodeTable role1 = (CodeTable) obj1;
			CodeTable role2 = (CodeTable) obj2;
			CollationKey c1 = collator.getCollationKey(role1.getName());
			CollationKey c2 = collator.getCollationKey(role2.getName());
			return collator.compare(c1.getSourceString(), c2.getSourceString());
		}
	};

	
	public static final int TYPE_SYSTEM = 0; // 系统固有代码表
	public static final int TYPE_OWN = 0; // 自定义代码表
	
	public static final int STATUS_ACTIVE = 0;
	public static final int STATUS_DELETE = -1;
	
	/* 自动生成ID */
	public static int ID_RULE_AUTO = 0;//
	/* 手动生成ID */
	public static int ID_RULE_MANUL = 1;//
	
	
	// Fields

	private Integer id;
	private String tablename;
	private String name;
	private String description;
	private Integer status;
	private Integer idRule;
	private Integer category;
	private Integer source;
	private Integer type;
	private Integer parentID;
	private String sortby;
	private Timestamp createTime;
	private Integer createBy;
	private Timestamp lastUpdateTime;
	private Integer lastUpdateBy;
	private Integer owner;
	private Integer companyID;
	private String name1;
	private String description1;
	private Set<CodeTableField> codeTableFields = new HashSet<CodeTableField>(0);
	private List<CommonCode> codeList = new ArrayList<CommonCode>();

	// Constructors

	/** default constructor */
	public CodeTable() {
	}

	/** minimal constructor */
	public CodeTable(Integer id, String tablename, Integer companyID) {
		this.id = id;
		this.tablename = tablename;
		this.companyID = companyID;
	}

	/** full constructor */
	public CodeTable(Integer id, String tablename, String name,
			String description, Integer status, Integer idRule, Integer category,
			Integer source, Integer type, Integer parentID, String sortby,
			Timestamp createTime, Integer createBy, Timestamp lastUpdateTime,
			Integer lastUpdateBy, Integer owner, Integer companyID,
			String name1, String description1,
			Set<CodeTableField> codeTableFields) {
		this.id = id;
		this.tablename = tablename;
		this.name = name;
		this.description = description;
		this.status = status;
		this.idRule = idRule;
		this.category = category;
		this.source = source;
		this.type = type;
		this.parentID = parentID;
		this.sortby = sortby;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastUpdateTime = lastUpdateTime;
		this.lastUpdateBy = lastUpdateBy;
		this.owner = owner;
		this.companyID = companyID;
		this.name1 = name1;
		this.description1 = description1;
		this.codeTableFields = codeTableFields;
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

	@Column(name = "tablename", nullable = false, length = 120)
	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
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

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "idRule")
	public Integer getIdRule() {
		return this.idRule;
	}

	public void setIdRule(Integer idRule) {
		this.idRule = idRule;
	}

	@Column(name = "category")
	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	@Column(name = "source")
	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "parentid")
	public Integer getParentID() {
		return this.parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	@Column(name = "sortby", length = 50)
	public String getSortby() {
		return this.sortby;
	}

	public void setSortby(String sortby) {
		this.sortby = sortby;
	}

	@Column(name = "createtime")
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "createby")
	public Integer getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	@Column(name = "lastupdatetime")
	public Timestamp getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Column(name = "lastupdateby")
	public Integer getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	public void setLastUpdateBy(Integer lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	@Column(name = "owner")
	public Integer getOwner() {
		return this.owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	@Column(name = "companyid", nullable = false)
	public Integer getCompanyID() {
		return this.companyID;
	}

	public void setCompanyID(Integer companyid) {
		this.companyID = companyid;
	}

	@Column(name = "name1", length = 120)
	public String getName1() {
		return this.name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	@Column(name = "description1", length = 500)
	public String getDescription1() {
		return this.description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "codeTable")
	public Set<CodeTableField> getCodeTableFields() {
		return this.codeTableFields;
	}

	public void setCodeTableFields(Set<CodeTableField> codeTableFields) {
		this.codeTableFields = codeTableFields;
	}

	@Transient
	// 表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性
	public List<CommonCode> getCodeList() {
		return codeList;
	}

	public void setCodeList(List<CommonCode> codeList) {
		this.codeList = codeList;
	}

	@Transient
	public List<CommonCode> getAvailableCodeList() {
		List<CommonCode> returnList = new ArrayList<CommonCode>();
		for (CommonCode code : this.codeList) {
			if (code.getStatus() != -1) {
				returnList.add(code);
			}
		}
		return returnList;
	}
	
	@Transient
	public String getTableTypeName(){
		String typeName = "";
		if(type == CodeTable.TYPE_SYSTEM){
			typeName = "系统固有";
		}else if(type == CodeTable.TYPE_OWN){
			typeName = "用户自定义";
		}
		return typeName;
	}
	
	/**
	 * 获取CommonCode
	 * @param codeID
	 * @return
	 */
	public CommonCode getCommonCode(int codeID){
		List<CommonCode> codes = codeList;
		for(CommonCode code : codes){
			if(code.getId() == codeID){
				return code;
			}
		}
		return null;
	}

}