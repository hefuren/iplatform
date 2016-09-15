package com.bluesky.iplatform.commons.db.mybatis.dao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import com.bluesky.iplatform.commons.db.PageInfo;
import com.bluesky.iplatform.commons.db.mybatis.dao.BaseSingleMyBatisDAO;
import com.bluesky.iplatform.commons.object.BatchObject;
import com.bluesky.iplatform.component.profile.model.User;
import com.github.pagehelper.PageHelper;

/**
 * 通用MyBatis DAO实现层，主要用于单表操作
 * @author ElwinHe *
 * @param <T>
 *  
 * 平台考虑SaaS模式，在对数据库操作时，需要判断用户权限，仅允许操作所在公司/租户的数据，
 * 系统管理员可以操作所有数据。
 */

@Repository(value = "BaseSingleMyBatisDAOImpl")
public abstract class BaseSingleMyBatisDAOImpl<T> extends SqlSessionDaoSupport implements BaseSingleMyBatisDAO<T>{
	
	/**
	 * 实体类对象
	 */
	protected Class<T> entityClass;

	/**
	 * 类名
	 */
	protected String className;
	
	public static final Logger log = LoggerFactory.getLogger(BaseSingleMyBatisDAOImpl.class);

	
	protected Mapper<T> mapper;
	
	@Resource(name = "sqlSessionFactory")  
	protected SqlSessionFactory sqlSessionFactory;  
  
	protected SqlSession sqlSession;  
    
    public BaseSingleMyBatisDAOImpl() {
    	entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		className = entityClass.getName();
	}
    
    @PostConstruct  
    public void SqlSessionFactory() {  
        super.setSqlSessionFactory(sqlSessionFactory);  
    }  
    
    /**
     * 初始化通用的Mapper
     * 子类需要重写该方法，注入自己的xxxMapper
     */
    @PostConstruct  
	public void initMapper(){
    	//子类需要重写该方法，注入自己的xxxMapper
	}
    
	@Autowired
	 public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
	     super.setSqlSessionTemplate(sqlSessionTemplate);
	 }

	@Override
	public T getMode(User user, int id) {
		log.debug("getting " + className + " instance with id: " + id);
		sqlSession = sqlSessionFactory.openSession();
		try {
			T instance = mapper.selectByPrimaryKey(new Integer(id));
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}

	@Override
	public List<T> getModes(User user, int[] ids) {
		log.debug("getting " + className + " instance with ids. ");
		sqlSession = sqlSessionFactory.openSession();
		try {
			Example example = new Example(entityClass.getClass());
			Set idSet = new HashSet<>();
			for(int id : ids){
				idSet.add(new Integer(id));
			}
			example.createCriteria().andIn("id", idSet);
			List<T> modes = mapper.selectByExample(example);
			return modes;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}

	@Override
	public void newMode(User user, T t) {
		log.debug("saving " + className + " instance");
		sqlSession = sqlSessionFactory.openSession();
		try {
			mapper.insert(t);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
		
	}

	@Override
	public void batchNewModes(User user, List<T> modes) {
		log.debug("saving " + className + " instance");
		sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,true);//用于批量操作
		try {
			for (T t : modes) {
				// 设置循环批量插入数据
				mapper.insert(t);
			}
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}

	@Override
	public void updateMode(User user, T t) {
		log.debug("updating " + className + " instance");
		sqlSession = sqlSessionFactory.openSession();
		try {
			mapper.updateByPrimaryKey(t);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}		
	}

	@Override
	public void batchUpdateModes(User user, List<T> modes) {
		log.debug("batch updating " + className + " instance");
		sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,true);//用于批量操作
		try {
			for (T t : modes) {
				mapper.updateByPrimaryKey(t);
			}
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("batch update failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}		
	}

	@Override
	public void saveOrUpdateModes(User user, List<T> modes) {
		log.debug("batch updating " + className + " instance");
		sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,true);//用于批量操作
		try {
			for (T t : modes) {
				BatchObject obj = (BatchObject)t;
				if(obj.isNew()){
					mapper.insert(t);
				}else if(obj.isDeleted()){
					mapper.delete(t);
				}else {
					mapper.updateByPrimaryKey(t);
				}
			}
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("batch update failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}		
	}

	@Override
	public void deleteMode(User user, T t) {
		log.debug("deleting " + className + " instance");
		sqlSession = sqlSessionFactory.openSession();
		try {
			mapper.delete(t);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
		
	}

	@Override
	public void batchDeleteModes(User user, int[] ids) {
		log.debug("deleting " + className + " instance");
		sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,true);//用于批量操作
		try {
			for(int id : ids){
				mapper.deleteByPrimaryKey(new Integer(id));
			}
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}		
	}

	@Override
	public List<T> getCompanyAllModes(User user) {
		log.debug("finding all " + className + " instances");
		sqlSession = sqlSessionFactory.openSession();
		try {
			Example example = new Example(entityClass.getClass());
			example.createCriteria().andEqualTo("companyID", new Integer(user.getCompanyID()));
			List<T> modes = mapper.selectByExample(example);
			return modes;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}

	@Override
	public T getModeByProperty(String propertyName, Object value) {
		log.debug("finding all " + className + " instances");
		sqlSession = sqlSessionFactory.openSession();
		try {
			Example example = new Example(entityClass.getClass());
			example.createCriteria().andEqualTo(propertyName, value);
			List<T> modes = mapper.selectByExample(example);
			T mode = null;
			if(modes.size()>0 && modes.size()==1){
				mode = modes.get(0); 
			}
			return mode;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}

	@Override
	public List<T> getModesByProperty(String propertyName, Object value) {
		log.debug("finding all " + className + " instances");
		sqlSession = sqlSessionFactory.openSession();
		try {
			Example example = new Example(entityClass.getClass());
			example.createCriteria().andEqualTo(propertyName, value);
			List<T> modes = mapper.selectByExample(example);
			return modes;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}

	@Override
	public List<T> getModesByExample(T t) {
		log.debug("finding all " + className + " instances");
		sqlSession = sqlSessionFactory.openSession();
		try {
			List<T> modes = mapper.select(t);
			return modes;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}

	@Override
	public PageInfo getByPageInfo(User user, PageInfo pageInfo) {
		log.debug("finding all " + className + " instances");
		try {
			Map<String, Object> conditions = (Map<String, Object>) pageInfo.getConditions();
			List items = new ArrayList();
			String[] paramNames = null;
			Example example = new Example(entityClass.getClass());
			if (conditions != null && conditions.size() > 0) {
				// 带条件查询
				paramNames = new String[conditions.size()];
				conditions.keySet().toArray(paramNames);
				for (String paramName : paramNames) {
					example.createCriteria().andEqualTo(paramName, conditions.get(paramName));
				}
			}
			
			sqlSession = sqlSessionFactory.openSession();
			if (pageInfo.isPaged()) {
				// 分页查询
				int totalRows = mapper.selectCountByExample(example);
				pageInfo.setTotalRows(totalRows);
				int pageCounts = (totalRows % pageInfo.getPageSize() > 0) ? totalRows / pageInfo.getPageSize() +1 : totalRows / pageInfo.getPageSize(); 
				pageInfo.setPageCount(pageCounts);
				int currentPage = pageInfo.getCurrentPage();
				int pageSize = pageInfo.getPageSize();
				
				//在需要进行分页的Mybatis方法前调用PageHelper.startPage静态方法，紧跟在这个方法后的第一个Mybatis查询方法会被进行分页。
				PageHelper.startPage(currentPage, pageSize);
				items = mapper.selectByExample(example);
				
			} else {
				items = mapper.selectByExample(example);
			}
			pageInfo.setItems(items);
		
			return pageInfo;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}finally{
			sqlSession.close();
		}
	}
	
}
