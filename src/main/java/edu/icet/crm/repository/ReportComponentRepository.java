package edu.icet.crm.repository;

import edu.icet.crm.model.ReportComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportComponentRepository extends JpaRepository<ReportComponent, Long> {
    // You can define custom query methods here if needed
}
