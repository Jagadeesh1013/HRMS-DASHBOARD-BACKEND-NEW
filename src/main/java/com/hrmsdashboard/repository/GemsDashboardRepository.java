package com.hrmsdashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrmsdashboard.dto.StatusCounts;
import com.hrmsdashboard.entity.GemsDashboardEntity;

public interface GemsDashboardRepository extends JpaRepository<GemsDashboardEntity, Long> {

	@Query("""
		    SELECT new com.hrmsdashboard.dto.StatusCounts(
		        COUNT(CASE WHEN h.jsonSent = 'Y' THEN 1 ELSE NULL END),
		        COUNT(CASE WHEN h.pdfSent = 'Y' THEN 1 ELSE NULL END),
		        COUNT(CASE WHEN h.hrmsStatus = 1 THEN 1 ELSE NULL END),
		        COUNT(CASE WHEN h.hrmsStatus = 2 THEN 1 ELSE NULL END),
		        COUNT(CASE WHEN h.hrmsStatus = 3 THEN 1 ELSE NULL END),
		        COUNT(CASE WHEN h.hrmsStatus = 4 THEN 1 ELSE NULL END)
		    )
		    FROM GemsDashboardEntity h
		    WHERE (:geNumber IS NULL OR h.geNumber = :geNumber)
		      AND (:eventName IS NULL OR LOWER(h.eventName) = LOWER(:eventName))
		      AND (:fromDate IS NULL OR DATE(h.createdAt) >= :fromDate)
		      AND (:toDate IS NULL OR DATE(h.createdAt) <= :toDate)
		""")
		StatusCounts getDashboardStatsWithFilters(
		    @Param("geNumber") String geNumber,
		    @Param("eventName") String eventName,
		    @Param("fromDate") LocalDate fromDate,
		    @Param("toDate") LocalDate toDate
		);
	
	@Query("""
		    SELECT h FROM GemsDashboardEntity h
		    WHERE (:geNumber IS NULL OR LOWER(h.geNumber) = LOWER(:geNumber))
		      AND (:eventName IS NULL OR LOWER(h.eventName) = LOWER(:eventName))
		      AND (:fromDate IS NULL OR DATE(h.createdAt) >= :fromDate)
			  AND (:toDate IS NULL OR DATE(h.createdAt) <= :toDate)
		      AND (
		        (:type = 'jsonSent' AND h.jsonSent = 'Y') OR
		        (:type = 'pdfSent' AND h.pdfSent = 'Y') OR
		        (:type = 'hrmsReceived' AND h.hrmsStatus = 1) OR
		        (:type = 'hrmsRejected' AND h.hrmsStatus = 2) OR
		        (:type = 'ddoReceived' AND h.hrmsStatus = 3) OR
		        (:type = 'ddoRejected' AND h.hrmsStatus = 4)
		      )
		""")
		List<GemsDashboardEntity> findByTypeWithFilters(
		        @Param("type") String type,
		        @Param("geNumber") String geNumber,
		        @Param("eventName") String eventName,
		        @Param("fromDate") LocalDate fromDate,
		        @Param("toDate") LocalDate toDate
		);
}
