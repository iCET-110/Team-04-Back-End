package edu.icet.crm.repository;

import edu.icet.crm.dto.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record,String> {
}
