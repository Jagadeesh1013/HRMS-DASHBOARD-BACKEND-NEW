package com.hrmsdashboard.dto;

import java.util.Map;

public class GpfSummaryResponse {

	private Map<String, Integer> statusCounts;
	private int totalTransactions;

	// Getters and Setters
	public Map<String, Integer> getStatusCounts() {
		return statusCounts;
	}

	public void setStatusCounts(Map<String, Integer> statusCounts) {
		this.statusCounts = statusCounts;
	}

	public int getTotalTransactions() {
		return totalTransactions;
	}

	public void setTotalTransactions(int totalTransactions) {
		this.totalTransactions = totalTransactions;
	}

}
