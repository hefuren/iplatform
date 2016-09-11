package com.bluesky.iplatform.component.codetable.model;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bluesky.iplatform.commons.hierarchy.SerializableComparator;
import com.bluesky.iplatform.commons.object.BatchObject;

@Component(value = "CommonCode")
@Scope(value = "prototype")
public class CommonCode extends BatchObject {

	private static final long serialVersionUID = -390273625239442675L;
	public static final int ROOT = 0;
	public static final int CATEGORY = 1;
	public static final int LEAF = 2;
	
	public static final int STATUS_ACTIVE = 0;
	public static final int STATUS_DELETE = -1;

	private String name = "";

	private String description = "";
	private int seqNo;
	private int parentID;
	private int status;
	private HashMap<Object, Object> properties = new HashMap<Object, Object>();

	public static SerializableComparator SequenceComparator = new SerializableComparator() {
		private static final long serialVersionUID = -2238634533881771683L;

		public int compare(Object obj1, Object obj2) {
			CommonCode field1 = (CommonCode) obj1;
			CommonCode field2 = (CommonCode) obj2;
			return field1.getSeqNo() - field2.getSeqNo();
		}
	};

	public void setProperty(String propName, Object propValue) {
		String key = propName;
		int index = key.indexOf(".");
		if (index != -1) {
			key = key.substring(index + 1);
		}
		if (key.equals("name"))
			setName(propValue == null ? "" : (String) propValue);
		else if (key.equals("description"))
			setDescription(propValue == null ? "" : (String) propValue);
		else if (key.equals("seqNo"))
			setSeqNo(new BigDecimal(propValue.toString()).intValue());
		else if (key.equals("parentID"))
			setParentID(new BigDecimal(propValue.toString()).intValue());
		else if (key.equals("companyID"))
			setCompanyID(new BigDecimal(propValue.toString()).intValue());
		else if (key.equals("status"))
			setStatus(propValue == null ? 0 : new BigDecimal(
					propValue.toString()).intValue());
		else
			this.properties.put(key, propValue);
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public int getParentID() {
		return this.parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public HashMap<Object, Object> getProperties() {
		return this.properties;
	}

	public void setProperties(HashMap<Object, Object> properties) {
		this.properties = properties;
	}

	public Object clone() {
		CommonCode code = new CommonCode();
		code.setId(getId());
		code.setName(new String(getName()));
		code.setDescription(new String(getDescription()));
		code.setSeqNo(getSeqNo());
		code.setCompanyID(getCompanyID());
		code.setParentID(getParentID());
		code.setStatus(getStatus());
		code.setProperties((HashMap<Object, Object>) getProperties().clone());
		return code;
	}

	public int compareTo(Object arg0) {
		CommonCode cc = (CommonCode) arg0;
		return getName().compareTo(cc.getName());
	}
}
