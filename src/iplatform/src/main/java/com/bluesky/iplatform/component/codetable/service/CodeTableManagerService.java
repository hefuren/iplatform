package com.bluesky.iplatform.component.codetable.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.bluesky.iplatform.commons.db.PageInfo;
import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.component.codetable.model.CodeTable;
import com.bluesky.iplatform.component.codetable.model.CommonCode;
import com.bluesky.iplatform.component.profile.model.User;

@Service(value="CodeTableManagerService")
public class CodeTableManagerService implements CodeTableManager {
	
//	private CodeTableDAO codeTableDAO;
	
	@Override
	public CodeTable getCodeTable(User user, int codeTableID) {
		CodeTable codeTable = null;
		try{
//			codeTable = (CodeTable) codeTableDAO.getMode(user, codeTableID);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return codeTable;
	}

	@Override
	public CodeTable getCodeTalbe(User user, String codeTalbeName) {
		CodeTable codeTable = null;
		try{
//			codeTable = (CodeTable) codeTableDAO.getCodeTalbe(user, codeTalbeName);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return codeTable;
	}

	@Override
	public void newCodeTable(User user, CodeTable codeTable) {
		try{
//			codeTableDAO.newMode(user, codeTable);
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

	@Override
	public void updateCodeTable(User user, CodeTable codeTable) {
		try{
//			codeTableDAO.updateMode(user, codeTable);
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

	@Override
	public void deleteCodeTable(User user, CodeTable codeTable) {
		try{
//			codeTableDAO.deleteMode(user, codeTable);
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

	@Override
	public void batchDeleteCodeTables(User user, int[] ids) {
		try{
//			codeTableDAO.batchDeleteModes(user, ids);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public List<CommonCode> getCommonCodes(User user, String tableName) {
		List<CommonCode> codes = null;
		try{
//			codes = codeTableDAO.getCommonCodes(user, tableName);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return codes;
	}

	@Override
	public PageInfo getCodeTables(User user, PageInfo pageInfo) {
		try{
//			pageInfo = codeTableDAO.getByPageInfo(user, pageInfo);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return pageInfo;
	}

	
	/** 通过Set 方法注入相应的DAO */
//	public void setCodeTableDAO(CodeTableDAO codeTableDAO) {
//		this.codeTableDAO = codeTableDAO;
//	}

	

}
