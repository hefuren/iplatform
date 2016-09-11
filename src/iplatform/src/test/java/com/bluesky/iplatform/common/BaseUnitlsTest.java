package com.bluesky.iplatform.common;

import org.springframework.context.ApplicationContext;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;

@SpringApplicationContext({"/spring/applicationContext.xml"})
public class BaseUnitlsTest extends UnitilsJUnit4 {
	
	 //加载Spring上下文  
    @SpringApplicationContext  
    public ApplicationContext ctx; 
    
//    @Test
//	public void test() {
//
//    }

}
