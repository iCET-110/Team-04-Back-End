package edu.icet.crm.repository;

import edu.icet.crm.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface RecordRepository extends JpaRepository<RecordEntity,String> {
    List<RecordEntity> findByRecordDate(LocalDate date);
    List<RecordEntity> findByRecordId(String recordId);
    List<RecordEntity> findByRecordIdAndRecordDate(String recordId, LocalDate dateString);
}
