package com.hrmsdashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrmsdashboard.entity.GpfDashboardEntity;

public interface GpfDashboardRepository extends JpaRepository<GpfDashboardEntity, String> {

    @Query("SELECT g.status, COUNT(g) FROM GpfDashboardEntity g WHERE " +
           "(:kgid IS NULL OR g.kgid = :kgid) AND " +
           "(:fromDate IS NULL OR g.jsonSentDate >= :fromDate) AND " +
           "(:toDate IS NULL OR g.jsonSentDate <= :toDate) " +
           "GROUP BY g.status")
    List<Object[]> countByStatusFiltered(@Param("kgid") String kgid,
                                         @Param("fromDate") LocalDate fromDate,
                                         @Param("toDate") LocalDate toDate);

    @Query("SELECT g FROM GpfDashboardEntity g WHERE g.status = :status AND " +
           "(:kgid IS NULL OR g.kgid = :kgid) AND " +
           "(:fromDate IS NULL OR g.jsonSentDate >= :fromDate) AND " +
           "(:toDate IS NULL OR g.jsonSentDate <= :toDate)")
    Page<GpfDashboardEntity> findFilteredByStatus(@Param("status") String status,
                                         @Param("kgid") String kgid,
                                         @Param("fromDate") LocalDate fromDate,
                                         @Param("toDate") LocalDate toDate,
                                         Pageable pageable);
}
