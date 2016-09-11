package com.bluesky.iplatform.component.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bluesky.iplatform.commons.hierarchy.Hierarchy;
import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.system.dao.MenuDAO;
import com.bluesky.iplatform.component.system.model.Menu;

@Repository(value = "MenuDAOImpl")
public class MenuDAOImpl extends BaseSingleMyBatisDAOImpl<Menu> implements MenuDAO<Menu> {

	@Override
	public Hierarchy getModelMenus(User user, int modelID) {
		Hierarchy hierarchy = new Hierarchy(Menu.SeqComparator);
		Menu rootNode = getMode(user, modelID);
		hierarchy.setRootNode(rootNode);
		
		List<Menu> subMenus = this.getModesByProperty("parentID", new Integer(modelID));
		for(Menu subMenu : subMenus){
			hierarchy.addObject(subMenu);
		}
		
		return hierarchy;
	}
	
	@Override
	public List<Menu> getModels(User user) {
		//获取模块菜单，parentID = 0
		List<Menu> menus = getModesByProperty("parentID", new Integer(0));
		
		return menus;
	}
	
	
	/**
	 *
	Hierarchy hierarchy = new Hierarchy(Function.SequenceComparator);
    Function rootNode = new Function();
    rootNode.setId(0);
    hierarchy.setRootNode(rootNode);
    ArrayList oValues = new ArrayList();
    oValues.add(new Integer(companyID));
    DBRowSet oRowSet = search(selectSQL, oValues);
    for (int i = 0; i < oRowSet.getRowCount(); i++) {
      Function oMode = new Function();
      int iColumn = 0;
      oMode.setId(oRowSet.getInt(i, iColumn++));
      oMode.setParentID(oRowSet.getInt(i, iColumn++));
      oMode.setName(oRowSet.getString(i, iColumn++));
      oMode.setDescription(oRowSet.getString(i, iColumn++));
      oMode.setDescription1(oRowSet.getString(i, iColumn++));
      oMode.setStatus(oRowSet.getInt(i, iColumn++));
      oMode.setType(oRowSet.getInt(i, iColumn++));
      oMode.setPolicy(oRowSet.getInt(i, iColumn++));
      oMode.setSeqNo(oRowSet.getInt(i, iColumn++));
      oMode.setClientURL(oRowSet.getString(i, iColumn++));
      oMode.setFolderURL(oRowSet.getString(i, iColumn++));
      oMode.setImageURL(oRowSet.getString(i, iColumn++));
      oMode.setCompanyID(companyID);
      hierarchy.addObject(oMode);
    }
    return hierarchy;
	 * 
	 */

}
