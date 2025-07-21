package com.hrmsdashboard.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gpf_transactions")
public class GpfDashboardEntity {

	@Id
	private String transactionId;

	private String gpfId;
	private String kgid;
	private String name;
	private LocalDate dateOfBirth;
	private LocalDate joiningDate;
	private String policyNo;
	private LocalDate policyStartDate;
	private LocalDate jsonSentDate;
	private String status;

	// Constructors
	public GpfDashboardEntity() {
	}

	public GpfDashboardEntity(String transactionId, String gpfId, String kgid, String name, LocalDate dateOfBirth,
			LocalDate joiningDate, String policyNo, LocalDate policyStartDate, LocalDate jsonSentDate, String status) {
		super();
		this.transactionId = transactionId;
		this.gpfId = gpfId;
		this.kgid = kgid;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.joiningDate = joiningDate;
		this.policyNo = policyNo;
		this.policyStartDate = policyStartDate;
		this.jsonSentDate = jsonSentDate;
		this.status = status;
	}

	// Getters and Setters
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getGpfId() {
		return gpfId;
	}

	public void setGpfId(String gpfId) {
		this.gpfId = gpfId;
	}

	public String getKgid() {
		return kgid;
	}

	public void setKgid(String kgid) {
		this.kgid = kgid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public LocalDate getPolicyStartDate() {
		return policyStartDate;
	}

	public void setPolicyStartDate(LocalDate policyStartDate) {
		this.policyStartDate = policyStartDate;
	}

	public LocalDate getJsonSentDate() {
		return jsonSentDate;
	}

	public void setJsonSentDate(LocalDate jsonSentDate) {
		this.jsonSentDate = jsonSentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
