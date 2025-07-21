package com.hrmsdashboard.dto;

public class StatusCounts {

	private Long jsonSent;
	private Long pdfSent;
	private Long hrmsReceived;
	private Long hrmsRejected;
	private Long ddoReceived;
	private Long ddoRejected;

	public StatusCounts() {
	}

	public StatusCounts(Long jsonSent, Long pdfSent, Long hrmsReceived, Long hrmsRejected, Long ddoReceived,
			Long ddoRejected) {
		this.jsonSent = jsonSent;
		this.pdfSent = pdfSent;
		this.hrmsReceived = hrmsReceived;
		this.hrmsRejected = hrmsRejected;
		this.ddoReceived = ddoReceived;
		this.ddoRejected = ddoRejected;
	}

	public Long getJsonSent() {
		return jsonSent;
	}

	public void setJsonSent(Long jsonSent) {
		this.jsonSent = jsonSent;
	}

	public Long getPdfSent() {
		return pdfSent;
	}

	public void setPdfSent(Long pdfSent) {
		this.pdfSent = pdfSent;
	}

	public Long getHrmsReceived() {
		return hrmsReceived;
	}

	public void setHrmsReceived(Long hrmsReceived) {
		this.hrmsReceived = hrmsReceived;
	}

	public Long getHrmsRejected() {
		return hrmsRejected;
	}

	public void setHrmsRejected(Long hrmsRejected) {
		this.hrmsRejected = hrmsRejected;
	}

	public Long getDdoReceived() {
		return ddoReceived;
	}

	public void setDdoReceived(Long ddoReceived) {
		this.ddoReceived = ddoReceived;
	}

	public Long getDdoRejected() {
		return ddoRejected;
	}

	public void setDdoRejected(Long ddoRejected) {
		this.ddoRejected = ddoRejected;
	}
}
