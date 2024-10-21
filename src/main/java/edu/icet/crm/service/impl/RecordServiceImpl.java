package edu.icet.crm.service.impl;

import edu.icet.crm.repository.RecordRepository;
import edu.icet.crm.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordRepository repository;

    @Override
    public void deleteRecord(String id) {
        repository.deleteById(id);
    }
}
