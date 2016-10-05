package com.bluesky.iplatform.component.from.dao;

import java.util.List;

import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.from.model.FormView;
import com.bluesky.iplatform.component.from.model.FormViewItem;
import com.bluesky.iplatform.component.profile.model.User;

public interface FormViewDAO<T> extends BaseSingleMyBatisDAO<T> {
	
	/**
	 * 通过表单对象查找对应的视图
	 * （注意：该方法查找对应的视图Formview对象不包含“FormViewItem”对象）
	 * @param user
	 * @param form
	 * @return
	 */
	public List<FormView> getModes(User user, FormSchema form);
	
	/**
	 * 通过对应的表单对象，查找 formViewItem 信息
	 * @param user
	 * @param formView
	 * @return
	 */
	public List<FormViewItem> getFormViewItems(User user, FormView formView);

}
