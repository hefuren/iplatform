package com.bluesky.iplatform.component.codetable.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import com.bluesky.iplatform.commons.db.mybatis.SqlMapper;
import com.bluesky.iplatform.commons.db.mybatis.dao.impl.BaseSingleMyBatisDAOImpl;
import com.bluesky.iplatform.commons.db.mybatis.utils.AllMapper;
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
		try {
			//动态更新CodeTable Sequence Cache
			SequenceUtils.dynamicUpdateSequenceCache(codeTable.getTablename());
			//插入主表
			Mapper<CodeTable> mapper = this.getMapper(sqlSession, mapperType);
			mapper.insert(codeTable);
			//插入子表
			Set<CodeTableField> fields = codeTable.getCodeTableFields();
			AllMapper<CodeTableField> fieldMap = (AllMapper<CodeTableField>) sqlSession.getMapper(CodeTableFieldMapper.class);
			List<CodeTableField> modes = new ArrayList<CodeTableField>();
			modes.addAll(fields);
			fieldMap.insertList(modes);
//			for (CodeTableField t : fields) {
//				// 设置循环批量插入数据
//				fieldMap.insert(t);
//			}
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
		} 
	}
	
	/**
	 * 保存CodeTable
	 * @param user
	 * @param t
	 */
	@Override
	public void updateMode(User user, CodeTable codeTable) {
		log.debug("updating " + className + " instance");
		try {
			//更新主表
			Mapper<CodeTable> mapper = this.getMapper(sqlSession, mapperType);
			mapper.updateByPrimaryKey(codeTable);
			//更新CommonCode
			//插入代码值
			final String codeTableName = codeTable.getTablename();
			final List codeList = codeTable.getCodeList();
			batchSaveCommonCodes(codeTableName, codeList);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}	
	}
	
	
	@Override
	public void deleteMode(User user, CodeTable codeTable) {
		log.debug("deleting " + className + " instance");
		try {
			ApplicationContext ctx = BaseContext.getApplicationContext();
			//删除st_codetable , st_codetablefield主表和子表的语句
			Mapper<CodeTableField> fieldMap =  sqlSession.getMapper(CodeTableFieldMapper.class);
			Example example = new Example(CodeTableField.class);
			example.createCriteria().andEqualTo("tableID", new Integer(codeTable.getId()));
			fieldMap.deleteByExample(example);
			
			Mapper<CodeTable> mapper = this.getMapper(sqlSession, mapperType);
			mapper.delete(codeTable);
			
			SqlMapper sqlMapper = ctx.getBean("sqlMapper", SqlMapper.class);
			String tableName = codeTable.getTablename();
			String sql = "drop table " + tableName +"";
			sqlMapper.update(sql);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
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
		try {
			Mapper<CodeTable> mapper = this.getMapper(sqlSession, mapperType);
			List<CodeTable> tables = this.getModes(user, ids);
			Map<Integer,CodeTable> tableMaps = new HashMap();
			for(CodeTable table : tables){
				tableMaps.put(new Integer(table.getId()), table);
			}
			
			// 循环批量删除数据
			List<Integer> idsList = new ArrayList<Integer>();
			for(int tableID : ids){
				idsList.add(new Integer(tableID));
			}
			//删除st_codetablefield 子表
			Example example_1 = new Example(CodeTableField.class);
			example_1.createCriteria().andIn("tableID", idsList);
			Mapper<CodeTableField> fieldMap =  sqlSession.getMapper(CodeTableFieldMapper.class);
			fieldMap.deleteByExample(example_1);
			
			//删除st_codetable主表
			Example example_2 = new Example(CodeTable.class);
			example_2.createCriteria().andIn("id", idsList);
			mapper.deleteByExample(example_2);
			
			//删除codeTable
			SqlMapper sqlMapper = ctx.getBean("sqlMapper", SqlMapper.class);
			for (int id : ids) {
				CodeTable table = tableMaps.get(new Integer(id));
				String tableName = TypeUtils.nullToString(table.getTablename());
				String sql = "drop table " + tableName +"";
				sqlMapper.update(sql);
			}
			log.debug("batch delete successful");
		} catch (RuntimeException re) {
			log.error("batch delete failed", re);
			throw re;
		} 
	}
	
	@Override
	public CodeTable getMode(User user, int id) {
		log.debug("getting " + className + " instance with id: " + id);
		CodeTable codeTable = null;
		try {
			Mapper<CodeTable> mapper = this.getMapper(sqlSession, mapperType);
			codeTable = mapper.selectByPrimaryKey(id);
			if(codeTable != null){
				String tableName = codeTable.getTablename();
				User tempUser = user;
				List<CommonCode> codesList = getCommonCodes(tempUser, tableName);
				codeTable.setCodeList(codesList);
			}
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} 
		return codeTable;
	}
	
	@Override
	public CodeTable getCodeTalbe(User user, String codeTalbeName) {
		log.debug("getting " + className + " instance with Name: " + codeTalbeName);
		CodeTable codeTable = null;
		try {
			codeTable = this.getModeByProperty("tablename", codeTalbeName);
			final String tableName = codeTable.getTablename();
			List<CommonCode> codes = getCommonCodes(user, tableName);
			codeTable.setCodeList(codes);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
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
			
			String deleteSQL = "delete from " + tableName + " a where a.id =  #{id} and a.companyID = #{companyID}";
			
			ApplicationContext ctx = BaseContext.getApplicationContext();
			for(CommonCode code : codes){
				//自动设置
				if(TypeUtils.nullToInt(code.getId()) == 0){
					//自动设置ID时，如果CodeID 等于 0，则是新增的代码，否则不用设置CodeID
					Long sequence = SequenceUtils.getSequence(tableName);
					code.setId(TypeUtils.nullToInt(sequence));
				}
				//如果性能慢，按Mybatis动态SQL，组织批量操作的SQL
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
				}else if(code.isModified()){
					parameter.put("name", code.getName());
					parameter.put("parentID", new Integer(code.getParentID()));
					parameter.put("description", code.getDescription());
					parameter.put("seqno", new Integer(code.getSeqNo()));
					parameter.put("status", new Integer(code.getStatus()));
					parameter.put("companyID", new Integer(code.getCompanyID()));
					parameter.put("id", new Integer(code.getId()));
					sqlMapper.update(updateSQL, parameter);
				}else if(code.isDeleted()){
					parameter.put("id", new Integer(code.getId()));
					parameter.put("companyID", new Integer(code.getCompanyID()));
					sqlMapper.delete(deleteSQL, parameter);
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
		try {
			SqlMapper sqlMapper = ctx.getBean("sqlMapper", SqlMapper.class);
			String sql = "drop table "+tablename;
			sqlMapper.update(sql);
			log.debug("drop table successful");
			
		} catch (RuntimeException re) {
			log.error("drop table failed", re);
			throw re;
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
		try{
			SqlMapper sqlMapper = ctx.getBean("sqlMapper", SqlMapper.class);
			String sql = "select id,name,parentID,description,seqno,status,companyID from " + tableName + " where companyID = #{companyID} order by id asc";
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("companyID", new Integer(user.getCompanyID()));
			List<Map<String, Object>> result = sqlMapper.selectList(sql, parameter);

			
			for(Iterator iterator = result.iterator();iterator.hasNext();){
				Map<String,Object> values = (Map<String,Object>) iterator.next(); 
				CommonCode code = ctx.getBean("CommonCode", CommonCode.class);
				code.setId(TypeUtils.nullToInt(values.get("id")));
				code.setName(TypeUtils.nullToString(values.get("name")));
				code.setParentID(TypeUtils.nullToInt(values.get("parentid")));
				code.setDescription(TypeUtils.nullToString(values.get("description")));
				code.setSeqNo(TypeUtils.nullToInt(values.get("seqno")));
				code.setStatus(TypeUtils.nullToInt(values.get("status")));
				code.setCompanyID(TypeUtils.nullToInt(values.get("companyid")));
				codeList.add(code);
			}
		} catch(RuntimeException ex){
			log.error("get failed", ex);
			throw ex;
		} 
		return codeList;
	}

	

}
