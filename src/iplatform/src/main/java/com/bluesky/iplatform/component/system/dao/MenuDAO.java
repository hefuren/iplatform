package com.bluesky.iplatform.component.system.dao;

import java.util.List;

import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;
import com.bluesky.iplatform.commons.hierarchy.Hierarchy;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.system.model.Menu;

public interface MenuDAO<T>  extends BaseSingleMyBatisDAO<T> {
	
	public Hierarchy getModelMenus(User user, int modelID);
	
	public List<Menu> getModels(User user);

}
