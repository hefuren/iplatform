package com.bluesky.iplatform.component.from.dao;

import java.util.List;

import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.from.model.FormView;
import com.bluesky.iplatform.component.from.model.FormViewItem;
import com.bluesky.iplatform.component.profile.model.User;

public interface FormViewDAO<T> extends BaseSingleMyBatisDAO<T> {
	
	public List<FormView> getModes(User user, FormSchema form);
	
	public List<FormViewItem> getFormViewItems(User user, FormView formView);

}
