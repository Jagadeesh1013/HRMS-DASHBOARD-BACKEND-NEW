package com.hrmsdashboard.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrmsdashboard.dto.GemsTransaction;
import com.hrmsdashboard.dto.StatusCounts;
import com.hrmsdashboard.service.GemsDashboardService;

@RestController
@RequestMapping("/api/gems")
@CrossOrigin(origins = "http://localhost:5173")
public class GemsDashboardController {

	private final GemsDashboardService dashboardService;

	public GemsDashboardController(GemsDashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}

	@GetMapping("/stats")
	public ResponseEntity<StatusCounts> getDashboardStats(@RequestParam(required = false) String geNumber,
			@RequestParam(required = false) String eventName,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

		StatusCounts stats = dashboardService.getDashboardStats(geNumber, eventName, fromDate, toDate);
		return ResponseEntity.ok(stats);
	}

	@GetMapping("/transactions/{type}")
	public ResponseEntity<Page<GemsTransaction>> getDashboardDetails(
	        @PathVariable String type,
	        @RequestParam(required = false) String geNumber,
	        @RequestParam(required = false) String eventName,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
	        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {

		PageRequest pageble = PageRequest.of(page, size);
	    Page<GemsTransaction> data = dashboardService.getDashboardDetails(type, geNumber, eventName, fromDate, toDate, pageble);
	    return ResponseEntity.ok(data);
	}


	@GetMapping("/triggerGems")
	public String triggerGems(@RequestParam(required = false) String geNumber,
			@RequestParam(required = false) String month, @RequestParam(required = false) String dbStatus) {
		return dashboardService.callGemsApi(geNumber, month, dbStatus);
	}
}