package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.dto.Record;
import edu.icet.crm.entity.RecordEntity;
import edu.icet.crm.repository.RecordRepository;
import edu.icet.crm.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {


    final ObjectMapper objectMapper;

    final RecordRepository recordRepository;

    @Override
    public void addRecord(Record record) {
        recordRepository.save(objectMapper.convertValue(record, RecordEntity.class));
    }
}
