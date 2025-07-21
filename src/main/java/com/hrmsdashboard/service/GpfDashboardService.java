package com.hrmsdashboard.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hrmsdashboard.dto.GpfSummaryResponse;
import com.hrmsdashboard.dto.GpfTransaction;
import com.hrmsdashboard.entity.GpfDashboardEntity;
import com.hrmsdashboard.repository.GpfDashboardRepository;

@Service
public class GpfDashboardService {

    @Autowired
    private GpfDashboardRepository gpfRepository;

    public GpfSummaryResponse getSummary(Optional<String> kgid,
                                         Optional<LocalDate> fromDate,
                                         Optional<LocalDate> toDate) {

        List<Object[]> statusCountsRaw = gpfRepository.countByStatusFiltered(
                kgid.orElse(null),
                fromDate.orElse(null),
                toDate.orElse(null));

        Map<String, Integer> statusCounts = new HashMap<>();
        int total = 0;

        for (Object[] row : statusCountsRaw) {
            String status = (String) row[0];
            int count = ((Long) row[1]).intValue();
            statusCounts.put(status, count);
            total += count;
        }

        GpfSummaryResponse response = new GpfSummaryResponse();
        response.setStatusCounts(statusCounts);
        response.setTotalTransactions(total);

        return response;
    }

    public Page<GpfTransaction> getDetails(String status,
                                              Optional<String> kgid,
                                              Optional<LocalDate> fromDate,
                                              Optional<LocalDate> toDate,
                                              int page, int size) {

        Page<GpfDashboardEntity> entityPage = gpfRepository.findFilteredByStatus(
                status,
                kgid.orElse(null),
                fromDate.orElse(null),
                toDate.orElse(null),
                PageRequest.of(page, size)
        );

        return entityPage.map(e -> {
            GpfTransaction dto = new GpfTransaction();
            dto.setTransactionId(e.getTransactionId());
            dto.setGpfId(e.getGpfId());
            dto.setKgid(e.getKgid());
            dto.setName(e.getName());
            dto.setDateOfBirth(e.getDateOfBirth());
            dto.setJoiningDate(e.getJoiningDate());
            dto.setPolicyNo(e.getPolicyNo());
            dto.setPolicyStartDate(e.getPolicyStartDate());
            dto.setJsonSentDate(e.getJsonSentDate());
            dto.setStatus(e.getStatus());
            return dto;
        });
    }
}
