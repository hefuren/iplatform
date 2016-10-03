package com.bluesky.iplatform.component.profile.user;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.bluesky.iplatform.common.BaseUnitlsTest;
import com.bluesky.iplatform.commons.db.PageInfo;
import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.commons.utils.CalendarUtils;
import com.bluesky.iplatform.commons.utils.CipherUtils;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.profile.service.ProfileManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class UserTest extends BaseUnitlsTest {

	@Test
	public void testNewUser() {
		
		try{
			ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");	
			ApplicationContext ctx = BaseContext.getApplicationContext();
			User mode = ctx.getBean("User", User.class);
			
			String displayName = "张飞";
			String email = "zhangfei@chuxin.com.cn";
			String lastName = "飞";
			String firstName = "张";
			String tel = "123456";
			String mobile = "13508551314";
			int curDeptID = 1000;
			String jobnumber = "00314674";
			int status = User.STATUS_ACTIVE;
			String username = "zhangfei";
			int companyID = 1000;
			String password = "123456";
			int createby = 1000;
			int lastupdateby = 1000;
			
			int userID =  TypeUtils.nullToInt(SequenceUtils.getSequence("st_user"));
			mode.setId(TypeUtils.nullToInt(userID));
			mode.setDisplayname(displayName);
			mode.setEmail(email);
			mode.setLastname(lastName);
			mode.setFirstname(firstName);
			mode.setTel(tel);
			mode.setMobile(mobile);
			mode.setDepartmentid(curDeptID);
			mode.setJobnumber(jobnumber);
			mode.setName(username);
			mode.setStatus(status);
			mode.setCompanyID(companyID);
			password = CipherUtils.toMD5(password);
			mode.setPassword(password);
			mode.setCreateBy(createby);
			mode.setCreateTime(CalendarUtils.getCurrentDate());
			mode.setLastUpdateBy(lastupdateby);
			mode.setLastUpdateTime(CalendarUtils.getCurrentDate());
			manager.newUser(mode);
			
			User user = manager.getUser(systemAdmin, userID);
			
			assertNotNull("新增用户失败", user);
			
			System.out.println("== 测试新增用户成功！== ");
		}catch(Exception ex){
			ex.printStackTrace();
			fail("Not yet implemented");
		}
		
	}
	
	@Test
	public void testUpdateUser(){
		try {
			ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");	
			
			int userID = 1002;
			User mode = manager.getUser(systemAdmin, userID);
			
			String displayName = "张飞1";
			String email = "zhangfei@chuxin.com.cn1";
			String lastName = "飞1";
			String firstName = "张1";
			String tel = "1234561";
			String mobile = "13508551315";
			int curDeptID = 1001;
			String jobnumber = "00314676";
			int status = User.STATUS_ACTIVE;
			String username = "zhangfei1";
			int companyID = 1000;
			String password = "1234561";
			int createby = 1000;
			int lastupdateby = 1000;
			
			mode.setId(TypeUtils.nullToInt(userID));
			mode.setDisplayname(displayName);
			mode.setEmail(email);
			mode.setLastname(lastName);
			mode.setFirstname(firstName);
			mode.setTel(tel);
			mode.setMobile(mobile);
			mode.setDepartmentid(curDeptID);
			mode.setJobnumber(jobnumber);
			mode.setName(username);
			mode.setStatus(status);
			mode.setCompanyID(companyID);
			password = CipherUtils.toMD5(password);
			mode.setPassword(password);
			mode.setCreateBy(createby);
			mode.setCreateTime(CalendarUtils.getCurrentDate());
			mode.setLastUpdateBy(lastupdateby);
			mode.setLastUpdateTime(CalendarUtils.getCurrentDate());
			manager.updateUser(mode);
			
			User user = manager.getUser(systemAdmin, userID);
			assertEquals("Name 属性更新失败", username, user.getName());
			assertEquals("displayName 属性更新失败", displayName, user.getDisplayname());
			assertEquals("status 属性更新失败", new Integer(status), user.getStatus());
			System.out.println("== 测试更新成功！== ");
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	/**
	 * 测试批量新增用户
	 */
	@Test
	public void testBatchNewUsers(){
		try {
			ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");	
			ApplicationContext ctx = BaseContext.getApplicationContext();

			String displayName = "关羽";
			String email = "guanyu@chuxin.com.cn";
			String lastName = "羽";
			String firstName = "关";
			String tel = "123456";
			String mobile = "13508551314";
			int curDeptID = 1000;
			String jobnumber = "00314674";
			int status = User.STATUS_ACTIVE;
			String username = "guanyu";
			int companyID = 1000;
			String password = "123456";
			int createby = 1000;
			int lastupdateby = 1000;
			
			User mode_1 = ctx.getBean("User", User.class);
			int userID =  TypeUtils.nullToInt(SequenceUtils.getSequence("st_user"));
			mode_1.setId(TypeUtils.nullToInt(userID));
			mode_1.setDisplayname(displayName);
			mode_1.setEmail(email);
			mode_1.setLastname(lastName);
			mode_1.setFirstname(firstName);
			mode_1.setTel(tel);
			mode_1.setMobile(mobile);
			mode_1.setDepartmentid(curDeptID);
			mode_1.setJobnumber(jobnumber);
			mode_1.setName(username);
			mode_1.setStatus(status);
			mode_1.setCompanyID(companyID);
			password = CipherUtils.toMD5(password);
			mode_1.setPassword(password);
			mode_1.setCreateBy(createby);
			mode_1.setCreateTime(CalendarUtils.getCurrentDate());
			mode_1.setLastUpdateBy(lastupdateby);
			mode_1.setLastUpdateTime(CalendarUtils.getCurrentDate());
			
			User mode_2 = ctx.getBean("User", User.class);
			
			displayName = "张飞";
			email = "zhangfei@chuxin.com.cn";
			lastName = "飞";
			firstName = "张";
			tel = "123456";
			mobile = "13508551314";
			curDeptID = 1000;
			jobnumber = "00314674";
			status = User.STATUS_ACTIVE;
			username = "zhangfei";
			companyID = 1000;
			password = "123456";
			createby = 1000;
			lastupdateby = 1000;
			
			int userID_2 =  TypeUtils.nullToInt(SequenceUtils.getSequence("st_user"));
			mode_2.setId(TypeUtils.nullToInt(userID_2));
			mode_2.setDisplayname(displayName);
			mode_2.setEmail(email);
			mode_2.setLastname(lastName);
			mode_2.setFirstname(firstName);
			mode_2.setTel(tel);
			mode_2.setMobile(mobile);
			mode_2.setDepartmentid(curDeptID);
			mode_2.setJobnumber(jobnumber);
			mode_2.setName(username);
			mode_2.setStatus(status);
			mode_2.setCompanyID(companyID);
			password = CipherUtils.toMD5(password);
			mode_2.setPassword(password);
			mode_2.setCreateBy(createby);
			mode_2.setCreateTime(CalendarUtils.getCurrentDate());
			mode_2.setLastUpdateBy(lastupdateby);
			mode_2.setLastUpdateTime(CalendarUtils.getCurrentDate());
			
			List<User> modes = new ArrayList<User>();
			modes.add(mode_1); 
			modes.add(mode_2); 
			
			manager.batchNewUsers(modes);
			System.out.println("== 批量新增用户成功！== ");
		
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	/**
	 * 测试通过pageInfo查询用户
	 */
	@Test
	public void testSelectUsers(){
		try {
			ApplicationContext ctx = BaseContext.getApplicationContext();
			ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");
			
			int currentPage = 1;
			
			PageInfo pageInfo = ctx.getBean("PageInfo", PageInfo.class);
			
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("companyID", new Integer(systemAdmin.getCompanyID()));
			pageInfo.setConditions(conditions);
			pageInfo.setCurrentPage(currentPage);
			pageInfo.setPaged(true);
			
			pageInfo = manager.getUsers(systemAdmin, pageInfo);
			List<User> modes = pageInfo.getItems();
			if(modes != null && modes.size() > 0){
				System.out.println("===========输出用户列表===========");
				for(User user : modes){
					System.out.println("	id :	"+user.getId());
					System.out.println("	username :	"+user.getName());
					System.out.println("	displayName :	"+user.getDisplayname());
					System.out.println("	email :	"+user.getEmail());
					System.out.println("	tel :	"+user.getTel());
					System.out.println("");
				}
				System.out.println("===========End 用户列表===========");
				System.out.println("== 通过pageInfo查询用户成功！== ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testSelectUserByName() {
		try {
			ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");
			
			String username = "ElwinHe";
			User user = manager.getUser(username, 1000);
			assertNotNull("查询失败！", user);
			if(user != null){
				System.out.println("	id :	"+user.getId());
				System.out.println("	username :	"+user.getName());
				System.out.println("	displayName :	"+user.getDisplayname());
				System.out.println("	email :	"+user.getEmail());
				System.out.println("	tel :	"+user.getTel());
				System.out.println("");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Not yet implemented");

		}
	}

	@Test
	public void testDeleteUser() {
		try {
			ApplicationContext ctx = BaseContext.getApplicationContext();
			ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");
			
			int currentPage = 1;
			
			PageInfo pageInfo = ctx.getBean("PageInfo", PageInfo.class);
			
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("companyID", new Integer(1000));
			pageInfo.setConditions(conditions);
			pageInfo.setCurrentPage(currentPage);
			pageInfo.setPaged(true);
			
			pageInfo = manager.getUsers(systemAdmin, pageInfo);
			List<User> modes = pageInfo.getItems();
			int tempUserID = 0;
			if(modes != null && modes.size() > 0){
				for(User user : modes){
					tempUserID = user.getId();
					manager.deleteUser(user);
					break;
				}
			}
			if(tempUserID != 0){
				User tempUser = manager.getUser(systemAdmin, tempUserID);
				assertEquals("删除用户失败 ！", new Integer(User.STATUS_DELETED), tempUser.getStatus());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
}
