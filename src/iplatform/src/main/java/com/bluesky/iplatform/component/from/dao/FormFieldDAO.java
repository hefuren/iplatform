package com.bluesky.iplatform.component.from.dao;

import java.util.List;

import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;
import com.bluesky.iplatform.component.from.model.FormField;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.profile.model.User;

public interface FormFieldDAO<T> extends BaseSingleMyBatisDAO<T> {
	
	public List<FormField> getFormFields(User user, FormSchema form);

}
