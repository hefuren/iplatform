package com.bluesky.iplatform.commons.web;

import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.bluesky.iplatform.commons.cache.EhcacheUtils;
import com.bluesky.iplatform.commons.db.SequenceUtils;

public class InitCacheManagerServlet  extends HttpServlet{

	private static final long serialVersionUID = 5936195460241311008L;

	public void init(ServletConfig config) throws ServletException {
		try{
			
			EhcacheUtils cacheUtils = EhcacheUtils.getInstance(EhcacheUtils.EHCACHE_KEY_PLATFORM);
			SequenceUtils.initSequence(cacheUtils);//初始化序列緩存
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
