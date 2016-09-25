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

import lombok.Data;

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
@Data
@Component(value = "CodeTable")
@Entity
@Scope(value = "prototype")
@Table(name = "st_codetable", schema = "public")
public class CodeTable extends BaseObject implements Hierarchyable,
		Serializable {

	private static final long serialVersionUID = 5382330577081526330L;


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
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "tablename", nullable = false, length = 120)
	private String tablename;
	
	@Column(name = "name", length = 120)
	private String name;
	
	@Column(name = "description", length = 500)
	private String description;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "idRule")
	private Integer idRule;
	
	@Column(name = "category")
	private Integer category;
	
	@Column(name = "source")
	private Integer source;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "parentid")
	private Integer parentID;
	
	@Column(name = "sortby", length = 50)
	private String sortby;
	
	@Column(name = "owner")
	private Integer owner;
	
	@Column(name = "name1", length = 120)
	private String name1;
	
	@Column(name = "description1", length = 500)
	private String description1;
	
	@Transient
	private Set<CodeTableField> codeTableFields = new HashSet<CodeTableField>(0);
	
	@Transient
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
	public Set<CodeTableField> getCodeTableFields() {
		return this.codeTableFields;
	}

	public void setCodeTableFields(Set<CodeTableField> codeTableFields) {
		this.codeTableFields = codeTableFields;
	}

	// 表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性
	@Transient
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
	@Transient
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