package com.example.dao.entity.listener;

import java.util.Date;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.example.dao.entity.AbstractBaseEntity;

public class PersistenceContextListener   {
	@PrePersist
	public void prePersist(Object object) {

		AbstractBaseEntity genericEntity = (AbstractBaseEntity) object;
		genericEntity.setDateModified(new Date());
		genericEntity.setUserModified(Thread.currentThread().getName());
		System.out
				.println("PersistenceContextListener :: Method PrePersist Invoked Upon Entity :: "
						+ object);
	}

	@PostPersist
	public void postPersist(Object object) {
		System.out
				.println("PersistenceContextListener :: Method PostPersist Invoked Upon Entity :: "
						+ object);
	}

	@PreRemove
	public void PreRemove(Object object) {
		System.out
				.println("PersistenceContextListener :: Method PreRemove Invoked Upon Entity :: "
						+ object);

	}

	@PostRemove
	public void PostRemove(Object object) {

		System.out
				.println("PersistenceContextListener :: Method PostRemove Invoked Upon Entity :: "
						+ object);

	}

	@PreUpdate
	public void PreUpdate(Object object) {
		System.out
				.println("PersistenceContextListener :: Method PreUpdate Invoked Upon Entity :: "
						+ object);
	}

	@PostUpdate
	public void PostUpdate(Object object) {

		System.out
				.println("PersistenceContextListener :: Method PostUpdate Invoked Upon Entity :: "
						+ object);

	}

}
