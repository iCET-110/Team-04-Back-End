package edu.icet.crm.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.crm.dto.Record;
import edu.icet.crm.entity.RecordEntity;
import edu.icet.crm.repository.RecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.Mockito.*;

class RecordServiceImplTest {

    @Mock
    private RecordRepository recordRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private RecordServiceImpl recordService;

    private Record record;
    private RecordEntity recordEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        record = new Record("R001", "P001", new Date(), "Test description", null);
        recordEntity = new RecordEntity("R001", "P001", new Date(), "Test description", null);
    }

    @Test
    void test_AddRecord_Success() {
        when(objectMapper.convertValue(record, RecordEntity.class)).thenReturn(recordEntity);
        recordService.addRecord(record);
        verify(recordRepository, times(1)).save(recordEntity);
    }
}
