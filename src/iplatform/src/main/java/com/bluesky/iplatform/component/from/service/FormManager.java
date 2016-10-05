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
	 * (删除Form时，需要删除对应的其它对象)
	 * @param user
	 * @param ids (对象ID数组)
	 * @
	 */
	public void deleteFormSchemas(User user, int[] ids) ;
	
	/**
	 * 修改Form对象
	 * @param user
	 * @param form
	 */
	public void updateFormSchema(User user, FormSchema form);
	
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
	public List<FormField> getFormFields(User user, FormSchema formSchema) ;
	
	/**
	 * 保存 Schema 字段
	 * @param user
	 * @param formSchema
	 * @param fields
	 * @return
	 * @
	 */
	public void saveFormFields(User user, List<FormField> fields) ;

	/**
	 * 获取 Schema 视图列表
	 * @param user
	 * @param formSchema
	 * @return
	 * @
	 */
	public List<FormView> getFormViews(User user, FormSchema formSchema) ;
	
	/**
	 * 新增表单视图对象
	 * （新增View,包括对ViewItem新增）
	 * @param user
	 * @param view
	 */
	public void newFormView(User user, FormView view);
	
	/**
	 * 修改表单视图对象
	 * @param user
	 * @param view
	 */
	public void updateFormView(User user, FormView view);
	
	/**
	 * 删除表单视图
	 * （删除对应的view Item）
	 * @param user
	 * @param view
	 */
	public void deleteFormView(User user, FormView view);

	/**
	 * 删除表单视图
	 * （删除对应的view Item）
	 * @param user
	 * @param ids
	 */
	public void deleteFormViews(User user, int[] ids);
	
	/**
	 * 获取FormView对象
	 * @param user
	 * @param id
	 * @return
	 */
	public FormView getFormView(User user, int id);
	
	/**
	 * 获取视图的字段
	 * @param user
	 * @param formView
	 * @return
	 */
	public List<FormViewItem> getFormViewItems(User user, FormView formView) ;
	
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
	 * @param formList（包含formListItem时，一起保存）
	 */
	public void newFormList(User user, FormList formList) ;

	/**
	 * 修改表单列表信息
	 * @param user
	 * @param formList
	 */
	public void updateFormList(User user, FormList formList) ;
	
	/**
	 * 新建表单过滤器
	 * @param user
	 * @param formFilter
	 * @return
	 */
	public void newFormFilter(User user, FormFilter formFilter) ;
	
	/**
	 * 修改表单过滤器
	 * @param user
	 * @param formFilter
	 * @return
	 */
	public void updateFormFilter(User user, FormFilter formFilter) ;
	
	/**
	 * 删除表单过滤器
	 * （删除对应的formFilterItem）
	 * @param user
	 * @param formFilter
	 */
	public void deleteFormFilter(User user, FormFilter formFilter);
	
	/**
	 * 获取表单过滤器
	 * @param user
	 * @param formSchema
	 * @return
	 * @
	 */
	public FormFilter getFormFilters(User user, int id) ;
	
	/**
	 * 获取表单过滤器
	 * @param user
	 * @param formSchema
	 * @return
	 * @
	 */
	public List<FormFilter> getFormFilters(User user, FormSchema formSchema) ;
}
