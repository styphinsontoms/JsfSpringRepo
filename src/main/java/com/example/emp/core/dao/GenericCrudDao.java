package com.example.emp.core.dao;

import java.util.List;
import java.util.Map;

import com.example.dao.entity.AbstractBaseEntity;

public interface GenericCrudDao {

	public <T extends AbstractBaseEntity> T findByPk(Class<?> name,Long recordId) throws Exception;
	public <T extends AbstractBaseEntity> void saveEntity(T entity) throws Exception;
	public <T extends AbstractBaseEntity>  List<T> findAll(Class<?> name) throws Exception;
	//public int bulkUpdate(Class<?> name,Class s,String fieldname,BulkConditions fieldUpdate,List<BulkConditions> conditions) throws Exception;
	public int bulkDelete(Class<?> name,Class s) throws Exception ;
	public <T> T findByPKGeneral(Long key,Class<?> name) throws Exception;	
	public <T extends AbstractBaseEntity> void deleteEntity(Class o,long key) throws Exception;
	
	public <T extends AbstractBaseEntity> List<T> findAllByNativeQuery(String query,
			Map parameters,Class s) throws Exception;
	
	public <T> List<T> findAllByNativeQuery(String query,
			Map parameters) throws Exception;
	
	public <T extends AbstractBaseEntity> T findSingleObjectByNativeQuery(String query,
			Map parameters,Class s)throws Exception;
	
	
	public <T> T findSingleObjectByNativeQuery(String query,
			Map parameters) throws Exception ;
	
	public int getCountByNativeQuery(String sqlString, Map parameters);
	
	public <T> List<T> findPageByNativeQuery(String sqlString, int startIndex,
            int endIndex, Map parameters) throws Exception;
	
	public long getNextSequence(String sequenceName)throws Exception;
	
	public int executeUpdateNativeQuery(String sqlString, Map parameters) throws Exception;
	public Object findByQuery(String sqlString, Map parameters);
	
	public List findAllByQuery(String sqlString, Map parameters)throws Exception;
	public <T> List<T> findAllByNativeQueryWithoutExtendClass(String query,
			Map parameters,Class s) throws Exception;
}
