package com.bluesky.iplatform.component.function;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.bluesky.iplatform.common.BaseUnitlsTest;
import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.function.model.Function;
import com.bluesky.iplatform.component.function.model.FunctionRelation;
import com.bluesky.iplatform.component.function.service.FunctionManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class FunctionTest extends BaseUnitlsTest {

	@Test
	public void testNewFunction() {
		try {
			FunctionManager manager = (FunctionManager)ComponentFactory.getManager("FunctionManager");	
			ApplicationContext ctx = BaseContext.getApplicationContext();
			Function mode = ctx.getBean("Function", Function.class);
			
			int id =  TypeUtils.nullToInt(SequenceUtils.getSequence("st_function"));
			
			mode.setId(id);
			mode.setName("SYS_NEW_PROJECT");
			mode.setDescription("设置该权限后，该角色可以在系统中新建项目。");
			mode.setParentID(100);
			mode.setFunctionname("新建项目");
			mode.setSystemID(100);
			mode.setCompanyID(1000);
			mode.setSeqno(100);
			mode.setApplicationid(100);
			mode.setClienturl("http://localhost:8000/iplatform/xxx.html");
			mode.setFolderurl("http://localhost:8000/iplatform/xxx.html");
			
			manager.newFunction(systemAdmin, mode);
			
			Function newMode = manager.getFunction(systemAdmin, id);
			if(newMode != null){
				System.out.println("===========新增权限信息=================");
				System.out.println("Name : "+ newMode.getName());
				System.out.println("ID : "+ newMode.getId());
				System.out.println("Description : "+ newMode.getDescription());
				System.out.println("FunctionName : "+ newMode.getFunctionname());
				System.out.println("ApplicationID : "+ newMode.getApplicationid());
				System.out.println("ClientUrl : "+ newMode.getClienturl());
				System.out.println("FolderUrl : " + newMode.getFolderurl());
				
				System.out.println("===========End 角色信息=================");
				System.out.println("新增权限成功 ！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		
	}
	
	@Test
	public void testDeleteFunction(){
		try {
			FunctionManager manager = (FunctionManager)ComponentFactory.getManager("FunctionManager");	
			
			int id = 1002;
			Function mode = manager.getFunction(systemAdmin, id);
			if(mode != null){
				manager.deleteFunction(systemAdmin, mode);
				Function newMode = manager.getFunction(systemAdmin, id);
				if(newMode == null){
					System.out.println("删除权限成功 ！");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testSelectFunctionByName(){
		try {
			FunctionManager manager = (FunctionManager)ComponentFactory.getManager("FunctionManager");	
			
			String name = "SYS_NEW_PRODUCT";
			Function mode = manager.getFunction(systemAdmin, name);
			if(mode != null){
				System.out.println("查找权限对象成功 ！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testIsExistFuncion(){
		try {
			FunctionManager manager = (FunctionManager)ComponentFactory.getManager("FunctionManager");	
			
			String name = "SYS_NEW_PROJECT";
			boolean result = manager.isExistFuncion(systemAdmin, name);
			if(result)
				System.out.println("该权限对象存在 ！");
			else {
				System.out.println("该权限对象不存在 ！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testSaveFunctionRelations(){
		try {
			FunctionManager manager = (FunctionManager)ComponentFactory.getManager("FunctionManager");	
			ApplicationContext ctx = BaseContext.getApplicationContext();
			
			FunctionRelation mode_1 = ctx.getBean("FunctionRelation",FunctionRelation.class);
			int id_1 =  TypeUtils.nullToInt(SequenceUtils.getSequence("st_functionRelation"));
			mode_1.setId(id_1);
			mode_1.setRoleID(1000);
			mode_1.setFunctionID(1000);
			mode_1.setType(FunctionRelation.TYPE_FUNCTION_TO_ROLE);
			mode_1.setCompanyID(1000);
			mode_1.setNew(true);
			
			FunctionRelation mode_2 = ctx.getBean("FunctionRelation",FunctionRelation.class);
			int id_2 =  TypeUtils.nullToInt(SequenceUtils.getSequence("st_functionRelation"));
			mode_2.setId(id_2);
			mode_2.setRoleID(1000);
			mode_2.setFunctionID(1001);
			mode_2.setType(FunctionRelation.TYPE_FUNCTION_TO_ROLE);
			mode_2.setCompanyID(1000);
			mode_2.setNew(true);
			
			List<FunctionRelation> modes = new ArrayList<FunctionRelation>();
			modes.add(mode_1);
			modes.add(mode_2);
			
			manager.saveFunctionRelations(systemAdmin, modes);
			
			int roleID = 1000;
			Map<String, Function> functionMap  = manager.getFunctionsByRoleID(systemAdmin, roleID);
			if(functionMap != null && functionMap.size()>0){
				Set<String> keysSet = functionMap.keySet();
				for(String key : keysSet){
					Function function = functionMap.get(key);
					System.out.println("===========权限=============");
					System.out.println("Name : "+ function.getName());
					System.out.println("ID : "+ function.getId());
					System.out.println("Description : "+ function.getDescription());
					System.out.println("FunctionName : "+ function.getFunctionname());
					System.out.println("ApplicationID : "+ function.getApplicationid());
					System.out.println("ClientUrl : "+ function.getClienturl());
					System.out.println("FolderUrl : " + function.getFolderurl());
					System.out.println("=========== END 权限=============");
					System.out.println("");
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testSelectFunctionRelations(){
		try {
			FunctionManager manager = (FunctionManager)ComponentFactory.getManager("FunctionManager");	
			int roleID = 1000;
			Map<String, Function> functionMap  = manager.getFunctionsByRoleID(systemAdmin, roleID);
			if(functionMap != null && functionMap.size()>0){
				Set<String> keysSet = functionMap.keySet();
				for(String key : keysSet){
					Function function = functionMap.get(key);
					System.out.println("===========权限=============");
					System.out.println("Name : "+ function.getName());
					System.out.println("ID : "+ function.getId());
					System.out.println("Description : "+ function.getDescription());
					System.out.println("FunctionName : "+ function.getFunctionname());
					System.out.println("ApplicationID : "+ function.getApplicationid());
					System.out.println("ClientUrl : "+ function.getClienturl());
					System.out.println("FolderUrl : " + function.getFolderurl());
					System.out.println("=========== END 权限=============");
					System.out.println("");
				}
				System.out.println("查询成功！！");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}
