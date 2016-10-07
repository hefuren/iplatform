package com.bluesky.iplatform.component.form;

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
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.from.model.FormField;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.from.service.FormManager;
import com.bluesky.iplatform.component.profile.model.User;
import com.bluesky.iplatform.component.utils.ComponentFactory;

public class FormFieldTest extends BaseUnitlsTest {

	@Test
	public void testNew() {
		this.setTestStartTime();
		try {
			ApplicationContext ctx = BaseContext.getApplicationContext();
			FormManager manager = (FormManager)ComponentFactory.getManager("FormManager");
			User user = this.getUser(1000, 1000);
			int schemaID = 1000;
			
			Long[] ids = SequenceUtils.getSequence("fm_formfield", 2);
			String[] names = new String[]{"name1","name2"};
			String[] fieldIDs = new String[]{"str01","str02"};
			String[] lableNames = new String[]{"优先级","风险等级"};
			int[] fieldTypes = new int[]{FormField.FIELDTYPE_FORM,FormField.FIELDTYPE_FORM};
			
			
			FormField field = ctx.getBean("FormField",FormField.class);
			field.setId(TypeUtils.nullToInt(ids[0]));
			field.setSchemaID(schemaID);
			field.setName(names[0]);
			field.setFieldKey(fieldIDs[0]);
			field.setLableName(lableNames[0]);
			field.setFieldType(fieldTypes[0]);
			field.setCompanyID(user.getCompanyID());
			field.setNew(true);
			
			FormField field2 = ctx.getBean("FormField",FormField.class);
			field2.setId(TypeUtils.nullToInt(ids[1]));
			field2.setSchemaID(schemaID);
			field2.setName(names[1]);
			field2.setFieldKey(fieldIDs[1]);
			field2.setLableName(lableNames[1]);
			field2.setFieldType(fieldTypes[1]);
			field2.setCompanyID(user.getCompanyID());
			field2.setNew(true);
			
			List<FormField> fields = new ArrayList<FormField>();
			fields.add(field);
			fields.add(field2);
			
			manager.saveFormFields(user, fields);
			
			FormSchema form = manager.getFormSchema(user, schemaID);
			List<FormField> formFields = manager.getFormFields(user, form);
			
			Set<Integer> idsSet = new HashSet<Integer>();
			for(FormField filedField : formFields){
				idsSet.add(new Integer(filedField.getId()));
			}
			
			boolean result = false;
			result = idsSet.contains(new Integer(field.getId())) && idsSet.contains(new Integer(field2.getId()));
			
			if (result) {
				System.out.println("新增表单字段成功 ！！！");
			}
			
			assertEquals("新增字段失败！！！", true, result);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
		this.setTestEndTime();
	}
	
	@Test
	public void selectTest(){
		try {
			FormManager manager = (FormManager)ComponentFactory.getManager("FormManager");
			User user = this.getUser(1000, 1000);
			int schemaID = 1000;
			
			FormSchema form = manager.getFormSchema(user, schemaID);
			List<FormField> formFields = manager.getFormFields(user, form);
			
			for(FormField mode : formFields){
				System.out.println("============= 输出字段信息  =============");
				System.out.println("id : "+mode.getId());
				System.out.println("Name : "+mode.getName());
				System.out.println("fieldID : "+mode.getFieldKey());
				System.out.println("lableName : "+mode.getLableName());
				System.out.println("companyID : "+mode.getCompanyID());
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	
	@Test
	public void testDelete(){
		try {
			FormManager manager = (FormManager)ComponentFactory.getManager("FormManager");
			User user = this.getUser(1000, 1000);
			int schemaID = 1000;
			
			FormSchema form = manager.getFormSchema(user, schemaID);
			List<FormField> formFields = manager.getFormFields(user, form);
			int size = formFields.size();
			for(int i=0;i<size;i++){
				int temp = size -i;
				if(temp == 1 || temp == 2){
					FormField field = formFields.get(i);
					field.setDeleted(true);
				}
			}
			manager.saveFormFields(user, formFields);
			List<FormField> fields = manager.getFormFields(user, form);
			
			assertEquals("删除字段失败", TypeUtils.nullToLong(size-2), TypeUtils.nullToLong(fields.size()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}
	

}
