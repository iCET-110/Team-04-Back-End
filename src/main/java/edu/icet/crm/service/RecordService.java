package edu.icet.crm.service;

import edu.icet.crm.dto.Record;
import edu.icet.crm.entity.RecordEntity;

import java.time.LocalDate;
import java.util.List;

public interface RecordService {
    List<Record> findByDate(LocalDate date);
    List<Record> findByRecordId(String recordId);
    List<Record> findByRecordIdAndDate(String recordId,LocalDate date);
    List<Record> bindingRecordEntityToRecord(List<RecordEntity> recordEntities);
}
