package com.bluesky.iplatform.component.form;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.bluesky.iplatform.common.BaseUnitlsTest;
import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.from.model.FormFilter;
import com.bluesky.iplatform.component.from.model.FormFilterItem;
import com.bluesky.iplatform.component.from.service.FormManager;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class FilterTest extends BaseUnitlsTest {

	@Test
	public void testNew() {
		this.setTestStartTime();
		try {
			ApplicationContext ctx = BaseContext.getApplicationContext();
			FormManager manager = (FormManager)ComponentFactory.getManager("FormManager");
			User user = this.getUser(1000, 1000);
			int schemaID = 1000;
			
			FormFilter filter = ctx.getBean("FormFilter", FormFilter.class);
			int filterid = TypeUtils.nullToInt(SequenceUtils.getSequence("fm_formfilter"));
			
			filter.setId(filterid);
			filter.setSchemaID(schemaID);
			filter.setName("过滤器");
			filter.setType(FormFilter.TYPE_DEFAULT);
			filter.setCompanyID(user.getCompanyID());
			
			List<FormFilterItem> items = new ArrayList<FormFilterItem>();
			FormFilterItem item1 = ctx.getBean("FormFilterItem",FormFilterItem.class);
			int itemid1 = TypeUtils.nullToInt(SequenceUtils.getSequence("fm_formfilter"));
			item1.setId(itemid1);
			item1.setFilterID(filterid);
			item1.setFieldKey("str01");
			item1.setNew(true);
			item1.setSchemaID(schemaID);
			item1.setCompanyID(user.getCompanyID());
			items.add(item1);
			filter.setFormFilterItems(items);

			manager.newFormFilter(user, filter);
			
			FormFilter newFilter = manager.getFormFilters(user, filterid);
			if(newFilter != null){
				System.out.println("新建过滤器成功！！");
			}
			
			assertNotNull("新建失败！", newFilter);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		this.setTestEndTime();
	}

}
