package com.bluesky.iplatform.component.profile.company;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bluesky.iplatform.common.BaseUnitlsTest;
import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.profile.model.Company;
import com.bluesky.iplatform.component.profile.service.ProfileManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class CompanyTest extends BaseUnitlsTest {

	@Test
	public void testNewCompany() {
		try{
			ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");		
			Company company = ctx.getBean("Company", Company.class);	
			int id = TypeUtils.nullToInt(SequenceUtils.getSequence("st_company"));
			company.setId(id);
			company.setName("深圳蓝云有限公司");
			manager.newCompany(company);
			
			Company mode = manager.getCompany(id);
			assertNotNull("新增公司成功", mode);
		}catch(Exception ex){
			fail("Not yet implemented");
			ex.printStackTrace();
		}
	}

}
