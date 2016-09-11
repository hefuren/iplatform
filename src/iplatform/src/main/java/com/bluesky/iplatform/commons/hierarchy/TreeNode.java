package com.bluesky.iplatform.commons.hierarchy;

import java.io.Serializable;

public class TreeNode implements Hierarchyable, Serializable{
	
	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * 父ID
	 */
	private Integer parentID;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 图片 imgcss
	 */
	private String imgcss;
	
	/**
	 * 链接
	 */
	private String url;
	
	/**
	 * js事件函数
	 */
	private String function;
	
	/**
	 * 层级
	 */
	private Integer level;
	
	/**
	 * 序号
	 */
	private Integer seqno;
	
	/**
	 * TreeNode 实际对象
	 */
	private Object actualObject;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentID() {
		return parentID;
	}
	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgcss() {
		return imgcss;
	}
	public void setImgcss(String imgcss) {
		this.imgcss = imgcss;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getSeqno() {
		return seqno;
	}
	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}
	public Object getActualObject() {
		return actualObject;
	}
	public void setActualObject(Object actualObject) {
		this.actualObject = actualObject;
	}
	

	
}
