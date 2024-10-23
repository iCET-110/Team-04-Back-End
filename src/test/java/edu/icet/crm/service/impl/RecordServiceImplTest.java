package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Record;
import edu.icet.crm.entity.RecordEntity;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class RecordServiceImplTest {

    @MockBean
    RecordRepository recordRepository;

    @Autowired
    RecordService recordService;

    @Test
    void test_findByRecordId_WhenRecordExists() {
        List<RecordEntity> recordEntities = new ArrayList<>();
        recordEntities.add(new RecordEntity("R001",
                "P001",
                LocalDate.of(2024, 5, 2),
                "Description",
                new ArrayList<>()));

        when(recordRepository.findAll()).thenReturn(recordEntities);
        List<Record> records = recordService.findByRecordId("R001");

        Assertions.assertNotNull(records);
        assertEquals(1, records.size());
        assertEquals("R001", records.get(0).getRecordId());
    }

    @Test
    void test_findByRecordId_WhenNoRecordExists() {
        List<RecordEntity> recordEntities = new ArrayList<>();
        when(recordRepository.findAll()).thenReturn(recordEntities);

        List<Record> records = recordService.findByRecordId("R999");

        Assertions.assertNotNull(records);
        Assertions.assertTrue(records.isEmpty());
    }

    @Test
    void test_findByRecordId_WhenNullIdExists() {
        List<RecordEntity> recordEntities = new ArrayList<>();
        recordEntities.add(new RecordEntity(null,
                "P001",
                LocalDate.of(2024, 5, 2),
                "Description",
                new ArrayList<>()));
        when(recordRepository.findAll()).thenReturn(recordEntities);
        List<Record> records = recordService.findByRecordId("R001");

        Assertions.assertNotNull(records);
        Assertions.assertTrue(records.isEmpty());
    }



    @Test
    void test_findByDate_WhenRecordsExist() {
        LocalDate date = LocalDate.of(2024, 5, 2);
        List<RecordEntity> recordEntities = new ArrayList<>();
        recordEntities.add(new RecordEntity("R001",
                "P001",
                date,
                "Description",
                new ArrayList<>()));

        when(recordRepository.findByRecordDate(date)).thenReturn(recordEntities);
        List<Record> records = recordService.findByDate(date);

        Assertions.assertNotNull(records);
        assertEquals(1, records.size());
        assertEquals("R001", records.get(0).getRecordId());
    }

    @Test
    void test_findByDate_WhenNoRecordsExist() {
        LocalDate date = LocalDate.of(2024, 5, 2);

        when(recordRepository.findByRecordDate(date)).thenReturn(new ArrayList<>());

        List<Record> records = recordService.findByDate(date);
        Assertions.assertNotNull(records);
        Assertions.assertTrue(records.isEmpty());
    }


    @Test
    void test_findByRecordIdAndDate_WhenRecordExists() {
        String recordId = "R001";
        LocalDate date = LocalDate.of(2024, 5, 2);
        List<RecordEntity> recordEntities = new ArrayList<>();
        recordEntities.add(new RecordEntity(recordId,
                "P001",
                date,
                "Description",
                new ArrayList<>()));

        when(recordRepository.findByRecordIdAndRecordDate(recordId, date)).thenReturn(recordEntities);
        List<Record> records = recordService.findByRecordIdAndDate(recordId, date);

        Assertions.assertNotNull(records);
        assertEquals(1, records.size());
        assertEquals(recordId, records.get(0).getRecordId());
    }

    @Test
    void test_findByRecordIdAndDate_WhenNoRecordExists() {
        String recordId = "R001";
        LocalDate date = LocalDate.of(2024, 5, 2);

        when(recordRepository.findByRecordIdAndRecordDate(recordId, date)).thenReturn(new ArrayList<>());

        List<Record> records = recordService.findByRecordIdAndDate(recordId, date);
        Assertions.assertNotNull(records);
        Assertions.assertTrue(records.isEmpty());
    }


}
