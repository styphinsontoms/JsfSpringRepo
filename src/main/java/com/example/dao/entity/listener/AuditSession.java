/* //////////////////////////////////////////////////////////////////////////////////////////////////////////
 // 
 //             Copyright  : 2013 PharmApps, LLC.
 //             All Rights Reserved.
 //
 //             This software is the confidential and proprietary information of PharmApps, LLC.
 //             (Confidential Information).
 // 
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // File Name   :  AuditSession.java
 // Created By  :  B Kiranmai / 30th Dec 2013
 // Description :  This class defines the entity for AG_AT_SESSION table.
///////////////////////////////////////////////////////////////////////////////////////////////////////////// */

package com.example.dao.entity.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.example.dao.entity.AuditTrail;

@Entity
@Table(name = "AG_AT_SESSION")
public class AuditSession {
	static final long serialVersionUID = 0xf7f44521e5b09e64L;

	@Id
	@Column(name = "RECORD_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @GeneratedValue(generator = "ag_seq_id", strategy =
	// GenerationType.SEQUENCE)
	// @SequenceGenerator(name = "ag_seq_id", sequenceName = "SEQ_RECORD_ID",
	// allocationSize = 1)
	private Long recordId;

	@Column(name = "APP_ID", nullable = false)
	protected String appId;

	@Column(name = "DATE_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

	@Column(name = "USER_ID", nullable = false)
	private String userId;

	@Column(name = "MODULE_ID")
	private String moduleId;

	@Column(name = "OPERATION_TYPE", nullable = false)
	private String operationType;

	@Column(name = "REASON_CODE")
	private String reasonCode;

	@Column(name = "REASON")
	private String reason;

	@Column(name = "APP_DATA1")
	private String appData1;

	@Column(name = "APP_DATA2")
	private String appData2;

	@Column(name = "APP_DATA3")
	private String appData3;

	@Column(name = "APP_DATA4")
	private String appData4;

	@Column(name = "APP_DATA5")
	private String appData5;

	@Column(name = "APP_DATA6")
	private String appData6;

	@Transient
	private List auditSessionAppDataList;

	@Transient
	private long seqNo;

	public AuditSession(String userId, String moduleId, String operationType,
			String reasonCode, String reason) {
		super();
		dateTime = new Date();
		this.userId = userId;
		this.moduleId = moduleId;
		this.operationType = operationType;
		this.reasonCode = reasonCode;
		this.reason = reason;
		appData1 = null;
		appData2 = null;
		appData3 = null;
		appData4 = null;
		appData5 = null;
		appData6 = null;
		auditSessionAppDataList = new ArrayList();
		seqNo = 0L;
		appId = "AGX";
	}

	public AuditSession() {
		recordId = null;
		dateTime = new Date();
		userId = "ADMINISTRATOR";
		moduleId = null;
		operationType = null;
		reasonCode = null;
		reason = null;
		appData1 = null;
		appData2 = null;
		appData3 = null;
		appData4 = null;
		appData5 = null;
		appData6 = null;
		auditSessionAppDataList = new ArrayList();
		seqNo = 0L;
		appId = "AGX";
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAppData1() {
		return appData1;
	}

	public void setAppData1(String appData1) {
		this.appData1 = appData1;
	}

	public String getAppData2() {
		return appData2;
	}

	public void setAppData2(String appData2) {
		this.appData2 = appData2;
	}

	public String getAppData3() {
		return appData3;
	}

	public void setAppData3(String appData3) {
		this.appData3 = appData3;
	}

	public String getAppData4() {
		return appData4;
	}

	public void setAppData4(String appData4) {
		this.appData4 = appData4;
	}

	public String getAppData5() {
		return appData5;
	}

	public void setAppData5(String appData5) {
		this.appData5 = appData5;
	}

	public String getAppData6() {
		return appData6;
	}

	public void setAppData6(String appData6) {
		this.appData6 = appData6;
	}

	public List<AuditTrail> getAuditSessionAppDataList() {
		return auditSessionAppDataList;
	}

	public void setAuditSessionAppDataList(List auditSessionAppDataList) {
		this.auditSessionAppDataList = auditSessionAppDataList;
	}

	public long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}

	@Override
	public String toString() {
		return "AuditSession [recordId=" + recordId + ", appId=" + appId
				+ ", dateTime=" + dateTime + ", userId=" + userId
				+ ", moduleId=" + moduleId + ", operationType=" + operationType
				+ ", reasonCode=" + reasonCode + ", reason=" + reason
				+ ", appData1=" + appData1 + ", appData2=" + appData2
				+ ", appData3=" + appData3 + ", appData4=" + appData4
				+ ", appData5=" + appData5 + ", appData6=" + appData6
				+ ", auditSessionAppDataList=" + auditSessionAppDataList
				+ ", seqNo=" + seqNo + "]";
	}

}
