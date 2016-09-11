package com.bluesky.iplatform.component.system.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.BaseController;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.profile.model.User;

@Controller
@RequestMapping("/SystemAction.html")
public class SystemAction extends BaseController{
	
	@Override
	public String setForwardPath(){
		this.forwardPath = "system/profile/";
		return forwardPath;
	}
	
	public String execute() {
		setForwardPath();
		String operation = TypeUtils.nullToString(request.getParameter("operation"));
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/com/bluesky/platform/application/applicationContext.xml");
		User user = ctx.getBean("User", User.class);
		user.setCompanyID(1000);
		user.setId(1000);
		
		
		String forward = "test";
		
		return forward;
	}
	
	private String test(User user,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String forward = "test";
		System.out.println("====================== start ==========================");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/com/bluesky/platform/application/applicationContext.xml");
		
//		FormSchema mode = ctx.getBean("FormSchema", FormSchema.class);
//		Long id = SequenceUtils.getSequence("fm_formSchema");
//		mode.setId(id.intValue());
//		mode.setCompanyID(1000);
//		mode.setName("风险流程");
//		mode.setDescription("风险管理流程");
//		
//		SchemaManager manger = ComponentFactory.getSchemaManager();
//		manger.newFormSchema(user, mode);
//		System.out.println("====================== end ==========================");
		
		return forward;
	}


}
