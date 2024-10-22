package edu.icet.crm.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.dto.Record;
import edu.icet.crm.entity.RecordEntity;
import edu.icet.crm.entity.ReportEntity;
import edu.icet.crm.repository.RecordRepository;
import edu.icet.crm.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {

    final ObjectMapper objectMapper;
    final RecordRepository recordRepository;

    @Override
    public void deleteRecord(String id) {
        recordRepository.deleteById(id);
    }



    @Override
    public void updateRecord(String id, Record record) throws Exception {
        Optional<RecordEntity> exitingRecordOptional = recordRepository.findById(id);

        if (exitingRecordOptional.isPresent()){
            RecordEntity  exitingRecord = exitingRecordOptional.get();

            exitingRecord.setDescription(record.getDescription());
            List<ReportEntity> reportEntityList = objectMapper.convertValue(
                    record.getReportList(),
                    new TypeReference<>() {});
            exitingRecord.setReportList(reportEntityList);

            recordRepository.save(exitingRecord);

        }
        else{
            throw new Exception("Record with ID"+id+"not found");
        }
    }



}
