package com.bluesky.iplatform.component.form;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.bluesky.iplatform.common.BaseUnitlsTest;
import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.commons.utils.CalendarUtils;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.from.service.FormManager;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class FormSchemaTest extends BaseUnitlsTest {
	
	@Test
	public void testSelect(){
		try {
			User user = this.getUser(1000, 1000);
			FormManager manager = (FormManager)ComponentFactory.getManager("FormManager");
			int id = 1000;
			FormSchema mode = manager.getFormSchema(user, id);
			if(mode != null){
				System.out.println("查询成功 ！！！");
			}
			assertNotNull("查询失败", mode);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testNewForm() {
		try {
			ApplicationContext ctx = BaseContext.getApplicationContext();
			FormManager manager = (FormManager)ComponentFactory.getManager("FormManager");
			User user = this.getUser(1000, 1000);
			
			FormSchema mode = ctx.getBean("FormSchema",FormSchema.class);
			int id = TypeUtils.nullToInt(SequenceUtils.getSequence("fm_formschema"));
			mode.setName("风险管理表单");
			mode.setStatus(FormSchema.STATUS_ACTIVE);
			mode.setDescription("用于项目中风险管理");
			mode.setType(0);
			mode.setCompanyID(user.getCompanyID());
			mode.setCreateBy(user.getId());
			mode.setCreateTime(CalendarUtils.getCurrentDate());
			mode.setLastUpdateBy(user.getId());
			mode.setLastUpdateTime(CalendarUtils.getCurrentDate());
			mode.setId(id);
			manager.newFormSchema(user, mode);
			
			FormSchema newform = manager.getFormSchema(user, id);
			if(newform != null){
				System.out.println("新增成功 ！！！");
			}
			assertNotNull("查询失败", newform);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testUpdate(){
		try {
			User user = this.getUser(1000, 1000);
			FormManager manager = (FormManager)ComponentFactory.getManager("FormManager");
			int id = 1000;
			FormSchema mode = manager.getFormSchema(user, id);
			mode.setName("风险管理表单");
			mode.setStatus(FormSchema.STATUS_INACTIVE);
			mode.setDescription("用于项目中风险管理-----修改");
			mode.setType(1);
			mode.setCompanyID(user.getCompanyID());
			mode.setCreateBy(user.getId());
			mode.setCreateTime(CalendarUtils.getCurrentDate());
			mode.setLastUpdateBy(user.getId());
			mode.setLastUpdateTime(CalendarUtils.getCurrentDate());
			mode.setId(id);
			manager.updateFormSchema(user, mode);
			
			FormSchema newform = manager.getFormSchema(user, id);
			
			assertEquals("更新失败", "用于项目中风险管理-----修改", newform.getDescription());
			assertEquals("更新失败", TypeUtils.nullToLong(FormSchema.STATUS_INACTIVE), TypeUtils.nullToLong(newform.getStatus()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testDelete(){
		try {
			User user = this.getUser(1000, 1000);
			FormManager manager = (FormManager)ComponentFactory.getManager("FormManager");
			int id =1003;
			int[] ids = new int[]{id};
			manager.deleteFormSchemas(user, ids);
			
			FormSchema newform = manager.getFormSchema(user, id);
			if(newform != null){
				System.out.println("删除失败！！！");
			}
			
			assertNull("删除失败", newform);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}
