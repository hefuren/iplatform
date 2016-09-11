package com.bluesky.iplatform.component.profile;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bluesky.iplatform.common.BaseUnitlsTest;
import com.bluesky.iplatform.component.profile.model.Company;
import com.bluesky.iplatform.component.profile.service.ProfileManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class TestCompanyManager extends BaseUnitlsTest{

	@Test
	public void test() {
		
		try{
			ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");		
			Company company = ctx.getBean("Company", Company.class);			
			company.setId(2000);
			company.setName("深圳蓝云有限公司");
			manager.newCompany(company);
			
		}catch(Exception ex){
			fail("Not yet implemented");
			ex.printStackTrace();
		}
		
		
	}

}
