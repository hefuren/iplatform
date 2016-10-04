package com.bluesky.iplatform.component.from.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bluesky.iplatform.component.from.dao.FormFieldDAO;
import com.bluesky.iplatform.component.from.dao.FormFilterDAO;
import com.bluesky.iplatform.component.from.dao.FormFilterItemDAO;
import com.bluesky.iplatform.component.from.dao.FormListDAO;
import com.bluesky.iplatform.component.from.dao.FormListItemDAO;
import com.bluesky.iplatform.component.from.dao.FormSchemaDAO;
import com.bluesky.iplatform.component.from.dao.FormViewDAO;
import com.bluesky.iplatform.component.from.dao.FormViewItemDAO;
import com.bluesky.iplatform.component.from.model.FormField;
import com.bluesky.iplatform.component.from.model.FormFilter;
import com.bluesky.iplatform.component.from.model.FormList;
import com.bluesky.iplatform.component.from.model.FormSchema;
import com.bluesky.iplatform.component.from.model.FormView;
import com.bluesky.iplatform.component.from.model.FormViewItem;
import com.bluesky.iplatform.component.profile.model.User;

@Service(value = "FormManagerService")
public class FormManagerService implements FormManager {
	
	@Resource(name = "FormSchemaDAOImpl")
	private FormSchemaDAO schemaDAO;
	
	@Resource(name = "FormFieldDAOImpl")
	private FormFieldDAO fieldDAO;
	
	@Resource(name = "FormViewDAOImpl")
	private FormViewDAO viewDAO;
	
	@Resource(name = "FormViewItemDAOImpl")
	private FormViewItemDAO viewItemDAO;
	
	@Resource(name = "FormListDAOImpl")
	private FormListDAO listDAO;
	
	@Resource(name = "FormListItemDAOImpl")
	private FormListItemDAO listItemDAO;
	
	@Resource(name = "FormFilterDAOImpl")
	private FormFilterDAO filterDAO;
	
	@Resource(name = "FormFilterItemDAOImpl")
	private FormFilterItemDAO filterItemDAO;

	@Override
	public void newFormSchema(User user, FormSchema formSchema) {
		try {
			schemaDAO.newMode(user, formSchema);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFormSchemas(User user, int[] ids) {
		try {
			schemaDAO.batchDeleteModes(user, ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getFormSchemas(User user) {
		try {
			schemaDAO.getCompanyAllModes(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public FormSchema getFormSchema(User user, int id) {
		try {
			schemaDAO.getMode(user, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<FormField> getFormFields(User user, FormSchema form) {
		try {
			fieldDAO.getFormFields(user, form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveFormFields(User user, List<FormField> fields) {
		try {
			fieldDAO.saveModes(user, fields);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<FormView> getFormViews(User user, FormSchema form) {
		List<FormView> modes = null;
		try {
			modes = viewDAO.getFormViews(user, form);
			return modes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modes;
	}

	@Override
	public void updateFormSchema(User user, FormSchema form) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newFormView(User user, FormView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFormView(User user, FormView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFormView(User user, FormView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFormViews(User user, int[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FormView getFormView(User user, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FormViewItem> getFormViewItems(User user, FormView formView) {
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
	public void updateFormList(User user, FormList formList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newFormFilter(User user, FormFilter formFilter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFormFilter(User user, FormFilter formFilter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFormFilter(User user, FormFilter formFilter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FormFilter getFormFilter(User user, FormSchema formSchema) {
		// TODO Auto-generated method stub
		return null;
	}


}
