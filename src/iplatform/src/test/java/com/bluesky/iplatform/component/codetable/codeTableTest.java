package com.bluesky.iplatform.component.codetable;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.bluesky.iplatform.common.BaseUnitlsTest;
import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.commons.utils.CalendarUtils;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.codetable.model.CodeTable;
import com.bluesky.iplatform.component.codetable.model.CodeTableField;
import com.bluesky.iplatform.component.codetable.model.CommonCode;
import com.bluesky.iplatform.component.codetable.service.CodeTableManager;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class codeTableTest extends BaseUnitlsTest{

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
					code.setStatus(CommonCode.STATUS_ACTIVE);
					code.setCompanyID(systemAdmin.getCompanyID());
					codes.add(code);
					index++;
				}
			}
			codeTable.setCodeList(codes);
			
			manager.newCodeTable(systemAdmin, codeTable);
		} catch (Exception e) {
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
		name.setDescription("名称");
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
		description.setDescription("描述");
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
