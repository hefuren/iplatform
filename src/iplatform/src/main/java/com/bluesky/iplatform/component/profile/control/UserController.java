package com.bluesky.iplatform.component.profile.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
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
import com.bluesky.iplatform.component.profile.model.Department;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.profile.service.ProfileManager;
import com.bluesky.iplatform.component.profile.service.StructureManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

@Controller
@RequestMapping("/UserAction.html")
public class UserController extends BaseController{
	
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
			if("userList".equals(operation)){
				return userList(webMgr, request, response);
			} else if("newUser".equals(operation)){
				return newUser(webMgr, request, response);
			} else if("saveUser".equals(operation)){
				return saveUser(webMgr, request, response);
			} else if("openUser".equals(operation)){
				return openUser(webMgr, request, response);
			} else if("deleteUser".equals(operation)){
				return deleteUser(webMgr, request, response);
			} else{
				return userList(webMgr, request, response);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
		
	}
	
	private String userList(SessionManager webMgr,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forwardKey = "userList";
		User user = webMgr.getCurrentUser();
		ApplicationContext ctx = BaseContext.getApplicationContext();
		ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");
		StructureManager structureManager = (StructureManager)ComponentFactory.getManager("StructureManager");
		
		int currentPage = TypeUtils.nullToInt(request.getParameter("currentPage"));
		currentPage = (currentPage == 0) ? 1 : currentPage;
		String curDeptID = TypeUtils.nullToString(request.getParameter("curDeptID"));
		if("".equals(curDeptID)){
			curDeptID = String.valueOf(Department.ROOTNODE);
		}
		
		Hierarchy obsHierarchy = structureManager.getDepartmentTree(user);
		PageInfo pageInfo = ctx.getBean("PageInfo", PageInfo.class);
		
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("companyID", new Integer(user.getCompanyID()));
		pageInfo.setConditions(conditions);
		pageInfo.setCurrentPage(currentPage);
		pageInfo.setPaged(true);
		
		pageInfo = manager.getUsers(user, pageInfo);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("user", user);
		request.setAttribute("curDeptID", curDeptID);
		request.setAttribute("obsHierarchy", obsHierarchy);
		
		return forward(forwardKey);
	}
	
	private String newUser(SessionManager webMgr,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forwardKey = "editUser";
		User user = webMgr.getCurrentUser();
		String isEdit = "edit";
		String saveAction = "new";
		StructureManager structureManager = (StructureManager)ComponentFactory.getManager("StructureManager");
		Hierarchy obsHierarchy = structureManager.getDepartmentTree(user);
		
		String curDeptID = TypeUtils.nullToString(request.getParameter("curDeptID"));
		if("".equals(curDeptID)){
			curDeptID = String.valueOf(Department.ROOTNODE);
		}
		
		ApplicationContext ctx = BaseContext.getApplicationContext();
		User mode = ctx.getBean("User", User.class);
		mode.setId(0);
		
		request.setAttribute("newUser", mode);
		request.setAttribute("isEdit", isEdit);
		request.setAttribute("saveAction", saveAction);
		request.setAttribute("curDeptID", curDeptID);
		request.setAttribute("obsHierarchy", obsHierarchy);
		
		return forward(forwardKey);
	}
	
	private String saveUser(SessionManager webMgr,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forwardKey = "editUser";
		User user = webMgr.getCurrentUser();
		String isEdit = "view";
		String saveAction = TypeUtils.nullToString(request.getParameter("saveAction"));
		
		StructureManager structureManager = (StructureManager)ComponentFactory.getManager("StructureManager");
		Hierarchy obsHierarchy = structureManager.getDepartmentTree(user);
		
		String displayName = TypeUtils.nullToString(request.getParameter("displayName"));
		String email = TypeUtils.nullToString(request.getParameter("email"));
		String lastName = TypeUtils.nullToString(request.getParameter("lastName"));
		String firstName = TypeUtils.nullToString(request.getParameter("firstName"));
		String tel = TypeUtils.nullToString(request.getParameter("tel"));
		String mobile = TypeUtils.nullToString(request.getParameter("mobile"));
		int curDeptID = TypeUtils.nullToInt(request.getParameter("curDeptID"));
		String jobnumber = TypeUtils.nullToString(request.getParameter("jobnumber"));
		
		String username = TypeUtils.nullToString(request.getParameter("username"));
		int status = TypeUtils.nullToInt(request.getParameter("status"));
		String password = TypeUtils.nullToString(request.getParameter("password"));
		
		ApplicationContext ctx = BaseContext.getApplicationContext();
		ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");
		User mode = null;
		if("new".equals(saveAction)){
			 mode = ctx.getBean("User", User.class);
		}else {
			int userID = TypeUtils.nullToInt(request.getParameter("userID"));
			mode = manager.getUser(user, userID);
		}
		
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
		mode.setCompanyID(user.getCompanyID());
		
		if("new".equals(saveAction)){
			Long seqID = SequenceUtils.getSequence("st_user");
			mode.setId(TypeUtils.nullToInt(seqID));
			password = CipherUtils.toMD5(password);
			mode.setPassword(password);
			mode.setCreateBy(user.getId());
			mode.setCreateTime(CalendarUtils.getCurrentDate());
			mode.setLastUpdateBy(user.getId());
			mode.setLastUpdateTime(CalendarUtils.getCurrentDate());
			manager.newUser(mode);
		} else {
			if(!password.equals(mode.getPassword())){
				//密码修改过，更新密码；避免重复密码加密的问题
				password = CipherUtils.toMD5(password);
				mode.setPassword(password);
			}
			mode.setLastUpdateBy(user.getId());
			mode.setLastUpdateTime(CalendarUtils.getCurrentDate());
			manager.updateUser(mode);
		}
		
		request.setAttribute("newUser", mode);
		request.setAttribute("isEdit", isEdit);
		request.setAttribute("curDeptID", curDeptID);
		request.setAttribute("obsHierarchy", obsHierarchy);
		
		return forward(forwardKey);
	}
	
	private String openUser(SessionManager webMgr,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String forwardKey = "editUser";
		User user = webMgr.getCurrentUser();
		String isEdit = TypeUtils.nullToString(request.getParameter("isEdit"));
		if("".equals(isEdit)){
			isEdit = "view";
		}
		String saveAction = "update";
		StructureManager structureManager = (StructureManager)ComponentFactory.getManager("StructureManager");
		Hierarchy obsHierarchy = structureManager.getDepartmentTree(user);
		
		String curDeptID = TypeUtils.nullToString(request.getParameter("curDeptID"));
		if("".equals(curDeptID)){
			curDeptID = String.valueOf(Department.ROOTNODE);
		}
		
		int userID = TypeUtils.nullToInt(request.getParameter("userID"));
		
		ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");
		User mode = manager.getUser(user, userID);
		
		request.setAttribute("newUser", mode);
		request.setAttribute("isEdit", isEdit);
		request.setAttribute("saveAction", saveAction);
		request.setAttribute("curDeptID", curDeptID);
		request.setAttribute("obsHierarchy", obsHierarchy);
		
		return forward(forwardKey);
	}
	
	private String deleteUser(SessionManager webMgr,HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = webMgr.getCurrentUser();
		
		String[] ids = request.getParameterValues("chk");
		ProfileManager manager = (ProfileManager)ComponentFactory.getManager("ProfileManager");
		
		int[] userIDs = new int[ids.length];
		int index = 0;
		for(String tempID : ids){
			userIDs[index++] = TypeUtils.nullToInt(tempID);
		}
		manager.batchDeleteUsers(user, userIDs);
		
		return userList(webMgr, request, response);
	}

}
