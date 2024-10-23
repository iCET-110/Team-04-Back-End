package edu.icet.crm.repository;

import edu.icet.crm.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ReportRepository extends JpaRepository<ReportEntity,String>{

    List<ReportEntity> findByReportDate(LocalDate reportDate);
}
