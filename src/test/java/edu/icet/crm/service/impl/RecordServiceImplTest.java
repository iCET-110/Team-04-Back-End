package edu.icet.crm.service.impl;

import edu.icet.crm.entity.RecordEntity;
import edu.icet.crm.repository.RecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RecordServiceImplTest {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private RecordServiceImpl recordService;

    private String validId;

    @BeforeEach
    void setUp() {

        List<RecordEntity> allRecords = recordRepository.findAll();

        assertFalse(allRecords.isEmpty(), "No records found in the database.");

        validId = allRecords.get(0).getRecordId();
    }

    @Test
    void shouldDeleteRecordWhenValidIdIsProvided() {

        assertNotNull(validId, "Valid record ID is null!");

        recordService.deleteRecord(validId);

        assertFalse(recordRepository.existsById(validId), "Record was not deleted.");
    }

    @Test
    void shouldHandleDeletionWhenRecordDoesNotExist() {
        String invalidId = "nonexistent";

        assertThrows(RuntimeException.class, () -> {
            recordService.deleteRecord(invalidId);
        });
    }
}
