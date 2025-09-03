package com.hrmsdashboard.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrmsdashboard.dto.StatusCounts;
import com.hrmsdashboard.entity.GemsDashboardEntity;

public interface GemsDashboardRepository extends JpaRepository<GemsDashboardEntity, Long> {

	@Query(value = """
			    SELECT
			        COUNT(DISTINCT CASE WHEN json_sent = 'Y' THEN pdf_file_name END) AS json_sent_count,
			        COUNT(DISTINCT CASE WHEN pdf_sent = 'Y' THEN pdf_file_name END) AS pdf_sent_count,
			        COUNT(DISTINCT CASE WHEN hrms_status = 1 THEN pdf_file_name END) AS hrms_received,
			        COUNT(DISTINCT CASE WHEN hrms_status = 2 THEN pdf_file_name END) AS hrms_rejected,
			        COUNT(DISTINCT CASE WHEN hrms_status = 3 THEN pdf_file_name END) AS ddo_received,
			        COUNT(DISTINCT CASE WHEN hrms_status = 4 THEN pdf_file_name END) AS ddo_rejected
			    FROM hrms_backup.hrms_status_update
			    WHERE ge_number = COALESCE(CAST(:geNumber AS TEXT), ge_number)
			      AND event_name = COALESCE(CAST(:eventName AS TEXT), event_name)
			      AND created_at::date >= COALESCE(:fromDate, created_at::date)
			      AND created_at::date <= COALESCE(:toDate, created_at::date)
			""", nativeQuery = true)
	StatusCounts getDashboardStatsWithFilters(@Param("geNumber") String geNumber, @Param("eventName") String eventName,
			@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

	@Query(value = """
			    SELECT *
			    FROM hrms_backup.hrms_status_update h
			    WHERE ge_number = COALESCE(CAST(:geNumber AS TEXT), ge_number)
			      AND event_name = COALESCE(CAST(:eventName AS TEXT), event_name)
			      AND created_at::date >= COALESCE(:fromDate, created_at::date)
			      AND created_at::date <= COALESCE(:toDate, created_at::date)
			      AND (
			        (:type = 'jsonSent' AND h.json_sent = 'Y') OR
			        (:type = 'pdfSent' AND h.pdf_sent = 'Y') OR
			        (:type = 'hrmsReceived' AND h.hrms_status = 1) OR
			        (:type = 'hrmsRejected' AND h.hrms_status = 2) OR
			        (:type = 'ddoReceived' AND h.hrms_status = 3) OR
			        (:type = 'ddoRejected' AND h.hrms_status = 4)
			      )
			""", nativeQuery = true)
	Page<GemsDashboardEntity> findByTypeWithFilters(@Param("type") String type, @Param("geNumber") String geNumber,
			@Param("eventName") String eventName, @Param("fromDate") LocalDate fromDate,
			@Param("toDate") LocalDate toDate, Pageable pageable);
}
