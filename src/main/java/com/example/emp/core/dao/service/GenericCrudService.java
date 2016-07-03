package com.example.emp.core.dao.service;

import java.util.List;
import java.util.Map;

import org.dom4j.tree.AbstractEntity;

import com.example.dao.entity.AbstractBaseEntity;

public interface GenericCrudService {

	public <T extends AbstractBaseEntity> void saveEntity(T entity)
			throws Exception;

	public <T extends AbstractBaseEntity> T findByPk(Class<?> name,
			Long recordId) throws Exception;
	public <T extends AbstractEntity> void saveEntity(T entity)
			throws Exception;

	public <T extends AbstractBaseEntity> List<T> findAll(Class<?> name)throws Exception;
	
	public int bulkDelete(Class<?> name, Class s) throws Exception;

	public <T> T findByPKGeneral(Long key, Class<?> name) throws Exception;

	public <T extends AbstractEntity> void deleteEntity(Class o, long key)
			throws Exception;

	public <T extends AbstractEntity> List<T> findAllByNativeQuery(
			String query, Map parameters, Class s) throws Exception;

	public <T extends AbstractEntity> T findSingleObjectByNativeQuery(
			String query, Map parameters, Class s) throws Exception;

	public <T> T findSingleObjectByNativeQuery(String query, Map parameters)
			throws Exception;

	public int getCountByNativeQuery(String sqlString, Map parameters);

	public <T> List<T> findAllByNativeQueryPerPage(String query, int first,
			int pageSize, Map parameters) throws Exception;

	public long getNextSequence(String sequenceName) throws Exception;

	public int executeUpdateNativeQuery(String sqlString, Map parameters)
			throws Exception;

	public Object findByQuery(String query, Map parameters) throws Exception;


	public <T> List<T> findAllByNativeQueryWithoutExtendClass(String query,
			Map parameters, Class c) throws Exception;
}
