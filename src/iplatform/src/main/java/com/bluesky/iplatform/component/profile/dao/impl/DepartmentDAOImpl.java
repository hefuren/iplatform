package com.bluesky.iplatform.component.profile.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.ExecutorType;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.commons.hierarchy.Hierarchy;
import com.bluesky.iplatform.commons.hierarchy.Hierarchyable;
import com.bluesky.iplatform.component.profile.dao.DepartmentDAO;
import com.bluesky.iplatform.component.profile.mapper.DepartmentMapper;
import com.bluesky.iplatform.component.profile.model.Department;
import com.bluesky.iplatform.component.profile.model.User;

@Repository(value = "DepartmentDAOImpl")
public class DepartmentDAOImpl extends BaseSingleMyBatisDAOImpl<Department> implements
		DepartmentDAO<Department> {
	
	 /**
     * 初始化通用的Mapper
     */
	@PostConstruct 
	public void initMapper(){
    	ApplicationContext ctx = BaseContext.getApplicationContext();
    	SqlSessionTemplate sqlSession = (SqlSessionTemplate)ctx.getBean("sqlSessionTemplate");    	
		this.mapper  = (Mapper<Department>) sqlSession.getMapper(DepartmentMapper.class);
	}
	
	@Override
	public Hierarchy getDepartmentTree(User user) throws Exception {
		Hierarchy hierarchy = new Hierarchy(Department.SeqComparator);		
		Department rootNode = getMode(user, Department.ROOTNODE);
		hierarchy.setRootNode(rootNode);
		
		List<Department> subDepts = this.getCompanyAllModes(user);
		for(Department subDept : subDepts){
			hierarchy.addObject(subDept);
		}
		return hierarchy;
	}

	@Override
	public void saveDepartment(User user, Hierarchy hierarchy) throws Exception {
		log.debug("saving Department instance");
		SqlSessionTemplate sqlSession = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH); 
		try{
			Hierarchyable rootNode = hierarchy.getRootNode();
			List<Hierarchyable> modes = hierarchy.getAllChildren(rootNode);
			modes.add(rootNode);
			
			//删除该公司的组织结构
			//String hql = "delete from Department as a where a.companyID = ? and a.id <> ?";
			Example example = new Example(Department.class);
			example.createCriteria().andEqualTo("companyID", new Integer(user.getCompanyID()));
			example.createCriteria().andNotEqualTo("id", new Integer(Department.ROOTNODE));
			this.mapper.deleteByExample(example);

			for(Hierarchyable mode : modes){
				Department dept = (Department)mode;
				if(mode.getId() != Department.ROOTNODE)
					this.mapper.insert(dept);
				else
					this.mapper.updateByPrimaryKey(dept);
			}
		}catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
		
	}

}
