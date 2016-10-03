package com.bluesky.iplatform.common;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.bluesky.iplatform.commons.db.PageInfo;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.profile.service.ProfileManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class BaseDAOTest extends BaseUnitlsTest {

	@Test
	public void testBatchUpdate(){
		try {
			ApplicationContext cx = BaseContext.getApplicationContext();
			ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");	
			PageInfo pageInfo = cx.getBean("PageInfo",PageInfo.class);
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("companyID", 1000);
			pageInfo.setConditions(conditions);
			pageInfo = manager.getUsers(systemAdmin, pageInfo);
			List<User> modes = pageInfo.getItems();
			
			for(User mode : modes){
				String firstname = mode.getFirstname();
				firstname += "_update";
				mode.setFirstname(firstname);
			}
			manager.batchUpdateUsers(modes);
			
			
			
		} catch (Exception e) {
			fail("Not yet implemented");
			e.printStackTrace();
		}
	}

}
