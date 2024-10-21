package edu.icet.crm.repository;

import edu.icet.crm.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    // You can define custom query methods here if needed
}
