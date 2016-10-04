package com.bluesky.iplatform.component.from.service;

import java.util.List;

import com.bluesky.iplatform.component.from.model.FormField;
import com.bluesky.iplatform.component.from.model.FormFilter;
import com.bluesky.iplatform.component.from.model.FormList;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.from.model.FormView;
import com.bluesky.iplatform.component.from.model.FormViewItem;
import com.bluesky.iplatform.component.profile.model.User;

public interface FormManager {
	/**
	 * 新建FormSchema
	 * @param user
	 * @param formSchema
	 * @return
	 * @
	 */
	public void newFormSchema(User user, FormSchema formSchema) ;
	
	/**
	 * 删除FormSchema
	 * @param user
	 * @param ids (对象ID数组)
	 * @
	 */
	public void deleteFormSchemas(User user, int[] ids) ;
	
	/**
	 * 获取FormSchema List
	 * @param user
	 * @return
	 * @
	 */
	public void getFormSchemas(User user) ;

	/**
	 * 获取FormSchema 对象
	 * @param user
	 * @param id
	 * @return
	 * @
	 */
	public FormSchema getFormSchema(User user, int id) ;
	
	/**
	 * 获取 Schema 字段列表
	 * @param user
	 * @param formSchema
	 * @return
	 * @
	 */
	public List<FormField> getSchemaFormFields(User user, FormSchema formSchema) ;
	
	/**
	 * 保存 Schema 字段
	 * @param user
	 * @param formSchema
	 * @param fields
	 * @return
	 * @
	 */
	public void saveSchemaFormFields(User user, List<FormField> fields) ;

	/**
	 * 获取 Schema 视图列表
	 * @param user
	 * @param formSchema
	 * @return
	 * @
	 */
	public List<FormView> getSchemaFormViews(User user, FormSchema formSchema) ;
	
	
	
	/**
	 * 获取视图的字段
	 * @param user
	 * @param formView
	 * @return
	 * @
	 */
	public List<FormViewItem> getFormViewItem(User user, FormView formView) ;
	
	/**
	 * 获取Schema 列表
	 * @param user
	 * @param formSchema
	 * @return
	 * @
	 */
	public FormList getFormList(User user, FormSchema formSchema) ;
	
	/**
	 * 新建FormList 
	 * @param user
	 * @param formList
	 * @
	 */
	public void newFormList(User user, FormList formList) ;
	
	/**
	 * 保存表单过滤器
	 * @param user
	 * @param formFilter
	 * @return
	 * @
	 */
	public void saveFormFilter(User user, FormFilter formFilter) ;
	
	/**
	 * 获取表单过滤器
	 * @param user
	 * @param formSchema
	 * @return
	 * @
	 */
	public FormFilter getFormFilter(User user, FormSchema formSchema) ;
}
