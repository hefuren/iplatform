package com.bluesky.iplatform.component.from.dao;

import java.util.List;

import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;
import com.bluesky.iplatform.component.from.model.FormFilter;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.profile.model.User;

public interface FormFilterDAO<T> extends BaseSingleMyBatisDAO<T> {
	
	public List<FormFilter> getModes(User user, FormSchema formSchema);

}
