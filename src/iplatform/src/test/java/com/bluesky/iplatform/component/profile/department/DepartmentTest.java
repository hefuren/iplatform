package com.bluesky.iplatform.component.profile.department;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.bluesky.iplatform.common.BaseUnitlsTest;
import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.commons.utils.CalendarUtils;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.profile.model.Department;
import com.bluesky.iplatform.component.profile.service.StructureManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class DepartmentTest extends BaseUnitlsTest {

	@Test
	public void testNewDepartment(){
		try {
			StructureManager manager = (StructureManager)ComponentFactory.getManager("StructureManager");	
			ApplicationContext ctx = BaseContext.getApplicationContext();
			Department mode = ctx.getBean("Department",Department.class);
			int departmentID =  TypeUtils.nullToInt(SequenceUtils.getSequence("st_department"));
			
			mode.setId(departmentID);
			mode.setName("质量与运营部");
			mode.setParentID(100);
			mode.setLevel(2);
			mode.setDescription("负责公司DETS流程、质量、收入、成本和运营等管理。");
			mode.setSeqno(1);
			mode.setCompanyID(1000);
			mode.setCreateBy(systemAdmin.getId());
			mode.setCreateTime(CalendarUtils.getCurrentDate());
			mode.setLastUpdateBy(systemAdmin.getId());
			mode.setLastUpdateTime(CalendarUtils.getCurrentDate());
			manager.newDepartment(systemAdmin, mode);
			
			Department newMode = manager.getDepartment(systemAdmin, departmentID);
			Assert.assertNotNull("新增部门失败", newMode);
			if(newMode != null){
				System.out.println("===========新增部门信息=================");
				System.out.println("Name : "+ newMode.getName());
				System.out.println("ID : "+ newMode.getId());
				System.out.println("Description : "+ newMode.getDescription());
				System.out.println("createTime : "+ TypeUtils.date2String(newMode.getCreateTime(), TypeUtils.DEFAULT_TIMESTAMP_FORMAT));
				System.out.println("lastUpdateTime : "+ TypeUtils.date2String(newMode.getLastUpdateTime(), TypeUtils.DEFAULT_TIMESTAMP_FORMAT));
				
				System.out.println("===========End 部门信息=================");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testDeleteDepartment(){
		try {
			StructureManager manager = (StructureManager)ComponentFactory.getManager("StructureManager");	
			int departmentID = 1001;
			Department mode = manager.getDepartment(systemAdmin, departmentID);
			
			manager.deleteDepartment(systemAdmin, mode);
			
			Department newMode = manager.getDepartment(systemAdmin, departmentID);
			if(newMode == null){
				System.out.println("删除部门成功！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}
