package com.bluesky.iplatform.component.codetable.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.ExecutorType;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.commons.db.SequenceUtils;
import com.bluesky.iplatform.commons.utils.BaseContext;
import com.bluesky.iplatform.commons.utils.TypeUtils;
import com.bluesky.iplatform.component.codetable.dao.CodeTableDAO;
import com.bluesky.iplatform.component.codetable.mapper.CodeTableFieldMapper;
import com.bluesky.iplatform.component.codetable.mapper.CodeTableMapper;
import com.bluesky.iplatform.component.codetable.model.CodeTable;
import com.bluesky.iplatform.component.codetable.model.CodeTableField;
import com.bluesky.iplatform.component.codetable.model.CommonCode;
import com.bluesky.iplatform.component.profile.model.User;
import com.github.abel533.sql.SqlMapper;

@Repository(value="CodeTableDAOImpl")
public class CodeTableDAOImpl extends BaseSingleMyBatisDAOImpl<CodeTable> implements CodeTableDAO<CodeTable> {
	
	
	@Override
	public void initMapperType() {
		mapperType = CodeTableMapper.class;
	}

	/**
	 * 新建CodeTable
	 * （需要创建对应的CodeTable表）
	 */
	@Override
	public void newMode(User user, CodeTable codeTable){
		log.debug("saving " + className + " instance");
		ApplicationContext ctx = BaseContext.getApplicationContext();
		String tableName = codeTable.getTablename();
		sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,true);//用于批量操作
		
		Mapper<CodeTable> mapper = this.getMapper(sqlSession, mapperType);
		try {
			//动态更新CodeTable Sequence Cache
			SequenceUtils.dynamicUpdateSequenceCache(codeTable.getTablename());
			//插入主表
			mapper.insert(codeTable);
			//插入子表
			Set<CodeTableField> fields = codeTable.getCodeTableFields();
			Mapper<CodeTableField> fieldMap = (Mapper<CodeTableField>) sqlSession.getMapper(CodeTableFieldMapper.class);
			for (CodeTableField t : fields) {
				// 设置循环批量插入数据
				fieldMap.insert(t);
			}
			//创建系统代码表
			SqlMapper sqlMapper = ctx.getBean("sqlMapper", SqlMapper.class);
			final String sql ="create table " + tableName + "(" +
					"	id		int		not null," +
					"	name	varchar(100)	null," +
					"	parentID	int		null," +
					"	description varchar(500)	null," +
					"	companyID	int		not null," +
					"	seqno		int		null," +
					"	status		int		null," +
					"	constraint pk_"+tableName+" primary key (id)" +
					")";
			
			sqlMapper.update(sql);
			
			//插入代码值
			final String codeTableName = tableName;
			final List codeList = codeTable.getCodeList();
			batchSaveCommonCodes(codeTableName, codeList);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			dorpErrorCodeTable(tableName);
			throw re;
		} finally{
			sqlSession.close();
		}
	}
	
	/**
	 * 删除CodeTable
	 * （删除对应的CodeTable表）
	 */
	@Override
	public void deleteMode(User user, CodeTable codeTable){
		log.debug("deleting " + className + " instance");
		ApplicationContext ctx = BaseContext.getApplicationContext();
		sqlSession = sqlSessionFactory.openSession();
		try {
			Mapper<CodeTable> mapper = this.getMapper(sqlSession, mapperType);
			mapper.delete(codeTable);
			//删除系统代码表
			SqlMapper sqlMapper = ctx.getBean("sqlMapper", SqlMapper.class);
			String tableName = codeTable.getName();
			String sql = "drop table " + tableName +"";
			sqlMapper.update(sql);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		} finally{
			sqlSession.close();
		}
	}
	
	/**
	 * 批量删除CodeTable
	 *  （删除对应的CodeTable表）
	 */
	@Override
	public void batchDeleteModes(User user, int[] ids){
		log.debug("deleting " + className +" instance");
		ApplicationContext ctx = BaseContext.getApplicationContext();
		sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,true);//用于批量操作
		try {
			Mapper<CodeTable> mapper = this.getMapper(sqlSession, mapperType);
			List<CodeTable> tables = this.getModes(user, ids);
			Map<Integer,CodeTable> tableMaps = new HashMap();
			for(CodeTable table : tables){
				tableMaps.put(new Integer(table.getId()), table);
			}
			for (int id : ids) {
				// 循环批量删除数据
				mapper.deleteByPrimaryKey(new Integer(id));
				//删除codeTable
				SqlMapper sqlMapper = ctx.getBean("sqlMapper", SqlMapper.class);
				CodeTable table = tableMaps.get(new Integer(id));
				String tableName = TypeUtils.nullToString(table.getName());
				String sql = "drop table " + tableName +"";
				sqlMapper.update(sql);
			}
			log.debug("batch delete successful");
		} catch (RuntimeException re) {
			log.error("batch delete failed", re);
			throw re;
		} finally{
			sqlSession.close();
		}
	}
	
	@Override
	public CodeTable getMode(User user, int id) {
		log.debug("getting " + className + " instance with id: " + id);
		CodeTable codeTable = null;
		sqlSession = sqlSessionFactory.openSession();
		try {
			Mapper<CodeTable> mapper = this.getMapper(sqlSession, mapperType);
			codeTable = mapper.selectByPrimaryKey(id);
			final String tableName = codeTable.getTablename();
			final User tempUser = user;
			List<CommonCode> codesList = getCommonCodes(tempUser, tableName);
			codeTable.setCodeList(codesList);
			
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally{
			sqlSession.close();
		}
		return codeTable;
	}
	
	@Override
	public CodeTable getCodeTalbe(User user, String codeTalbeName) {
		log.debug("getting " + className + " instance with Name: " + codeTalbeName);
		CodeTable codeTable = null;
		sqlSession = sqlSessionFactory.openSession();
		try {
			codeTable = this.getModeByProperty("tablename", codeTalbeName);
			final String tableName = codeTable.getTablename();
			List<CommonCode> codes = getCommonCodes(user, tableName);
			codeTable.setCodeList(codes);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally{
			sqlSession.close();
		}
		return codeTable;
	}
	
	

	/**
	 * 批量保存代码值
	 * @param tableName
	 * @param codes
	 */
	private void batchSaveCommonCodes(String tableName, List<CommonCode> codes){
		log.debug("saving or updating " + className + " instance");
		try {
			//原生SQL也必须按照mybatis规范写
			String newSQL = "insert into "+ tableName +" (id,name,parentID,description,seqno,status,companyID) values (#{id},#{name},#{parentID},#{description}"
					+ ",#{seqno},#{status},#{companyID})";
			String updateSQL = "update "+ tableName +" set name = #{name}, parentID = #{parentID}, description = #{description}, seqno = #{seqno}, "
					+ "status = #{status}, companyID = #{companyID} where id = #{id} ";
			
			ApplicationContext ctx = BaseContext.getApplicationContext();
			
			for(CommonCode code : codes){
				//自动设置
				if(TypeUtils.nullToInt(code.getId()) == 0){
					//自动设置ID时，如果CodeID 等于 0，则是新增的代码，否则不用设置CodeID
					Long sequence = SequenceUtils.getSequence(tableName);
					code.setId(TypeUtils.nullToInt(sequence));
				}
				
				SqlMapper sqlMapper = ctx.getBean("sqlMapper", SqlMapper.class);
				Map<String, Object> parameter = new HashMap<String, Object>();
				if(code.isNew()){
					parameter.put("id", new Integer(code.getId()));
					parameter.put("name", code.getName());
					parameter.put("parentID", new Integer(code.getParentID()));
					parameter.put("description", code.getDescription());
					parameter.put("seqno", new Integer(code.getSeqNo()));
					parameter.put("status", new Integer(code.getStatus()));
					parameter.put("companyID", new Integer(code.getCompanyID()));
					sqlMapper.insert(newSQL, parameter);
				}else{
					parameter.put("name", code.getName());
					parameter.put("parentID", new Integer(code.getParentID()));
					parameter.put("description", code.getDescription());
					parameter.put("seqno", new Integer(code.getSeqNo()));
					parameter.put("status", new Integer(code.getStatus()));
					parameter.put("companyID", new Integer(code.getCompanyID()));
					parameter.put("id", new Integer(code.getId()));
					sqlMapper.update(updateSQL, parameter);
				}
			}
			log.debug("saving or updating successful");
		} catch (RuntimeException re) {
			log.error("saving or updating failed", re);
			throw re;
		}
	}
	
	/**
	 * 通过表名删除系统代码表
	 * 说明：新建系统代码表并保存代码值时，如果错误调用该方法删除表
	 * @param tablename
	 */
	private void dorpErrorCodeTable(String tablename){
		log.debug("drop table " + tablename + " ");
		ApplicationContext ctx = BaseContext.getApplicationContext();
		sqlSession = sqlSessionFactory.openSession();
		try {
			SqlMapper sqlMapper = ctx.getBean("sqlMapper", SqlMapper.class);
			String sql = "drop table "+tablename;
			sqlMapper.update(sql);
			log.debug("drop table successful");
			
		} catch (RuntimeException re) {
			log.error("drop table failed", re);
			throw re;
		} finally{
			sqlSession.close();
		}
	}

	
	/**
	 * 通过表名获取Codes
	 * @param user
	 * @param tableName
	 * @return
	 */
	@Override
	public List<CommonCode> getCommonCodes(User user, String tableName) {
		log.debug("get codes form the "+ tableName+" codeTable");
		ApplicationContext ctx = BaseContext.getApplicationContext();
		List<CommonCode> codeList = new ArrayList();
		sqlSession = sqlSessionFactory.openSession();
		try{
			SqlMapper sqlMapper = ctx.getBean("sqlMapper", SqlMapper.class);
			String sql = "select id,name,parentID,description,seqno,status,companyID from " + tableName + " where companyID = ? order by id asc";
			List<Map<String, Object>> result = sqlMapper.selectList(sql, new Integer(user.getCompanyID()));

			for(Iterator iterator = result.iterator();iterator.hasNext();){
				Object[] values = (Object[]) iterator.next(); 
				CommonCode code = ctx.getBean("CommonCode", CommonCode.class);
				code.setId(TypeUtils.nullToInt(values[0]));
				code.setName(TypeUtils.nullToString(values[1]));
				code.setParentID(TypeUtils.nullToInt(values[2]));
				code.setDescription(TypeUtils.nullToString(values[3]));
				code.setSeqNo(TypeUtils.nullToInt(values[4]));
				code.setStatus(TypeUtils.nullToInt(values[5]));
				code.setCompanyID(TypeUtils.nullToInt(values[6]));
				codeList.add(code);
			}
		} catch(RuntimeException ex){
			log.error("get failed", ex);
			throw ex;
		} finally{
			sqlSession.close();
		}
		return codeList;
	}

	

}
