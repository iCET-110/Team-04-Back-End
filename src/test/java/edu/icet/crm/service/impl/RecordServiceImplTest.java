package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Record;
import edu.icet.crm.entity.RecordEntity;
import edu.icet.crm.entity.ReportEntity;
import edu.icet.crm.repository.RecordRepository;
import edu.icet.crm.service.RecordService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class RecordServiceImplTest {

    @MockBean
    RecordRepository recordRepository;

    @Autowired
    RecordService recordService;

    @Test
    void test_bindingRecordEntityToRecord(){
        List<RecordEntity> recordEntity = new ArrayList<>();
        List<ReportEntity> reportEntities = new ArrayList<>();
        RecordEntity recordEntity1 = new RecordEntity(
                "R001",
                "P001",
                LocalDate.of(2025, 5, 2),
                "about Records list testing",
                reportEntities
        );
        recordEntity.add(recordEntity1);
        List<Record> records = recordService.bindingRecordEntityToRecord(recordEntity);
        Assertions.assertNotNull(records);
        Assertions.assertEquals(1, records.size());
    }

    @Test
    void test_findByDate_When2025_05_02Record(){
        List<RecordEntity> recordEntities = new ArrayList<>();
        List<ReportEntity> reportEntities = new ArrayList<>();
        RecordEntity recordEntity1 = new RecordEntity(
                "R001",
                "P001",
                LocalDate.of(2025, 5, 2),
                "about Records list testing",
                reportEntities
        );
        recordEntities.add(recordEntity1);
        when(recordRepository.findByRecordDate(LocalDate.of(2025, 5, 2))).thenReturn(recordEntities);
        List<Record> records = recordService.findByDate(LocalDate.of(2025, 5, 2));
        Assertions.assertNotNull(records);
        Assertions.assertEquals(1, records.size());
    }

    @Test
    void test_findByRecordId_WhenR001Record(){
        List<RecordEntity> recordEntities = new ArrayList<>();
        List<ReportEntity> reportEntities = new ArrayList<>();
        RecordEntity recordEntity1 = new RecordEntity(
                "R001",
                "P001",
                LocalDate.of(2025, 5, 2),
                "about Records list testing",
                reportEntities
        );
        recordEntities.add(recordEntity1);
        when(recordRepository.findByRecordId("R001")).thenReturn(recordEntities);
        List<Record> records = recordService.findByRecordId("R001");
        Assertions.assertNotNull(records);
        Assertions.assertEquals(1, records.size());
    }

    @Test
    void test_findByRecordIdAndRecordDate_WhenR001And20025_05_02Record(){
        List<RecordEntity> recordEntities = new ArrayList<>();
        List<ReportEntity> reportEntities = new ArrayList<>();
        RecordEntity recordEntity1 = new RecordEntity(
                "R001",
                "P001",
                LocalDate.of(2025, 5, 2),
                "about Records list testing",
                reportEntities
        );
        recordEntities.add(recordEntity1);
        when(recordRepository.findByRecordIdAndRecordDate("R001",LocalDate.of(2025, 5, 2))).thenReturn(recordEntities);
        List<Record> records = recordService.findByRecordIdAndDate("R001",LocalDate.of(2025, 5, 2));
        Assertions.assertNotNull(records);
        Assertions.assertEquals(1, records.size());
    }

}
