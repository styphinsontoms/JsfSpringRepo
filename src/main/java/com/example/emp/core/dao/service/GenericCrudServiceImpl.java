package com.example.emp.core.dao.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.dom4j.tree.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.entity.AbstractBaseEntity;
import com.example.emp.core.dao.GenericCrudDao;

@Component
public class GenericCrudServiceImpl implements GenericCrudService {

	private static Logger LOG = LoggerFactory
			.getLogger(GenericCrudServiceImpl.class);

	@Autowired
	private GenericCrudDao genericCrudDao;

	/**
	 * @param genericCrudDao
	 *            the genericCrudDao to set
	 */
	public void setGenericCrudDao(GenericCrudDao genericCrudDao) {
		this.genericCrudDao = genericCrudDao;
	}

	/**
	 * @return the genericCrudDao
	 * 
	 */
	public GenericCrudDao getGenericCrudDao() {
		return genericCrudDao;
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractBaseEntity> T findByPk(Class<?> name,
			Long recordId) throws Exception {
		return (T) genericCrudDao.findByPk(name, recordId);
		// return null;
	}

	@Transactional
	public <T extends AbstractBaseEntity> void saveEntity(T entity)
			throws Exception {
		genericCrudDao.saveEntity(entity);

	}

	public <T extends AbstractBaseEntity> List<T> findAll(Class<?> name)
			throws Exception {

		return (List<T>) genericCrudDao.findAll(name);
	}

	public int bulkDelete(Class<?> name, Class s) throws Exception {

		return genericCrudDao.bulkDelete(name, s);
	}

	@SuppressWarnings("unchecked")
	public <T> T findByPKGeneral(Long key, Class<?> name) throws Exception {
		return (T) genericCrudDao.findByPKGeneral(key, name);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findAllByNativeQuery(String query, Map parameters)
			throws Exception {

		return genericCrudDao.findAllByNativeQuery(query, parameters);
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractBaseEntity> T findSingleObjectByNativeQuery(
			String query, Map parameters, Class s) throws Exception {

		return (T) genericCrudDao.findSingleObjectByNativeQuery(query,
				parameters, s);
	}

	@SuppressWarnings("unchecked")
	public <T> T findSingleObjectByNativeQuery(String query, Map parameters)
			throws Exception {

		return (T) genericCrudDao.findSingleObjectByNativeQuery(query,
				parameters);

	}

	public int getCountByNativeQuery(String sqlString, Map parameters) {
		return genericCrudDao.getCountByNativeQuery(sqlString, parameters);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findAllByNativeQueryPerPage(String query, int first,
			int pageSize, Map parameters) throws Exception {
		try {
			/*String modifiedSqlString = getNativeQueryForCurrentPage(query,
					first, pageSize);*/
			return genericCrudDao.findPageByNativeQuery(query, first, pageSize,
					parameters);
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}
	}

	private String getNativeQueryForCurrentPage(String nativeSqlQueryString,
			int startIndex1, int endIndex) {
		int startIndex = startIndex1;
		if(startIndex!=0){
			startIndex++;
		}
		StringBuilder modifiedSqlString = new StringBuilder(
				"SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM ( ");
		modifiedSqlString.append(nativeSqlQueryString);
		modifiedSqlString.append(" ) a WHERE ROWNUM > 0 " 
				+ ") WHERE rnum between "+startIndex+" and "+endIndex);
		return modifiedSqlString.toString();

	}
	public long getNextSequence(String sequenceName) throws Exception {
		try {
			return genericCrudDao.getNextSequence(sequenceName);
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}
	}

	public int executeUpdateNativeQuery(String sqlString, Map parameters)
			throws Exception {
		try {
			return genericCrudDao.executeUpdateNativeQuery(sqlString,
					parameters);
		} catch (Exception e) {
			// LOGGER.error(e.toString(), e);
			throw e;
		}

	}

	public Object findByQuery(String sqlString, Map parameters)
			throws Exception {
		try {
			return genericCrudDao.findByQuery(sqlString, parameters);
		} catch (Exception e) {
			// LOGGER.error(e.toString(), e);
			throw e;
		}
	}

	public List findAllByQuery(String sqlString, Map parameters)
			throws Exception {
		try {
			return genericCrudDao.findAllByQuery(sqlString, parameters);
		} catch (Exception e) {
			// LOGGER.error(e.toString(), e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findAllByNativeQueryWithoutExtendClass(String query,
			Map parameters, Class c) throws Exception {

		return genericCrudDao.findAllByNativeQueryWithoutExtendClass(query,
				parameters, c);
	}

	@PostConstruct
	public void initBean() {
		System.out.println("Initializing Bean" + this.getClass().toString());
	}

	@PreDestroy
	public void destroyBean() {
		System.out.println("Destroying Bean" + this.getClass().toString());

	}

	@Override
	public <T extends AbstractEntity> void saveEntity(T entity)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends AbstractEntity> void deleteEntity(Class o, long key)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends AbstractEntity> List<T> findAllByNativeQuery(
			String query, Map parameters, Class s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends AbstractEntity> T findSingleObjectByNativeQuery(
			String query, Map parameters, Class s) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
