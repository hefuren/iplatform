package com.bluesky.iplatform.component.profile;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.binding.mapping.results.Success;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;

import com.bluesky.iplatform.common.BaseUnitlsTest;
import com.bluesky.iplatform.component.profile.model.Company;

public class TestSpring extends BaseUnitlsTest{
	
//	 @SpringApplicationContext({"applicationContext.xml"})  
//	 private ApplicationContext applicationContext;  


	@Test
	public void test() {
		assertNotNull(ctx);  
		Company company = ctx.getBean("Company",Company.class);
		assertNotNull(company.toString());  
	}
	

}
