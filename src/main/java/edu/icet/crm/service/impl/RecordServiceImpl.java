package edu.icet.crm.service.impl;

import edu.icet.crm.repository.RecordRepository;
import edu.icet.crm.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecordServiceImpl implements RecordService {

    final RecordRepository recordRepository;
}
