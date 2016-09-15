package com.bluesky.iplatform.common;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;

import com.bluesky.iplatform.commons.cache.EhcacheUtils;
import com.bluesky.iplatform.commons.db.SequenceUtils;

@SpringApplicationContext({ "/spring/applicationContext.xml" })
public class BaseUnitlsTest extends UnitilsJUnit4 {

	// 加载Spring上下文
	@SpringApplicationContext
	public ApplicationContext ctx;

	/**
	 * 初始化Cache
	 */
	@Before
	public void initCacheManagerServlet() {
		try {
			EhcacheUtils cacheUtils = EhcacheUtils.getInstance(EhcacheUtils.EHCACHE_KEY_PLATFORM);
			SequenceUtils.initSequence(cacheUtils);// 初始化序列緩存
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

}
