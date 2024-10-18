package edu.icet.crm.repository;

import edu.icet.crm.dto.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface RecordRepository extends JpaRepository<Record,String> {
}
