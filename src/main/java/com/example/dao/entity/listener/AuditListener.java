package com.example.dao.entity.listener;

import javax.persistence.Table;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.descriptors.DescriptorEvent;
import org.eclipse.persistence.descriptors.DescriptorEventAdapter;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.queries.InsertObjectQuery;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventListener;
import org.eclipse.persistence.sessions.SessionEventManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.dao.entity.AbstractBaseEntity;
import com.example.dao.entity.AuditTrail;

public class AuditListener extends DescriptorEventAdapter implements
		SessionEventListener, SessionCustomizer {

	public static long orderCount = 0;
	public static final String INSERT = "I";
	public static final String UPDATE = "U";
	public static final String DELETE = "D";
	public static final String USERID = "SYSTEM";
	public static final String MODULE_ID = "SEC901";
	public static ThreadLocal<String> transactionSessionThread = new ThreadLocal<String>();
	public static ThreadLocal<Long> auditSessionId = new ThreadLocal<Long>();

	@Override
	public void aboutToDelete(DescriptorEvent event) {
		super.aboutToDelete(event);
		System.out.println("order count is : " + (++orderCount));
		LOG.info("Auditing for aboutToDelete Operation for Thread"
				+ Thread.currentThread().getName() + " With Session "
				+ transactionSessionThread.get());

		AbstractBaseEntity genericAuditEntity = (AbstractBaseEntity) event
				.getObject();
		System.out.println("genericAuditEntity" + genericAuditEntity);
	}

	/*
	 * @Override public void aboutToInsert(DescriptorEvent event) {
	 * super.aboutToInsert(event); System.out.println("order count is : " +
	 * (++orderCount)); LOG.info("Auditing for aboutToInsert Operation" +
	 * Thread.currentThread().getName() + " With Session " +
	 * transactionSessionThread.get());
	 * 
	 * AbstractBaseEntity genericAuditEntity = (AbstractBaseEntity) event
	 * .getObject(); System.out.println("genericAuditEntity" +
	 * genericAuditEntity); }
	 */

	@Override
	public void aboutToUpdate(DescriptorEvent event) {
		super.aboutToUpdate(event);
		System.out.println("order count is : " + (++orderCount));
		LOG.info("Auditing for aboutToUpdate Operation"
				+ Thread.currentThread().getName() + " With Session "
				+ transactionSessionThread.get());

		AbstractBaseEntity genericAuditEntity = (AbstractBaseEntity) event
				.getObject();
		System.out.println("genericAuditEntity" + genericAuditEntity);
	}

	private static Logger LOG = LoggerFactory.getLogger(AuditListener.class);

	public void postInsert(DescriptorEvent event) {
		System.out.println("order count is : " + (++orderCount));
		LOG.info("Auditing for postInsert Operation"
				+ Thread.currentThread().getName() + " With Session "
				+ transactionSessionThread.get());

		AbstractBaseEntity abstractBaseEntity = (AbstractBaseEntity) event
				.getObject();
		System.out.println("genericAuditEntity" + abstractBaseEntity);
		event.getSession().insertObject(abstractBaseEntity);

		InsertObjectQuery query = (InsertObjectQuery) event.getQuery();

		if (transactionSessionThread.get() != null
				&& transactionSessionThread.get().equalsIgnoreCase(
						Thread.currentThread().getName())) {
			AuditSession sessionObj = new AuditSession(
					abstractBaseEntity.getUserModified(),
					abstractBaseEntity.getModuleId(), INSERT, "", INSERT);
			/*AuditSession obj = (AuditSession) event.getSession().insertObject(
					sessionObj);*/
			//auditSessionId.set(obj.getRecordId());
			System.out.println("Audit Session Data inserted is : "
					+ auditSessionId.get());
			transactionSessionThread.remove();

		}

		long count = 1L;
		for (int i = 0; i < query.getModifyRow().getFields().size(); i++) {

			DatabaseField dbField = (DatabaseField) query.getModifyRow()
					.getFields().elementAt(i);
			if (dbField == null) {
				continue;
			}
			String fieldName = dbField.getName();

			if (query.getModifyRow().getValues(fieldName) != null
					&& !"".equals(query.getModifyRow().getValues(fieldName))) {

				AuditTrail auditTrail = new AuditTrail();
				if (abstractBaseEntity.getClass().getAnnotation(Table.class) != null) {
					auditTrail.setTableId(abstractBaseEntity.getClass()
							.getAnnotation(Table.class).name());
				} else {
					auditTrail.setTableId(abstractBaseEntity.getClass()
							.getSimpleName());
				}

				auditTrail.setOperationType(INSERT);

				auditTrail.setFieldId(fieldName);
				auditTrail.setAppRecordId(String.valueOf(abstractBaseEntity
						.getRecordId()));

				auditTrail.setNewValue(query.getModifyRow()
						.getValues(fieldName));
				auditTrail.setOldValue(null);

				auditTrail.setSeqNo(count);
				auditTrail.setAuditSessionRecId(auditSessionId.get());

				event.getSession().insertObject(auditTrail);
				count++;
			}

		}

	}

	public void postUpdate(DescriptorEvent event) {
		System.out.println("order count is : " + (++orderCount));
		LOG.info("Auditing for postUpdate Operation"
				+ Thread.currentThread().getName() + " With Session "
				+ transactionSessionThread.get());
		AbstractBaseEntity genericAuditEntity = (AbstractBaseEntity) event
				.getObject();

		System.out.println("genericAuditEntity" + genericAuditEntity);
		event.getSession().insertObject(genericAuditEntity);
	}

	public void postMerge(DescriptorEvent event) {
		System.out.println("order count is : " + (++orderCount));
		LOG.info("Auditing for postMerge Operation"
				+ Thread.currentThread().getName() + " With Session "
				+ transactionSessionThread.get());

		AbstractBaseEntity genericAuditEntity = (AbstractBaseEntity) event
				.getObject();
		System.out.println("genericAuditEntity" + genericAuditEntity);
		event.getSession().insertObject(genericAuditEntity);
	}

	public void postDelete(DescriptorEvent event) {
		System.out.println("order count is : " + (++orderCount));
		LOG.info("Auditing for Delete Operation"
				+ Thread.currentThread().getName() + " With Session "
				+ transactionSessionThread.get());

		AbstractBaseEntity genericAuditEntity = (AbstractBaseEntity) event
				.getObject();
		System.out.println("genericAuditEntity" + genericAuditEntity);
		event.getSession().insertObject(genericAuditEntity);
	}

	/** This will audit a specific class. */
	public void customize(ClassDescriptor descriptor) {
		System.out.println("Adding object to listener" + this);
		descriptor.getEventManager().addListener(this);
	}

	/** This will audit all classes. */
	@Override
	public void customize(Session session) {
		System.out.println("order count is : " + (++orderCount));
		// session.setName(Thread.currentThread().getName());
		System.out.println("<<<<<<<< Customized sessioon has name  >>>>>>>>>"
				+ session.getName());
		for (ClassDescriptor descriptor : session.getDescriptors().values()) {
			customize(descriptor);
		}
	}

	@Override
	public void preBeginTransaction(SessionEvent event) {
		System.out.println("order count is : " + (++orderCount));
		transactionSessionThread.set(Thread.currentThread().getName());
		LOG.info("<<<<<< Db Transaction for Current User Begins here >>>>>"
				+ event.getSession());
		System.out.println("descripttors List "
				+ event.getSession().getDescriptors());
		System.out.println("genericAuditEntity ");

	}

	// every DescriptorEvent event has only one audit listener.
	private AuditListener getSessionAuditListener(DescriptorEvent event) {
		System.out.println("order count is : " + (++orderCount));
		System.out.println("<<<<< Session namem is >>>> :"
				+ event.getSession().getName());
		SessionEventManager eventManager = event.getSession().getEventManager();
		if (null != eventManager && eventManager.hasListeners()) {
			return (AuditListener) eventManager.getListeners().get(0);
		}

		return null;
	}

	public void preInsert(DescriptorEvent event) {

		System.out.println("order count is : " + (++orderCount));
		LOG.info("Auditing for preInsert Operation"
				+ Thread.currentThread().getName() + " With Session "
				+ transactionSessionThread.get());

		/*
		 * AbstractBaseEntity genericAuditEntity = (AbstractBaseEntity) event
		 * .getObject(); System.out.println("genericAuditEntity " +
		 * genericAuditEntity);
		 */

	}

	public void preUpdateWithChanges(DescriptorEvent event) {
		System.out.println("order count is : " + (++orderCount));
		LOG.info("Auditing for preUpdateWithChanges Operation"
				+ Thread.currentThread().getName() + " With Session "
				+ transactionSessionThread.get());

		AbstractBaseEntity genericAuditEntity = (AbstractBaseEntity) event
				.getObject();
		System.out.println("genericAuditEntity" + genericAuditEntity);
	}

	@Override
	public void missingDescriptor(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moreRowsDetected(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void noRowsModified(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void outputParametersDetected(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postAcquireClientSession(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postAcquireConnection(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postAcquireExclusiveConnection(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postAcquireUnitOfWork(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postBeginTransaction(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postCalculateUnitOfWorkChangeSet(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postCommitTransaction(SessionEvent arg0) {
		System.out.println("order count is : " + (++orderCount));
		orderCount = 0;
		System.out.println("Transaction committed successfully ");

	}

	@Override
	public void postCommitUnitOfWork(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postConnect(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postDistributedMergeUnitOfWorkChangeSet(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postExecuteQuery(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postLogin(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postLogout(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postMergeUnitOfWorkChangeSet(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postReleaseClientSession(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postReleaseUnitOfWork(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postResumeUnitOfWork(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postRollbackTransaction(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preCalculateUnitOfWorkChangeSet(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preCommitTransaction(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preCommitUnitOfWork(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preDistributedMergeUnitOfWorkChangeSet(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preExecuteQuery(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preLogin(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preLogout(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preMergeUnitOfWorkChangeSet(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preReleaseClientSession(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preReleaseConnection(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preReleaseExclusiveConnection(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preReleaseUnitOfWork(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preRollbackTransaction(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void prepareUnitOfWork(SessionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
