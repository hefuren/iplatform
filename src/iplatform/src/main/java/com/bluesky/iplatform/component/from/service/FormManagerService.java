package com.bluesky.iplatform.component.from.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bluesky.iplatform.component.from.model.FormField;
import com.bluesky.iplatform.component.from.model.FormFilter;
import com.bluesky.iplatform.component.from.model.FormList;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.from.model.FormView;
import com.bluesky.iplatform.component.from.model.FormViewItem;
import com.bluesky.iplatform.component.profile.model.User;

@Service(value = "FormManagerService")
public class FormManagerService implements FormManager{

	@Override
	public void newFormSchema(User user, FormSchema formSchema) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFormSchemas(User user, int[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getFormSchemas(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FormSchema getFormSchema(User user, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FormField> getSchemaFormFields(User user, FormSchema formSchema) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSchemaFormFields(User user, List<FormField> fields) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FormView> getSchemaFormViews(User user, FormSchema formSchema) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FormViewItem> getFormViewItem(User user, FormView formView) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormList getFormList(User user, FormSchema formSchema) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void newFormList(User user, FormList formList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveFormFilter(User user, FormFilter formFilter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FormFilter getFormFilter(User user, FormSchema formSchema) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
