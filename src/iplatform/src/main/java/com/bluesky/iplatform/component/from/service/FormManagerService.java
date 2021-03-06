package com.bluesky.iplatform.component.from.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.bluesky.iplatform.commons.utils.TypeUtils;
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
import com.bluesky.iplatform.component.from.model.FormFilterItem;
import com.bluesky.iplatform.component.from.model.FormList;
import com.bluesky.iplatform.component.from.model.FormListItem;
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
	public void updateFormSchema(User user, FormSchema form) {
		try {
			schemaDAO.updateMode(user, form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteFormSchemas(User user, int[] ids) {
		try {
			//删除Form需要删除对应的对象
			List<Object> idList = TypeUtils.arrayToList(ids);
			
			//删除FormList
			Example example = new Example(FormListItem.class);
			example.createCriteria().andIn("schemaID", idList);
			listItemDAO.deleteModesByExample(user, example);
			
			example = new Example(FormList.class);
			example.createCriteria().andIn("schemaID", idList);
			listDAO.deleteModesByExample(user, example);
			
			//删除FormView
			example = new Example(FormViewItem.class);
			example.createCriteria().andIn("schemaID", idList);
			viewItemDAO.deleteModesByExample(user, example);
			
			example = new Example(FormView.class);
			example.createCriteria().andIn("schemaID", idList);
			viewDAO.deleteModesByExample(user, example);
			
			//删除FormFilter
			example = new Example(FormFilterItem.class);
			example.createCriteria().andIn("schemaID", idList);
			filterItemDAO.deleteModesByExample(user, example);
			
			example = new Example(FormFilter.class);
			example.createCriteria().andIn("schemaID", idList);
			filterDAO.deleteModesByExample(user, example);
			
			//删除FormField
			example = new Example(FormField.class);
			example.createCriteria().andIn("schemaID", idList);
			fieldDAO.deleteModesByExample(user, example);
			
			schemaDAO.batchDeleteModes(user, ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<FormSchema> getFormSchemas(User user) {
		try {
			List<FormSchema> modes = schemaDAO.getCompanyAllModes(user);
			return modes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FormSchema getFormSchema(User user, int id) {
		try {
			FormSchema mode = (FormSchema)schemaDAO.getMode(user, id);
			return mode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<FormField> getFormFields(User user, FormSchema form) {
		try {
			List<FormField> modes = fieldDAO.getFormFields(user, form);
			return modes;
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
			modes = viewDAO.getModes(user, form);
			return modes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modes;
	}

	@Override
	public void newFormView(User user, FormView view) {
		try {
			//保存表单视图
			viewDAO.newMode(user, view);
			//保存表单视图对应的viewItem
			List<FormViewItem> items = view.getFormViewItems();
			viewItemDAO.saveModes(user, items);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateFormView(User user, FormView view) {
		try {
			//保存表单视图信息
			viewDAO.updateMode(user, view);
			//保存表单视图对应的viewItem
			List<FormViewItem> items = view.getFormViewItems();
			viewItemDAO.saveModes(user, items);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFormView(User user, FormView view) {
		try {
			//已通过数据外键关联自动删除viewItem
			viewDAO.deleteMode(user, view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFormViews(User user, int[] ids) {
		try {
			viewDAO.batchDeleteModes(user, ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public FormView getFormView(User user, int id) {
		FormView mode = null;
		try {
			mode = (FormView)viewDAO.getMode(user, id);
			return mode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mode;
	}

	@Override
	public List<FormViewItem> getFormViewItems(User user, FormView formView) {
		List<FormViewItem> modes = null;
		try {
			modes = viewDAO.getFormViewItems(user, formView);
			return modes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modes;
	}

	@Override
	public FormList getFormList(User user, FormSchema formSchema) {
		FormList modes = null;
		try {
			modes = listDAO.getMode(user, formSchema);
			return modes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void newFormList(User user, FormList formList) {
		try {
			listDAO.newMode(user, formList);
			List<FormListItem> items = formList.getFormListItems();
			listItemDAO.saveModes(user, items);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateFormList(User user, FormList formList) {
		try {
			listDAO.updateMode(user, formList);
			List<FormListItem> items = formList.getFormListItems();
			listItemDAO.saveModes(user, items);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void newFormFilter(User user, FormFilter formFilter) {
		try {
			filterDAO.newMode(user, formFilter);
			List<FormFilterItem> items = formFilter.getFormFilterItems();
			filterItemDAO.saveModes(user, items);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateFormFilter(User user, FormFilter formFilter) {
		try {
			filterDAO.updateMode(user, formFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteFormFilter(User user, FormFilter formFilter) {
		try {
			filterDAO.deleteMode(user, formFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<FormFilter> getFormFilters(User user, FormSchema formSchema) {
		List<FormFilter> modes = null;
		try {
			modes = filterDAO.getModes(user, formSchema);
			return modes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modes;
	}

	@Override
	public FormFilter getFormFilters(User user, int id) {
		FormFilter mode = null;
		try {
			mode = (FormFilter)filterDAO.getMode(user, id);
			return mode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mode;
	}


}
