package com.bluesky.iplatform.commons.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ehcache.Cache;
import org.springframework.context.ApplicationContext;

import com.bluesky.iplatform.commons.cache.EhcacheUtils;
import com.bluesky.iplatform.commons.utils.BaseContext;
import  com.bluesky.iplatform.component.utils.ComponentFactory;
import com.bluesky.iplatform.component.codetable.model.CodeTable;
import com.bluesky.iplatform.component.codetable.service.CodeTableManager;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.profile.service.ProfileManager;

public class SequenceUtils {

	private static Map sequenceMap = new HashMap();
	
	public static final String SEQUENCECACHE_KEY = "sequenceCache"; // Sequence Cache Key
	
	private static List<String> tableLists = new ArrayList<String>();
	
	public static void initSequence(EhcacheUtils cacheUtils) throws Exception{
		if(cacheUtils == null){
			cacheUtils = EhcacheUtils.getInstance(EhcacheUtils.EHCACHE_KEY_PLATFORM);
		}
		Cache sequenceEhcache = cacheUtils.getEhcache(EhcacheUtils.EHCACHE_KEY_SEQUENCE);
		SQLUtils sqlUtils = (SQLUtils)BaseContext.getBean("SQLUtils");
		
		//初始化 tableLists
		initTableLists();
		sequenceMap = sqlUtils.getTablesMaxID(tableLists,sequenceMap);
		cacheUtils.put(sequenceEhcache, SEQUENCECACHE_KEY, sequenceMap);
	}
	
	public synchronized static Long getSequence(String table){
		Long seq = null;
		try {
			EhcacheUtils cacheUtils = EhcacheUtils.getInstance(EhcacheUtils.EHCACHE_KEY_PLATFORM);
			Cache sequenceEhcache = cacheUtils.getEhcache(EhcacheUtils.EHCACHE_KEY_SEQUENCE);
			Map map;
			
			map = (Map)cacheUtils.get(sequenceEhcache, SEQUENCECACHE_KEY);
			
			Long maxID = (Long)map.get(table);
			maxID = (maxID < 1000) ? 1000 : maxID + 1;
			map.put(table, new Long(maxID));
			seq = maxID.longValue();
			return seq;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seq;
	}
	
	public synchronized static Long[] getSequence(String table,int iCount) throws Exception{
		Long[] seq = new Long[iCount];
		EhcacheUtils cacheUtils = EhcacheUtils.getInstance(EhcacheUtils.EHCACHE_KEY_PLATFORM);
		Cache sequenceEhcache = cacheUtils.getEhcache(EhcacheUtils.EHCACHE_KEY_SEQUENCE);		
		Map map = (Map)cacheUtils.get(sequenceEhcache, SEQUENCECACHE_KEY);
		Long maxID = (Long)map.get(table);
		for(int i=0; i<iCount; i++){
			maxID = (maxID < 1000) ? 1000 : maxID + 1;
			seq[i] = maxID;
		}
		map.put(table, maxID);
		return seq;
	}
	
	private static void initTableLists(){
		//Schema tables
		tableLists.add("fm_formSchema");
		tableLists.add("fm_formField");
		tableLists.add("fm_formList");
		tableLists.add("fm_formListItem");
		tableLists.add("fm_formView");
		tableLists.add("fm_formViewItem");
		tableLists.add("fm_formfilter");
		tableLists.add("fm_formfilterItem");
		
		//system tables
		tableLists.add("st_department");
		tableLists.add("st_user");
		tableLists.add("st_codetable");
		
		//CodeTable 
//		List<String> codeTables = getCodeTableList();
//		tableLists.addAll(codeTables);
		
		//hw tables
		/*
		tableLists.add("hw_project");
		tableLists.add("hw_projectSalesInfo");
		tableLists.add("hw_projectrevenuecode");
		tableLists.add("hw_incomestatement");
		tableLists.add("hw_projectBudget");
		tableLists.add("hw_projectBudgetBaseLine");
		tableLists.add("hw_projectBudgetHistory");
		*/
	}
	
	private static List<String> getCodeTableList(){
		CodeTableManager manager = (CodeTableManager)ComponentFactory.getManager("CodeTableManager");
		ProfileManager profileManager = (ProfileManager)ComponentFactory.getManager("ProfileManager");
		User adminUser = profileManager.getAdminUser();
		ApplicationContext ctx = BaseContext.getApplicationContext();
		PageInfo pageInfo = ctx.getBean("PageInfo", PageInfo.class);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("companyID", new Integer(adminUser.getCompanyID()));
		pageInfo.setConditions(conditions);
		pageInfo.setPaged(false);
		
		pageInfo = manager.getCodeTables(adminUser, pageInfo);
		List<CodeTable> items = pageInfo.getItems();
		List<String> tbNames = new ArrayList();
		
		for(CodeTable table : items){
			tbNames.add(table.getTablename());
		}
		
		return tbNames;
	}
	
	/**
	 * 动态增加表时，更新Sequence Cache
	 * @param table
	 * @throws Exception
	 */
	public static void dynamicUpdateSequenceCache(String table){
//		OSCacheManager oscacheAdmin = OSCacheManager.getInstance();
//		Map map;
//		try {
//			map = (Map)oscacheAdmin.getOscacheAdmin().getFromCache(OSCacheUtils.SEQUENCECACHE_KEY);
//			if(map.get(table) == null){
//				map.put(table, new Long(0));
//			}
//		} catch (NeedsRefreshException e) {
//			e.printStackTrace();
//		}
		
	}
}
