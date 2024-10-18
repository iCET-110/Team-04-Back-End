package edu.icet.crm.repository;

import edu.icet.crm.dto.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,String> {
}
