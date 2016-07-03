package com.example.emp.core.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.entity.AbstractBaseEntity;

@Component("GenericCrudDao")
public class GenericCrudDaoImpl implements GenericCrudDao {

	private static Logger LOG = LoggerFactory
			.getLogger(GenericCrudDaoImpl.class);
	@PersistenceContext
	@Autowired
	private EntityManager em;

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractBaseEntity> T findByPk(Class<?> name,
			Long recordId) throws Exception {
		T t = null;

		try {

			t = (T) em.find(name, recordId);

		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}
		return t;
	}

	@Transactional(rollbackFor = java.lang.Throwable.class)
	public <T extends AbstractBaseEntity> void saveEntity(T entity)
			throws Exception {
		try {
			if (entity.getRecordId() == null) {
				em.persist(entity);
				em.flush();
			} else {
				em.merge(entity);
			}
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}

	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractBaseEntity> List<T> findAll(Class<?> name)
			throws Exception {
		List<T> list;
		try {
			CriteriaQuery<T> cq = (CriteriaQuery<T>) em.getCriteriaBuilder()
					.createQuery(name);
			Query query = em.createQuery(cq);
			list = (List<T>) query.getResultList();
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}

		return list;

	}

	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = java.lang.Throwable.class)
	public int bulkDelete(Class<?> name, Class s) throws Exception {
		int successFlag = 1;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaDelete<Class<?>> delete = cb.createCriteriaDelete(s);
			Root<Class<?>> emp = delete.from(s);

			successFlag = em.createQuery(delete).executeUpdate();
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}

		return successFlag;

	}

	@SuppressWarnings("unchecked")
	public <T> T findByPKGeneral(Long key, Class<?> name) throws Exception {

		T t = null;

		try {
			t = (T) em.find(name, key);
		} catch (Exception ex) {
			LOG.error(ex.toString(), ex);
			throw ex;
		}

		return t;
	}

	@Transactional
	public <T extends AbstractBaseEntity> void deleteEntity(Class o, long key)
			throws Exception {

		try {
			Object obj = em.find(o, key);
			em.remove(obj);
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}

	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractBaseEntity> List<T> findAllByNativeQuery(
			String query, Map parameters, Class s) throws Exception {

		List t = null;
		try {
			Query q = em.createNativeQuery(query, s);

			fillParametersToQuery(q, parameters);
			t = q.getResultList();
		} catch (NoResultException nre) {
			LOG.debug("No Result Found");
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}
		return t;

	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findAllByNativeQuery(String query, Map parameters)
			throws Exception {
		List t = null;

		try {
			Query q = em.createNativeQuery(query);

			fillParametersToQuery(q, parameters);
			t = q.getResultList();
		} catch (NoResultException nre) {
			LOG.debug("No Result Found");
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	protected void fillParametersToQuery(Query query, Map parameters) {
		if (null != parameters) {
			Iterator it = parameters.keySet().iterator();
			while (it.hasNext()) {
				int i = (Integer) it.next();
				query.setParameter(i, parameters.get(i));

			}
		}

	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractBaseEntity> T findSingleObjectByNativeQuery(
			String query, Map parameters, Class s) throws Exception {

		AbstractBaseEntity t = null;
		try {
			Query q = em.createNativeQuery(query, s);
			fillParametersToQuery(q, parameters);
			t = (AbstractBaseEntity) q.getSingleResult();
		} catch (NoResultException nre) {
			LOG.debug("No Result Found");
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}
		return (T) t;

	}

	@SuppressWarnings("unchecked")
	public <T> T findSingleObjectByNativeQuery(String query, Map parameters)
			throws Exception {
		Object t = null;

		try {
			Query q = em.createNativeQuery(query);
			fillParametersToQuery(q, parameters);
			t = q.getSingleResult();
		} catch (NoResultException nre) {
			LOG.debug("No Result Found");
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}
		return (T) t;
	}

	public int getCountByNativeQuery(String sqlString, Map parameters) {
		List result = null;
		Long retNum = null;
		try {
			result = findAllByNativeQuery(sqlString, parameters);
			if (null != result) {
				retNum = (Long) result.get(0);
				return retNum.intValue();
			} else {
				return 0;
			}

		} catch (Exception e) {
			LOG.error(e.toString(), e);
			return 0;

		}

	}

	private String getNativeQueryForCurrentPage(String nativeSqlQueryString,
			int startIndex1, int endIndex) {
		int startIndex = startIndex1;
		if (startIndex != 0) {
			startIndex++;
		}
		nativeSqlQueryString=nativeSqlQueryString+" LIMIT "+startIndex+","+endIndex;
		//Query for Oracle
		/*
		StringBuilder modifiedSqlString = new StringBuilder(
				"SELECT * FROM ( SELECT a.*, ROWNUM rnum FROM ( ");
		modifiedSqlString.append(nativeSqlQueryString);
		modifiedSqlString.append(" ) a WHERE ROWNUM > 0 "
				+ ") WHERE rnum between " + startIndex + " and " + endIndex);
				return modifiedSqlString.toString();*/
		
		return nativeSqlQueryString;

	}

	public <T> List<T> findPageByNativeQuery(String sqlString, int startIndex,
			int endIndex, Map parameters) throws Exception {
		List t = null;
		try {
			String modifiedSqlString = getNativeQueryForCurrentPage(sqlString,
					startIndex, endIndex);
			Query query = em.createNativeQuery(modifiedSqlString);
			fillParametersToQuery(query, parameters);
			t = query.getResultList();
		} catch (NoResultException nre) {
			LOG.debug("No Result Found");
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}
		return t;
	}

	public long getNextSequence(String sequenceName) throws Exception {
		try {
			String trimmedSequenceName = null;
			long nextValue;
			trimmedSequenceName = sequenceName.trim();
			String sqlString = null;
			sqlString = "SELECT " + trimmedSequenceName + ".NEXTVAL FROM DUAL";
			Query query = em.createNativeQuery(sqlString);
			BigDecimal data = (BigDecimal) query.getSingleResult();
			nextValue = data.longValue();
			return nextValue;
		} catch (Exception e) {
			LOG.error("Record ID sequence not found", e);
			throw e;

		}

	}

	@Transactional(rollbackFor = java.lang.Throwable.class)
	public int executeUpdateNativeQuery(String sqlString, Map parameters)
			throws Exception {
		try {
			Query query = em.createNativeQuery(sqlString);
			fillParametersToQuery(query, parameters);
			return query.executeUpdate();
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}
	}

	public Object findByQuery(String sqlString, Map parameters) {
		Query query = em.createQuery(sqlString);
		fillParametersToQuery(query, parameters);
		return query.getSingleResult();
	}

	public List findAllByQuery(String sqlString, Map parameters)
			throws Exception {
		try {
			Query query = em.createNativeQuery(sqlString);
			fillParametersToQuery(query, parameters);
			return query.getResultList();
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}
	}

	public <T> List<T> findAllByNativeQueryWithoutExtendClass(String query,
			Map parameters, Class s) throws Exception {
		List t = null;
		try {
			Query q = em.createNativeQuery(query, s);

			fillParametersToQuery(q, parameters);
			t = q.getResultList();
		} catch (NoResultException nre) {
			LOG.debug("No Result Found");
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}
		return t;
	}

	@PostConstruct
	public void initBean() {
		System.out.println("Initializing Bean" + this.getClass().toString());
	}

	@PreDestroy
	public void destroyBean() {
		System.out.println("Destroying Bean" + this.getClass().toString());
	}
}
