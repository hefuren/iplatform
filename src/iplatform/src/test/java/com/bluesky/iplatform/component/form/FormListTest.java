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
import com.bluesky.iplatform.component.from.model.FormList;
import com.bluesky.iplatform.component.from.model.FormListItem;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.from.service.FormManager;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class FormListTest extends BaseUnitlsTest {

	@Test
	public void testNew() {
		this.setTestStartTime();
		try {
			ApplicationContext ctx = BaseContext.getApplicationContext();
			FormManager manager = (FormManager)ComponentFactory.getManager("FormManager");
			User user = this.getUser(1000, 1000);
			int schemaID = 1000;
			
			FormList formList = ctx.getBean("FormList", FormList.class);
			int listid = TypeUtils.nullToInt(SequenceUtils.getSequence("fm_formlist"));
			formList.setId(listid);
			formList.setSchemaID(schemaID);
			formList.setCompanyID(user.getCompanyID());
			formList.setOrderType(FormList.ORDER_TYPE_ASC);
			
			FormListItem item1 = ctx.getBean("FormListItem", FormListItem.class);
			int id1 = TypeUtils.nullToInt(SequenceUtils.getSequence("fm_formlistitem"));
			item1.setId(id1);
			item1.setSchemaID(schemaID);
			item1.setCompanyID(user.getCompanyID());
			item1.setListID(listid);
			item1.setWidth(50);
			item1.setAlign(FormListItem.ALIGN_LEFT);
			item1.setNew(true);
			
			List<FormListItem> items = new ArrayList<FormListItem>();
			items.add(item1);
			formList.setFormListItems(items);
			manager.newFormList(user, formList);
			
			FormSchema form = manager.getFormSchema(user, schemaID);
			FormList newFormList = manager.getFormList(user, form);
			
			assertNotNull("新建失败！", newFormList);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		this.setTestEndTime();
	}
	
	@Test
	public void testUpdate(){
		this.setTestStartTime();
		try {
			FormManager manager = (FormManager)ComponentFactory.getManager("FormManager");
			User user = this.getUser(1000, 1000);
			int schemaID = 1000;
			
			FormSchema form = manager.getFormSchema(user, schemaID);
			FormList formList = manager.getFormList(user, form);
			formList.setOrderType(FormList.ORDER_TYPE_DSEC);
			
			List<FormListItem> items = formList.getFormListItems();
			for(FormListItem item : items){
				item.setModified(true);
				item.setAlign(FormListItem.ALIGN_RIGHT);
				item.setFieldID("str01");
				item.setWidth(100);
				item.setSeqno(100);
			}
			manager.updateFormList(user, formList);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		this.setTestEndTime();
	}
	

}
