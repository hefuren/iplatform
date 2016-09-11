package com.bluesky.iplatform.component.test.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluesky.iplatform.commons.hierarchy.Hierarchy;
import com.bluesky.iplatform.commons.db.PageInfo;
import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.commons.utils.BaseController;
import com.bluesky.iplatform.commons.utils.CalendarUtils;
import com.bluesky.iplatform.commons.utils.CipherUtils;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.commons.web.SessionManager;
import com.bluesky.iplatform.component.profile.dao.CompanyDAO;
import com.bluesky.iplatform.component.profile.mapper.CompanyMapper;
import com.bluesky.iplatform.component.profile.model.Company;
import com.bluesky.iplatform.component.profile.model.Department;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.profile.service.ProfileManager;
import com.bluesky.iplatform.component.profile.service.StructureManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

@Controller
@RequestMapping("/TestAction.html")
public class TestController extends BaseController{
	
	@Override
	public String setForwardPath(){
		this.forwardPath = "system/profile/";
		return forwardPath;
	}

	@Override
	public String execute() {
		setForwardPath();
		String operation = TypeUtils.nullToString(request.getParameter("operation"));
		
		try{
			if("test".equals(operation)){
				return test(webMgr, request, response);
			} else{
				return test(webMgr, request, response);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
		
	}
	
	private String test(SessionManager webMgr,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forwardKey = "test";
		User user = webMgr.getCurrentUser();
		ApplicationContext ctx = BaseContext.getApplicationContext();
 		ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");
		
		Company company = ctx.getBean("Company", Company.class);
		
		company.setId(2000);
		company.setName("深圳蓝云有限公司");
		//manager.newCompany(company);
		
		
		SqlSessionTemplate sqlSession = (SqlSessionTemplate)ctx.getBean("sqlSessionTemplate");
//		CompanyMapper dao  = sqlSession.getMapper(CompanyMapper.class);
//		dao.insert(company);
		CompanyDAO dao = ctx.getBean("CompanyDAOImpl", CompanyDAO.class);
		dao.newMode(user, company);
		
		return forward(forwardKey);
	}
	

}
