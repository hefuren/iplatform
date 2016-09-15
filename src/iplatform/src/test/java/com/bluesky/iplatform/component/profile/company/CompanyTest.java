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
			company.setName("深圳市初心软件有限公司");
			company.setDescription("深圳市初心软件有限公司");
			company.setStatus(Company.STATUS_ACTIVATED);
			company.setType(0);
			company.setContactname("ElwinHe");
			company.setContactemail("hefuren@huawei.com");
			company.setContactphone("13424200701");
			company.setContactfax("0755-235648");
			company.setZip("258");
			company.setUrl("http://www.easytrack.com.cn");
			company.setEmployess(500);
			company.setAdress("深圳市坂田基地");
			
			manager.newCompany(company);
			
			Company mode = manager.getCompany(id);
			assertNotNull("新增公司失败", mode);
		}catch(Exception ex){
			fail("Not yet implemented");
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateCompany(){
		try{
			ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");	
			int companyID = 1001;
			Company company = manager.getCompany(companyID);
			
			String name = "深圳市初心软件有限公司分公司";
			String dec = "深圳市初心软件有限公司分公司";
			int status = Company.STATUS_UNACTIVATED;
			int type = 1;
			String contactName = "hefuren";
			String contactEmail = "hefuren1984@163.com";
			String contactPhone = "13414479202";
			String fax = "123456";
			String zip = "586000";
			String url = "http://www.chuxin.com.cn";
			int employess = 100;
			String adress = "深圳市南山区";
			
			company.setName(name);
			company.setDescription(dec);
			company.setStatus(status);
			company.setType(type);
			company.setContactname(contactName);
			company.setContactemail(contactEmail);
			company.setContactphone(contactPhone);
			company.setContactfax(fax);
			company.setZip(zip);
			company.setUrl(url);
			company.setEmployess(employess);
			company.setAdress(adress);
			manager.updateCompany(company);
			
			Company mode = manager.getCompany(companyID);
			
			assertEquals("Name 属性更新失败", name, mode.getName());
			assertEquals("description 属性更新失败", dec, mode.getDescription());
			assertEquals("status 属性更新失败", new Integer(status), mode.getStatus());
			assertEquals("type 属性更新失败", new Integer(type), mode.getType());
			
			
		}catch(Exception ex){
			fail("Not yet implemented");
			ex.printStackTrace();
		}
		
		
	}

}
