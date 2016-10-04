package com.bluesky.iplatform.component.from.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component(value = "FormViewItem")
@Entity
@Scope(value = "prototype")
@Table(name = "fm_formviewitem", schema = "public")
public class FormViewItem {
	
	/**
	 * 控件类型：Select 下拉框
	 */
	public static final int CONTROL_TYPE_SELECT = 1;
	
	/**
	 * 控件类型：Checkbox 复选框
	 */
	public static final int CONTROL_TYPE_CHECKBOX = 2;
	
	/**
	 * 控件类型：Radio 单选框
	 */
	public static final int CONTROL_TYPE_RADIO = 3;
	
	/**
	 * 控件类型：Text 文本框
	 */
	public static final int CONTROL_TYPE_TEXT = 4;
	
	
	/**
	 * 控件类型：TextArea 文本框
	 */
	public static final int CONTROL_TYPE_TEXTAREA = 5;
	
	
	/**
	 * 控件类型：RichText 富文本框
	 */
	public static final int CONTROL_TYPE_RICHTEXT = 6;
	
	
	/**
	 * 控件类型：Calendar 日历控件
	 */
	public static final int CONTROL_TYPE_CALENDAR = 7;
	
	/**
	 * 控件类型：TitleRow 标题行
	 */
	public static final int CONTROL_TYPE_TITLE = 8;
	
	/**
	 * 控件类型：Slider 滑块
	 */
	public static final int CONTROL_TYPE_SLIDER = 9;
	
	/**
	 * 控件类型：Blank 空白行
	 */
	public static final int CONTROL_TYPE_BLANDK = 10;
	
	/**
	 * 控件类型：Divide Line 分割线
	 */
	public static final int CONTROL_TYPE_DIVIDELINE = 11;
	
	/**
	 * 控件类型：Attachments 附件
	 */
	public static final int CONTROL_TYPE_ATTACHEMENTS = 12;
	
	/**
	 * 宽度：50% 行宽
	 */
	public static final int WIDTH_HALF_ROW = 0;
	
	/**
	 * 宽度：100% 行宽
	 */
	public static final int WIDTH_TOTAL_ROW = 1;
	
	/**
	 * 编辑状态：可编辑，可选
	 */
	public static final int EDITFLAG_EDIT = 0;
	
	/**
	 * 编辑状态：可编辑，必填
	 */
	public static final int EDITFLAG_EDIT_REQUIRED = 1;
	
	/**
	 * 编辑状态：不可编辑
	 */
	public static final int EDITFLAG_READ = -1;
	
	
	
	

	// Fields
	@Id
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "viewid", nullable = false)
	private Integer viewID;
	
	@Column(name = "fieldid", length = 50)
	private String fieldID;
	
	/**
	 * 控件类型（文本、下拉、单选、复选……）
	 */
	@Column(name = "controltype")
	private Integer controlType;
	
	@Column(name = "width")
	private Integer width;
	
	@Column(name = "height")
	private Integer height;
	
	@Column(name = "editflag")
	private Integer editFlag;
	
	@Column(name = "defaultvalue", length = 500)
	private String defaultValue;
	
	@Column(name = "seqno")
	private Integer seqno;
	
	@Column(name = "companyid", nullable = false)
	private Integer companyID;

}
