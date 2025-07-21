package com.hrmsdashboard.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gems_transactions")
public class GemsDashboardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TRANSACTION_ID", nullable = false, length = 50)
	private String transactionId;

	@Column(name = "GE_NUMBER", nullable = false, length = 8)
	private String geNumber;

	@Column(name = "EVENT_ID", nullable = false, length = 20)
	private String eventId;

	@Column(name = "EVENT_NAME", nullable = false, length = 50)
	private String eventName;

	@Column(name = "FILE_ID", nullable = false, length = 30)
	private String fileId;

	@Column(name = "PDF_FILE_NAME", length = 50)
	private String pdfFileName;

	@Column(name = "AS_ON_DATE")
	private LocalDate asOnDate;

	@Column(name = "JSON_SENT", length = 1)
	private String jsonSent = "N";

	@Column(name = "PDF_SENT", length = 1)
	private String pdfSent = "N";

	@Column(name = "JSON_SENT_DATE")
	private LocalDate jsonSentDate;

	@Column(name = "HRMS_STATUS")
	private Integer hrmsStatus;

	@Column(name = "DDO_COMMENTS", length = 255)
	private String ddoComments;

	@Column(name = "CREATED_AT", updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getGeNumber() {
		return geNumber;
	}

	public void setGeNumber(String geNumber) {
		this.geNumber = geNumber;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}

	public LocalDate getAsOnDate() {
		return asOnDate;
	}

	public void setAsOnDate(LocalDate asOnDate) {
		this.asOnDate = asOnDate;
	}

	public String getJsonSent() {
		return jsonSent;
	}

	public void setJsonSent(String jsonSent) {
		this.jsonSent = jsonSent;
	}

	public String getPdfSent() {
		return pdfSent;
	}

	public void setPdfSent(String pdfSent) {
		this.pdfSent = pdfSent;
	}

	public LocalDate getJsonSentDate() {
		return jsonSentDate;
	}

	public void setJsonSentDate(LocalDate jsonSentDate) {
		this.jsonSentDate = jsonSentDate;
	}

	public Integer getHrmsStatus() {
		return hrmsStatus;
	}

	public void setHrmsStatus(Integer hrmsStatus) {
		this.hrmsStatus = hrmsStatus;
	}

	public String getDdoComments() {
		return ddoComments;
	}

	public void setDdoComments(String ddoComments) {
		this.ddoComments = ddoComments;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
