package com.bluesky.iplatform.component.profile.user;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.bluesky.iplatform.common.BaseUnitlsTest;
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
			
			System.out.println("== 测试成功！== ");
		}catch(Exception ex){
			ex.printStackTrace();
			fail("Not yet implemented");
		}
		
	}

}
