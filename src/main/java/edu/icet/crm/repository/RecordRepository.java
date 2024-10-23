package edu.icet.crm.repository;

import edu.icet.crm.entity.RecordEntity;
import edu.icet.crm.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<RecordEntity,String> {
}
