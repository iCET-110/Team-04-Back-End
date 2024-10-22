package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Record;
import edu.icet.crm.entity.RecordEntity;
import edu.icet.crm.repository.RecordRepository;
import edu.icet.crm.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {

    final RecordRepository recordRepository;

    @Override
    public List<Record> findByDate(LocalDate date) {
        List<RecordEntity> recordEntities = recordRepository.findByRecordDate(date);
        return bindingRecordEntityToRecord(recordEntities);
    }

    @Override
    public List<Record> findByRecordId(String recordId) {
        List<RecordEntity> recordEntities = recordRepository.findByRecordId(recordId);
        return bindingRecordEntityToRecord(recordEntities);
    }

    @Override
    public List<Record> findByRecordIdAndDate(String recordId, LocalDate date) {
        List<RecordEntity> recordEntities = recordRepository.findByRecordIdAndRecordDate(recordId,date);
        return bindingRecordEntityToRecord(recordEntities);
    }

    @Override
    public List<Record> bindingRecordEntityToRecord(List<RecordEntity> recordEntities) {
        List<Record> records = new ArrayList<>();
        recordEntities.forEach(entity -> records.add(new ModelMapper().map(entity, Record.class)));
        return records;
    }

}
