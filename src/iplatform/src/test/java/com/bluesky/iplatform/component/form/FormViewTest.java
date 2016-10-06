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
import com.bluesky.iplatform.component.from.model.FormView;
import com.bluesky.iplatform.component.from.model.FormViewItem;
import com.bluesky.iplatform.component.from.service.FormManager;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class FormViewTest extends BaseUnitlsTest {

	@Test
	public void testNew() {
		this.setTestStartTime();
		try {
			ApplicationContext ctx = BaseContext.getApplicationContext();
			FormManager manager = (FormManager)ComponentFactory.getManager("FormManager");
			User user = this.getUser(1000, 1000);
			int schemaID = 1000;
			
			FormView view = ctx.getBean("FormView", FormView.class);
			int viewid = TypeUtils.nullToInt(SequenceUtils.getSequence("fm_formview"));
			
			view.setId(viewid);
			view.setName("项目信息表单视图");
			view.setDescription("项目信息表单视图");
			view.setSchemaID(schemaID);
			view.setCompanyID(user.getCompanyID());
			view.setSchemaID(schemaID);
			
			FormViewItem item1 = ctx.getBean("FormViewItem",FormViewItem.class);
			int itemid1 = TypeUtils.nullToInt(SequenceUtils.getSequence("fm_formviewitem"));
			
			item1.setId(itemid1);
			item1.setCompanyID(user.getCompanyID());
			item1.setEditFlag(FormViewItem.CONTROL_TYPE_CALENDAR);
			item1.setFieldID("str01");
			item1.setHeight(1);
			item1.setWidth(FormViewItem.WIDTH_HALF_ROW);
			item1.setViewID(viewid);
			item1.setNew(true);
			item1.setSchemaID(schemaID);
			
			List<FormViewItem> items = new ArrayList<FormViewItem>();
			items.add(item1);
			view.setFormViewItems(items);
			
			manager.newFormView(user, view);
			
			FormView newView = manager.getFormView(user, viewid);
			List<FormViewItem> itemList = newView.getFormViewItems();
			
			assertNotNull("新建失败！", newView);
			assertNotNull("新建失败", itemList.get(0));
			
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
			int viewID = 1000;
			
			FormView formView = manager.getFormView(user, viewID);
			
			formView.setDescription("项目信息表单视图----update");
			
			List<FormViewItem> items = formView.getFormViewItems();
			for(FormViewItem item : items){
				item.setHeight(5);
				item.setDefaultValue("默认值ABC");
				item.setModified(true);
				item.setControlType(FormViewItem.CONTROL_TYPE_SELECT);
			}
			manager.updateFormView(user, formView);
			
			FormView mode = manager.getFormView(user, viewID);
			
			assertEquals("更新失败！！！", "项目信息表单视图----update", mode.getDescription());
			assertEquals("更新失败！！！","默认值ABC", mode.getFormViewItems().get(0).getDefaultValue());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		this.setTestEndTime();
	}
	
	@Test
	public void testDelete(){
		this.setTestStartTime();
		try {
			FormManager manager = (FormManager)ComponentFactory.getManager("FormManager");
			User user = this.getUser(1000, 1000);
			int viewID = 1001;
			
			FormView view = manager.getFormView(user, viewID);
			if(view != null){
				manager.deleteFormView(user, view);
				FormView view2 = manager.getFormView(user, viewID);
				
				assertNull("删除成功！！！",view2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		this.setTestEndTime();
	}

}
