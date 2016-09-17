package com.bluesky.iplatform.component.profile.role;

import static org.junit.Assert.*;

import java.util.List;

import com.bluesky.iplatform.common.BaseUnitlsTest;
import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.commons.utils.CalendarUtils;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.profile.model.Role;
import com.bluesky.iplatform.component.profile.service.RoleManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class RoleTest extends BaseUnitlsTest{

	@Test
	public void testNewRole() {
		try {
			ApplicationContext ctx = BaseContext.getApplicationContext();
			Role role = ctx.getBean("Role",Role.class);
			
			int id =  TypeUtils.nullToInt(SequenceUtils.getSequence("st_role"));
			String name = "产品经理";
			String description = "负责公司产品规划、需求分析等工作";
			int seqno = 1;
			int companyID = 1000;
			int type = Role.TYPE_CUSTOMERIZATION_ROLE;
			int status = Role.STATUS_ACTIVE;
			
			role.setId(id);
			role.setName(name);
			role.setDescription(description);
			role.setSeqno(seqno);
			role.setStatus(status);
			role.setType(type);
			role.setCompanyID(companyID);
			role.setCreateBy(systemAdmin.getId());
			role.setCreateTime(CalendarUtils.getCurrentDate());
			role.setLastUpdateBy(systemAdmin.getId());
			role.setLastUpdateTime(CalendarUtils.getCurrentDate());
			
			RoleManager manager = (RoleManager)ComponentFactory.getManager("RoleManager");
			manager.newRole(systemAdmin, role);
			
			Role newMode = manager.getRole(systemAdmin, id);
			if(newMode != null){
				System.out.println("===========新增角色信息=================");
				System.out.println("Name : "+ newMode.getName());
				System.out.println("ID : "+ newMode.getId());
				System.out.println("Description : "+ newMode.getDescription());
				System.out.println("createTime : "+ TypeUtils.date2String(newMode.getCreateTime(), TypeUtils.DEFAULT_TIMESTAMP_FORMAT));
				System.out.println("lastUpdateTime : "+ TypeUtils.date2String(newMode.getLastUpdateTime(), TypeUtils.DEFAULT_TIMESTAMP_FORMAT));
				
				System.out.println("===========End 角色信息=================");
				System.out.println("新增角色成功 ！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");

		}
	}

	
	@Test
	public void testSelectRoles(){
		try {
			RoleManager manager = (RoleManager)ComponentFactory.getManager("RoleManager");
			int[] ids = new int[]{1000,1001};
			List<Role> modes = manager.getRoles(systemAdmin, ids);
			
			if(modes != null && modes.size() >0){
				for(Role mode : modes){
					System.out.println("===========角色信息=================");
					System.out.println("Name : "+ mode.getName());
					System.out.println("ID : "+ mode.getId());
					System.out.println("Description : "+ mode.getDescription());
					System.out.println("createTime : "+ TypeUtils.date2String(mode.getCreateTime(), TypeUtils.DEFAULT_TIMESTAMP_FORMAT));
					System.out.println("lastUpdateTime : "+ TypeUtils.date2String(mode.getLastUpdateTime(), TypeUtils.DEFAULT_TIMESTAMP_FORMAT));
					System.out.println("===========End 角色信息=================");
					System.out.println("");
				}
				
				System.out.println("查询角色成功 ！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
}
