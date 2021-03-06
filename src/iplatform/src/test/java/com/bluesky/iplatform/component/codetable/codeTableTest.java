package com.bluesky.iplatform.component.codetable;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.bluesky.iplatform.common.BaseUnitlsTest;
import com.bluesky.iplatform.commons.db.PageInfo;
import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.commons.utils.CalendarUtils;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.codetable.dao.impl.CodeTableDAOImpl;
import com.bluesky.iplatform.component.codetable.model.CodeTable;
import com.bluesky.iplatform.component.codetable.model.CodeTableField;
import com.bluesky.iplatform.component.codetable.model.CommonCode;
import com.bluesky.iplatform.component.codetable.service.CodeTableManager;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class codeTableTest extends BaseUnitlsTest{
	
	@Test
	public void testOnlyNewCodeTable(){
		try {
			ApplicationContext ctx = BaseContext.getApplicationContext();
			
			String name = "项目优先级";
			String description = "定义项目的优先级：高中低";
			int idRule = 0;
			int tableID = TypeUtils.nullToInt(SequenceUtils.getSequence("st_codetable"));
			String tableName = "codeTable_"+tableID;
			
			
			CodeTable mode_1 = ctx.getBean("CodeTable", CodeTable.class);
			mode_1.setId(tableID);
			mode_1.setTablename(tableName);
			mode_1.setName(name);
			mode_1.setDescription(description);
			mode_1.setStatus(CodeTable.STATUS_ACTIVE);
			mode_1.setType(CodeTable.TYPE_OWN);
			mode_1.setIdRule(idRule);
			mode_1.setCompanyID(systemAdmin.getCompanyID());
			mode_1.setCreateBy(systemAdmin.getId());
			mode_1.setCreateTime(CalendarUtils.getCurrentDate());
			mode_1.setLastUpdateBy(systemAdmin.getId());
			mode_1.setLastUpdateTime(CalendarUtils.getCurrentDate());
			
			CodeTable mode_2 = ctx.getBean("CodeTable", CodeTable.class);
			mode_2.setId(tableID+1);
			mode_2.setTablename(tableName);
			mode_2.setName(name);
			mode_2.setDescription(description);
			mode_2.setStatus(CodeTable.STATUS_ACTIVE);
			mode_2.setType(CodeTable.TYPE_OWN);
			mode_2.setIdRule(idRule);
			mode_2.setCompanyID(systemAdmin.getCompanyID());
			mode_2.setCreateBy(systemAdmin.getId());
			mode_2.setCreateTime(CalendarUtils.getCurrentDate());
			mode_2.setLastUpdateBy(systemAdmin.getId());
			mode_2.setLastUpdateTime(CalendarUtils.getCurrentDate());
			
			
			List<CodeTable> modes = new ArrayList<CodeTable>();
			modes.add(mode_1);
			modes.add(mode_2);
			CodeTableDAOImpl daoImpl = ctx.getBean("CodeTableDAOImpl", CodeTableDAOImpl.class);
			
			
			daoImpl.batchNewModes(systemAdmin, modes);
			
		} catch (Exception e) {
			fail("Not yet implemented");
		}
	}

	@Test
	public void testNewCodeTable() {
		
		try {
			ApplicationContext ctx = BaseContext.getApplicationContext();
			CodeTableManager manager = (CodeTableManager)ComponentFactory.getManager("CodeTableManager");
			
			String name = "项目优先级";
			String description = "定义项目的优先级：高中低";
			int idRule = 0;
			int tableID = TypeUtils.nullToInt(SequenceUtils.getSequence("st_codetable"));
			String tableName = "codeTable_"+tableID;
			
			String[] seqNos = new String[]{"1","2","3"};
			String[] isNew = new String[]{"true","true","true"};
			String[] codeID = new String[]{"1000","10001","1002"};
			String[] codeName = new String[]{"高","中","低"};
			String[] codeDesp = new String[]{"高","中","低"};
			
			CodeTable codeTable = ctx.getBean("CodeTable", CodeTable.class);
			codeTable.setId(tableID);
			codeTable.setTablename(tableName);
			codeTable.setName(name);
			codeTable.setDescription(description);
			codeTable.setStatus(CodeTable.STATUS_ACTIVE);
			codeTable.setType(CodeTable.TYPE_OWN);
			codeTable.setIdRule(idRule);
			codeTable.setCompanyID(systemAdmin.getCompanyID());
			codeTable.setCreateBy(systemAdmin.getId());
			codeTable.setCreateTime(CalendarUtils.getCurrentDate());
			codeTable.setLastUpdateBy(systemAdmin.getId());
			codeTable.setLastUpdateTime(CalendarUtils.getCurrentDate());
			Set<CodeTableField> filedSet = initCodeTableFieldSet(codeTable);
			codeTable.setCodeTableFields(filedSet);
			
			List<CommonCode> codes = new ArrayList<CommonCode>();
			int index = 0;
			if(seqNos != null && seqNos.length > 0){
				for(String seqNo : seqNos){
					CommonCode code = ctx.getBean("CommonCode", CommonCode.class);
					if(idRule == CodeTable.ID_RULE_MANUL){
						//手动设置
						code.setId(TypeUtils.nullToInt(codeID[index]));
					}
					code.setName(codeName[index]);
					code.setDescription(codeDesp[index]);
					code.setSeqNo(TypeUtils.nullToInt(seqNo));
					code.setNew(TypeUtils.stringToBoolean(isNew[index]));
					code.setStatus(CommonCode.STATUS_DELETE);
					code.setCompanyID(1000);
					codes.add(code);
					index++;
				}
			}
			codeTable.setCodeList(codes);
			manager.newCodeTable(systemAdmin, codeTable);
			//执行完后对比数据st_codetable , st_codetablefield,以及实际创建的表，看看执行结果自己判断
//			System.out.println("新增系统代码表成功！");
		} catch (Exception e) {
			fail("Not yet implemented");
		}
		
	}
	
	@Test
	public void testUpdateCodeTable(){
		try {
			ApplicationContext ctx = BaseContext.getApplicationContext();
			CodeTableManager manager = (CodeTableManager)ComponentFactory.getManager("CodeTableManager");
			
			String name = "项目优先级";
			String description = "定义项目的优先级：高中低；用于设置项目的优先级。";
			int idRule = 0;
			int tableID = 1000;
			String tableName = "codeTable_"+tableID;
			
			String[] seqNos = new String[]{"1","2","3"};
			String[] isModified = new String[]{"true","true","true"};
			String[] codeID = new String[]{"1000","10001","1002"};
			String[] codeName = new String[]{"高","中","低"};
			String[] codeDesp = new String[]{"项目优先级为：高","项目优先级为：中","项目优先级为：低"};
			
			CodeTable codeTable = ctx.getBean("CodeTable", CodeTable.class);
			codeTable.setId(tableID);
			codeTable.setTablename(tableName);
			codeTable.setName(name);
			codeTable.setDescription(description);
			codeTable.setStatus(CodeTable.STATUS_ACTIVE);
			codeTable.setType(CodeTable.TYPE_OWN);
			codeTable.setIdRule(idRule);
			codeTable.setCompanyID(systemAdmin.getCompanyID());
			codeTable.setCreateBy(systemAdmin.getId());
			codeTable.setCreateTime(CalendarUtils.getCurrentDate());
			codeTable.setLastUpdateBy(systemAdmin.getId());
			codeTable.setLastUpdateTime(CalendarUtils.getCurrentDate());
			Set<CodeTableField> filedSet = initCodeTableFieldSet(codeTable);
			codeTable.setCodeTableFields(filedSet);
			
			List<CommonCode> codes = new ArrayList<CommonCode>();
			int index = 0;
			if(seqNos != null && seqNos.length > 0){
				for(String seqNo : seqNos){
					CommonCode code = ctx.getBean("CommonCode", CommonCode.class);
					if(idRule == CodeTable.ID_RULE_MANUL){
						//手动设置
						code.setId(TypeUtils.nullToInt(codeID[index]));
					}
					code.setName(codeName[index]);
					code.setDescription(codeDesp[index]);
					code.setSeqNo(TypeUtils.nullToInt(seqNo));
					code.setModified(TypeUtils.stringToBoolean(isModified[index]));
					code.setStatus(CommonCode.STATUS_ACTIVE);
					code.setCompanyID(1000);
					codes.add(code);
					index++;
				}
			}
			codeTable.setCodeList(codes);
			manager.updateCodeTable(systemAdmin, codeTable);
//			System.out.println("修改系统代码表成功！");
		} catch (Exception e) {
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testSelectCodeTable(){
		try {
			User user = this.getUser(1000, 1000);
			
			CodeTableManager manager = (CodeTableManager)ComponentFactory.getManager("CodeTableManager");
			int tableID = 1000;
			CodeTable table = manager.getCodeTable(user, tableID);
			if(table != null){
				System.out.println("============== 输出 codetable 信息 =============");
				System.out.println("id : "+ table.getId());
				System.out.println("name : "+ table.getName());
				System.out.println("description : "+ table.getDescription());
				System.out.println();
				
				System.out.println("============== 输出 commonCode 信息 =============");
				List<CommonCode> codes = table.getCodeList();
				if(codes != null && codes.size() >0){
					for(CommonCode code : codes){
						System.out.println("codeID : " + code.getId());
						System.out.println("codeName : " + code.getName());
						System.out.println("codeDescription : " + code.getDescription());
						System.out.println();
					}
				}
			}
			
			assertNotNull("查询失败！", table);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testDeleteCodeTable(){
		try {
			User user = this.getUser(1000, 1000);
			
			CodeTableManager manager = (CodeTableManager)ComponentFactory.getManager("CodeTableManager");
			int tableID = 1000;
			CodeTable table = manager.getCodeTable(user, tableID);
			
			if(table != null){
				manager.deleteCodeTable(user, table);
				CodeTable newTable = null;
				try {
					newTable = manager.getCodeTable(user, tableID);
				} catch (Exception e) {
					e.printStackTrace();
				}
				assertNull("删除代码表失败", newTable);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testBatchDelete(){
		try {
			ApplicationContext ctx = BaseContext.getApplicationContext();
			User user = this.getUser(1000, 1000);
			CodeTableManager manager = (CodeTableManager)ComponentFactory.getManager("CodeTableManager");
			int[] ids = new int[]{1000,1001};
			manager.batchDeleteCodeTables(user, ids);
			PageInfo pageInfo = ctx.getBean("PageInfo", PageInfo.class);
			pageInfo = manager.getCodeTables(user, pageInfo);
			List<CodeTable> list = pageInfo.getItems();
			
			assertEquals("删除失败 ！", 0, list.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testSelectCommonCodes(){
		try {
			User user = this.getUser(1000, 1000);
			CodeTableManager manager = (CodeTableManager)ComponentFactory.getManager("CodeTableManager");
			int tableID = 1000;
			CodeTable table = manager.getCodeTable(user, tableID);
			List<CommonCode> codes = manager.getCommonCodes(user, table.getTablename());
			for(CommonCode code : codes){
				System.out.println("codeID : " + code.getId());
				System.out.println("codeName : " + code.getName());
				System.out.println("codeDescription : " + code.getDescription());
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	private Set<CodeTableField> initCodeTableFieldSet(CodeTable table){
		Set<CodeTableField> fields = new HashSet<CodeTableField>();
		int tableID = table.getId();
		int fieldID = tableID * 10 + 1;
		int seq = 1;
		ApplicationContext ctx = BaseContext.getApplicationContext();
		CodeTableField name = ctx.getBean("CodeTableField", CodeTableField.class);
		name.setTableID(table.getId());
		name.setId(fieldID++);
		name.setDescription("名称1");
		name.setFieldid(0);
		name.setStatus(1);
		name.setType(0);
		name.setFieldtype(0);
		name.setDatatype(0);
		name.setDatalength(100);
		name.setDataprecision(0);
		name.setDataformat(0);
		name.setDefaultvalue(null);
		name.setSeqno(seq++);
		name.setFieldlevel(1);
		name.setUsecount(0);
		name.setCompanyID(table.getCompanyID());
		fields.add(name);
		
		CodeTableField description = ctx.getBean("CodeTableField", CodeTableField.class);
		description.setTableID(table.getId());
		description.setId(fieldID++);
		description.setDescription("描述1");
		description.setFieldid(0);
		description.setStatus(1);
		description.setType(0);
		description.setFieldtype(0);
		description.setDatatype(0);
		description.setDatalength(500);
		description.setDataprecision(0);
		description.setDataformat(0);
		description.setDefaultvalue(null);
		description.setSeqno(seq++);
		description.setFieldlevel(1);
		description.setUsecount(0);
		description.setCompanyID(table.getCompanyID());
		fields.add(description);
		
		CodeTableField companyID = ctx.getBean("CodeTableField", CodeTableField.class);
		companyID.setTableID(table.getId());
		companyID.setId(fieldID++);
		companyID.setDescription("公司");
		companyID.setFieldid(0);
		companyID.setStatus(1);
		companyID.setType(0);
		companyID.setFieldtype(0);
		companyID.setDatatype(0);
		companyID.setDatalength(100);
		companyID.setDataprecision(0);
		companyID.setDataformat(0);
		companyID.setDefaultvalue(null);
		companyID.setSeqno(seq++);
		companyID.setFieldlevel(1);
		companyID.setUsecount(0);
		companyID.setCompanyID(table.getCompanyID());
		fields.add(companyID);
		
		CodeTableField seqNo = ctx.getBean("CodeTableField", CodeTableField.class);
		seqNo.setTableID(table.getId());
		seqNo.setId(fieldID++);
		seqNo.setDescription("序号");
		seqNo.setFieldid(0);
		seqNo.setStatus(1);
		seqNo.setType(0);
		seqNo.setFieldtype(1);
		seqNo.setDatatype(1);
		seqNo.setDatalength(12);
		seqNo.setDataprecision(0);
		seqNo.setDataformat(0);
		seqNo.setDefaultvalue(null);
		seqNo.setSeqno(seq++);
		seqNo.setFieldlevel(1);
		seqNo.setUsecount(0);
		seqNo.setCompanyID(table.getCompanyID());
		fields.add(seqNo);
		
		CodeTableField parentID = ctx.getBean("CodeTableField", CodeTableField.class);
		parentID.setTableID(table.getId());
		parentID.setId(fieldID++);
		parentID.setDescription("分类");
		parentID.setFieldid(0);
		parentID.setStatus(1);
		parentID.setType(0);
		parentID.setFieldtype(1);
		parentID.setDatatype(1);
		parentID.setDatalength(12);
		parentID.setDataprecision(0);
		parentID.setDataformat(0);
		parentID.setDefaultvalue(null);
		parentID.setSeqno(seq++);
		parentID.setFieldlevel(1);
		parentID.setUsecount(0);
		parentID.setCompanyID(table.getCompanyID());
		fields.add(parentID);
		
		CodeTableField status = ctx.getBean("CodeTableField", CodeTableField.class);
		status.setTableID(table.getId());
		status.setId(fieldID++);
		status.setDescription("状态");
		status.setFieldid(0);
		status.setStatus(1);
		status.setType(0);
		status.setFieldtype(1);
		status.setDatatype(1);
		status.setDatalength(12);
		status.setDataprecision(0);
		status.setDataformat(0);
		status.setDefaultvalue(null);
		status.setSeqno(seq++);
		status.setFieldlevel(1);
		status.setUsecount(0);
		status.setCompanyID(table.getCompanyID());
		fields.add(status);
		
		return fields;
		
	}

}
