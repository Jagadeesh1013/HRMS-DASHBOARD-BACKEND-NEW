package com.hrmsdashboard.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hrmsdashboard.dto.GemsTransaction;
import com.hrmsdashboard.dto.StatusCounts;
import com.hrmsdashboard.entity.GemsDashboardEntity;
import com.hrmsdashboard.repository.GemsDashboardRepository;

@Service
public class GemsDashboardService {

	private final GemsDashboardRepository dashboardRepository;
	private final WebClient webClient;

	public GemsDashboardService(GemsDashboardRepository dashboardRepository, WebClient webClient) {
		this.dashboardRepository = dashboardRepository;
		this.webClient = webClient;
	}

	public StatusCounts getDashboardStats(String geNumber, String eventName, LocalDate fromDate, LocalDate toDate) {
		return dashboardRepository.getDashboardStatsWithFilters(
				geNumber != null && !geNumber.isEmpty() ? geNumber : null,
				eventName != null && !eventName.isEmpty() ? eventName : null, fromDate, toDate);
	}

	public Page<GemsTransaction> getDashboardDetails(String type, String geNumber, String eventName, LocalDate fromDate,
			LocalDate toDate, Pageable pageable) {
		Page<GemsDashboardEntity> entityPage = dashboardRepository.findByTypeWithFilters(
	            type,
	            geNumber != null && !geNumber.isEmpty() ? geNumber : null,
	            eventName != null && !eventName.isEmpty() ? eventName : null,
	            fromDate,
	            toDate,
	            pageable
	    );

	    return entityPage.map(g -> {
	        GemsTransaction dto = new GemsTransaction();
	        dto.setTransactionId(g.getTransactionId());
	        dto.setGeNumber(g.getGeNumber());
	        dto.setEventId(g.getEventId());
	        dto.setEventName(g.getEventName());
	        dto.setFileId(g.getFileId());
	        dto.setPdfFileName(g.getPdfFileName());
	        dto.setJsonSentDate(g.getJsonSentDate());
	        return dto;
	    });
	}

	public String callGemsApi(String geNumber, String month, String dbStatus) {
		return webClient.get()
					    .uri(uriBuilder -> uriBuilder
					    .path("/gemsApi")
						.queryParamIfPresent("geNumber", Optional.ofNullable(geNumber))
						.queryParamIfPresent("month", Optional.ofNullable(month))
						.queryParamIfPresent("dbStatus", Optional.ofNullable(dbStatus)).build())
					    .retrieve()
					    .bodyToMono(String.class)
					    .block();
	}
}
