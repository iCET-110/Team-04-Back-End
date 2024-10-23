package edu.icet.crm.service.impl;

import edu.icet.crm.entity.ReportEntity;
import edu.icet.crm.repository.ReportRepository;
import edu.icet.crm.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    final ReportRepository reportRepository;

    @Override
    public ReportEntity saveReport(ReportEntity reportEntity) {
        return  reportRepository.save(reportEntity);
    }
}
