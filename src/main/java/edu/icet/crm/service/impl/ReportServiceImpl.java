package edu.icet.crm.service.impl;

import edu.icet.crm.repository.ReportRepository;
import edu.icet.crm.service.ReportService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    final ReportRepository reportRepository;
}
