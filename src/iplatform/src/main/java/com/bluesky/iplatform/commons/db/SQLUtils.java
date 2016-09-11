package com.bluesky.iplatform.commons.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.bluesky.iplatform.commons.utils.BaseContext;
import com.github.abel533.sql.SqlMapper;


@Repository(value = "SQLUtils")
public class SQLUtils {

	public Map getTablesMaxID(List<String> tables, Map hashMap) throws Exception{
		ApplicationContext ctx = BaseContext.getApplicationContext();
		SqlMapper sqlMapper = ctx.getBean("sqlMapper", SqlMapper.class);
		for (String table : tables) {
			String sql = "select max(id) from " + table + " where 1=1";
			List list = sqlMapper.selectList(sql);
			long maxID = 1000;
			if (list != null && list.size() == 1) {
				Object object = list.get(0);
				if (object instanceof Integer) {
					Integer temp = (Integer) object;
					maxID = (new Long(temp)).longValue();
				} else if (object instanceof Long) {
					maxID = ((Long) object).longValue();
				}
				hashMap.put(table, new Long(maxID));
			}
		}
		return hashMap;
	}
}
