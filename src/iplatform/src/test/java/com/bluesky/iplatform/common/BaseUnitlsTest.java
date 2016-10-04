package com.bluesky.iplatform.common;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;

import com.bluesky.iplatform.commons.cache.EhcacheUtils;
import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.profile.service.ProfileManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

@SpringApplicationContext({ "/spring/applicationContext.xml" })
public class BaseUnitlsTest extends UnitilsJUnit4 {

	// 加载Spring上下文
	@SpringApplicationContext
	public ApplicationContext ctx;
	
	protected User systemAdmin;
	
	@Before
	public void setup(){
		initCacheManagerServlet();
		setAdminUser();
	}

	/**
	 * 初始化cache
	 */
	private void initCacheManagerServlet() {
		try {
			EhcacheUtils cacheUtils = EhcacheUtils.getInstance(EhcacheUtils.EHCACHE_KEY_PLATFORM);
			SequenceUtils.initSequence(cacheUtils);// 初始化序列緩存
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * 设置System Admin
	 */
	private void setAdminUser(){
		ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");	
		systemAdmin = manager.getAdminUser();
	}
	
	/**
	 * 初始化并返回User对象
	 * （该对象仅包括id, companyID两个属性）
	 * @param userID
	 * @param companyID
	 */
	public User getUser(int userID, int companyID){
		ApplicationContext ctx = BaseContext.getApplicationContext();
		User user = ctx.getBean("User",User.class);
		user.setId(userID);
		user.setCompanyID(companyID);
		return user;
	}

}
