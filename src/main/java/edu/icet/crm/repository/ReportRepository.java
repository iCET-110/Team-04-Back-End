package edu.icet.crm.repository;

import edu.icet.crm.dto.Report;
import edu.icet.crm.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportRepository extends JpaRepository<ReportEntity,String>{
    List<Report> findByDate(LocalDateTime reportDate);
}
