package com.hrmsdashboard.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hrmsdashboard.dto.GemsTransaction;
import com.hrmsdashboard.dto.StatusCounts;
import com.hrmsdashboard.entity.GemsDashboardEntity;
import com.hrmsdashboard.repository.GemsDashboardRepository;

@Service
public class GemsDashboardService {

	private final GemsDashboardRepository dashboardRepository;

	public GemsDashboardService(GemsDashboardRepository dashboardRepository) {
		this.dashboardRepository = dashboardRepository;
	}

	public StatusCounts getDashboardStats(String geNumber, 
													   String eventName, 
													   LocalDate fromDate,
													   LocalDate toDate) {
		return dashboardRepository.getDashboardStatsWithFilters(
								geNumber != null && !geNumber.isEmpty() ? geNumber : null,
								eventName != null && !eventName.isEmpty() ? eventName : null, 
								fromDate, 
								toDate);
	}

	public List<GemsTransaction> getDashboardDetails(String type,
														 String geNumber, 
														 String eventName, 
														 LocalDate fromDate,
														 LocalDate toDate) {
	List<GemsDashboardEntity> entityList = dashboardRepository.findByTypeWithFilters(
							type,
							geNumber != null && !geNumber.isEmpty() ? geNumber : null,
							eventName != null && !eventName.isEmpty() ? eventName : null, 
							fromDate, 
							toDate);
		return entityList.stream().map(g -> {
	        GemsTransaction dto = new GemsTransaction();
	        dto.setTransactionId(g.getTransactionId());
	        dto.setGeNumber(g.getGeNumber());
	        dto.setEventId(g.getEventId());
	        dto.setEventName(g.getEventName());
	        dto.setFileId(g.getFileId());
	        dto.setPdfFileName(g.getPdfFileName());
	        dto.setJsonSentDate(g.getJsonSentDate());
	        return dto;
	    }).toList();
	}
}
