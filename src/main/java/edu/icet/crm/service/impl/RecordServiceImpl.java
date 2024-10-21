package edu.icet.crm.service.impl;

import edu.icet.crm.repository.RecordRepository;
import edu.icet.crm.service.RecordService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    final RecordRepository recordRepository;
}
