package com.bluesky.iplatform.component.from.dao;

import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;
import com.bluesky.iplatform.component.from.model.FormList;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.profile.model.User;

public interface FormListDAO<T> extends BaseSingleMyBatisDAO<T> {
	
	public FormList getMode(User user, FormSchema formSchema);

}
