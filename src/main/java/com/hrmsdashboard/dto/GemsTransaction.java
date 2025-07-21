package com.hrmsdashboard.dto;

import java.time.LocalDate;

public class GemsTransaction {

	private String transactionId;
	private String geNumber;
	private String eventId;
	private String eventName;
	private String fileId;
	private String pdfFileName;
	private LocalDate jsonSentDate;

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

	public LocalDate getJsonSentDate() {
		return jsonSentDate;
	}

	public void setJsonSentDate(LocalDate jsonSentDate) {
		this.jsonSentDate = jsonSentDate;
	}

}
