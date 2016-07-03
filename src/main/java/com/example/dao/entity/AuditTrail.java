/* //////////////////////////////////////////////////////////////////////////////////////////////////////////
 // 
 //             Copyright  : 2013 PharmApps, LLC.
 //             All Rights Reserved.
 //
 //             This software is the confidential and proprietary information of PharmApps, LLC.
 //             (Confidential Information).
 // 
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // File Name   :  AuditTrail.java
 // Created By  :  B Kiranmai / 30th Dec 2013
 // Description :  This class defines the entity for AG_AT_TRAIL table.
///////////////////////////////////////////////////////////////////////////////////////////////////////////// */

package com.example.dao.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AG_AT_TRAIL")
public class AuditTrail {
	static final long serialVersionUID = -8714064586454960589L;

	public static final String INSERTED = "I";
	public static final String UPDATED = "U";
	public static final String DELETED = "D";
	public static final String ALPHANUMERIC_DATA = "A";
	public static final String BLOB_DATA = "B";
	public static final String CLOB_DATA = "C";
	public static final String DATE_DATA = "D";
	public static final String NUMERIC_DATA = "N";

	@Id
	@Column(name = "RECORD_ID")
	@GeneratedValue(generator = "ag_seq_id", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "ag_seq_id", sequenceName = "SEQ_RECORD_ID", allocationSize = 1)
	private Long recordId;

	@Column(name = "APP_RECORD_ID", nullable = false)
	protected String appRecordId;

	@Column(name = "SEQ_NO", nullable = false)
	protected Long seqNo;

	@Column(name = "OPERATION_TYPE", nullable = false)
	protected String operationType;

	@Column(name = "TABLE_ID", nullable = false)
	protected String tableId;

	@Column(name = "FIELD_ID", nullable = false)
	protected String fieldId;

	@Column(name = "OLD_VALUE")
	protected char[] oldValue;

	@Column(name = "NEW_VALUE")
	protected char[] newValue;

	@Column(name = "OLD_BLOB_VALUE")
	protected byte[] oldBlobValue;

	@Column(name = "NEW_BLOB_VALUE")
	protected byte[] newBlobValue;

	@Column(name = "REASON_CODE")
	protected String reasonCode;

	@Column(name = "REASON")
	protected String reason;

	@Column(name = "FIELD_TYPE")
	protected String fieldType;

	@Column(name = "FK_AT_SESSION_REC_ID")
	protected Long auditSessionRecId;

	/**
	 * This is a constructor
	 */
	public AuditTrail() {
		recordId = null;
		seqNo = null;
		operationType = null;
		tableId = null;
		fieldId = null;
		oldValue = null;
		newValue = null;
		reason = null;
		reasonCode = null;
		auditSessionRecId = null;
		fieldType = "A";
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getAppRecordId() {
		return appRecordId;
	}

	public void setAppRecordId(String appRecordId) {
		this.appRecordId = appRecordId;
	}

	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public char[] getOldValue() {
		return oldValue;
	}

	public void setOldValue(Object oldValue) {
		if (oldValue != null) {
			if ((oldValue instanceof byte[]) || (oldValue instanceof Byte[])) {
				oldBlobValue = (byte[]) oldValue;
			} else if ((oldValue instanceof char[])
					|| (oldValue instanceof Character[])) {
				this.oldValue = (char[]) oldValue;
			} else {
				this.oldValue = oldValue.toString().toCharArray();
			}
		}
	}

	public char[] getNewValue() {
		return newValue;
	}

	public void setNewValue(Object newValue) {
		if (newValue != null) {
			if ((newValue instanceof byte[]) || (newValue instanceof Byte[])) {
				newBlobValue = (byte[]) newValue;
				this.setFieldType("B");
			} else if ((newValue instanceof char[])
					|| (newValue instanceof Character[])) {
				this.newValue = (char[]) newValue;
				this.setFieldType("C");
			} else {
				this.newValue = newValue.toString().toCharArray();
				this.setFieldType("A");
			}
		}
	}

	public byte[] getOldBlobValue() {
		return oldBlobValue;
	}

	public void setOldBlobValue(byte[] oldBlobValue) {
		this.oldBlobValue = oldBlobValue;
	}

	public byte[] getNewBlobValue() {
		return newBlobValue;
	}

	public void setNewBlobValue(byte[] newBlobValue) {
		this.newBlobValue = newBlobValue;
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

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		if (ALPHANUMERIC_DATA.equals(fieldType) || BLOB_DATA.equals(fieldType)
				|| CLOB_DATA.equals(fieldType)) {
			this.fieldType = fieldType;
		}
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		if (INSERTED.equals(operationType) || UPDATED.equals(operationType)
				|| DELETED.equals(operationType)) {
			this.operationType = operationType;
		}
	}

	public Long getAuditSessionRecId() {
		return auditSessionRecId;
	}

	public void setAuditSessionRecId(Long auditSessionRecId) {
		this.auditSessionRecId = auditSessionRecId;
	}

	@Override
	public String toString() {
		return "AuditTrail [recordId=" + recordId + ", appRecordId="
				+ appRecordId + ", seqNo=" + seqNo + ", operationType="
				+ operationType + ", tableId=" + tableId + ", fieldId="
				+ fieldId + ", oldValue=" + Arrays.toString(oldValue)
				+ ", newValue=" + Arrays.toString(newValue) + ", oldBlobValue="
				+ Arrays.toString(oldBlobValue) + ", newBlobValue="
				+ Arrays.toString(newBlobValue) + ", reasonCode=" + reasonCode
				+ ", reason=" + reason + ", fieldType=" + fieldType
				+ ", auditSessionRecId=" + auditSessionRecId + "]";
	}

	
	
}
