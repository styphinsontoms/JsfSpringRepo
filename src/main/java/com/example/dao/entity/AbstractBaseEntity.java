package com.example.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.dao.entity.listener.AuditListener;
import com.example.dao.entity.listener.PersistenceContextListener;

@MappedSuperclass
public abstract class AbstractBaseEntity extends AuditListener implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4579657857482622372L;

	public static int ENTITY_WITH_MODIFIED_FIELDS = 1;

	@Temporal(TemporalType.DATE)
	private Date dateModified;

	private String userModified;

	private String moduleId;
	
	private Long recordId;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getUserModified() {
		return userModified;
	}

	public void setUserModified(String userModified) {
		this.userModified = userModified;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Override
	public String toString() {
		return "AbstractBaseEntity [dateModified=" + dateModified
				+ ", userModified=" + userModified + ", recordId=" + recordId
				+ "]";
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
