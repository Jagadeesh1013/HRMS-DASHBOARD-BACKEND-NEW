package com.hrmsdashboard.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrmsdashboard.dto.GpfSummaryResponse;
import com.hrmsdashboard.dto.GpfTransaction;
import com.hrmsdashboard.service.GpfDashboardService;

@RestController
@RequestMapping("/api/gpf")
@CrossOrigin(origins = "http://localhost:5173")
public class GpfDashboardController {

    @Autowired
    private GpfDashboardService gpfService;

    @GetMapping("/stats")
    public ResponseEntity<GpfSummaryResponse> getGpfSummary(
            @RequestParam Optional<String> kgid,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> toDate) {

        GpfSummaryResponse summary = gpfService.getSummary(kgid, fromDate, toDate);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/transactions/{status}")
    public ResponseEntity<Page<GpfTransaction>> getGpfDetails(
            @PathVariable String status,
            @RequestParam Optional<String> kgid,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> toDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<GpfTransaction> data = gpfService.getDetails(status, kgid, fromDate, toDate, page, size);
        return ResponseEntity.ok(data);
    }
}