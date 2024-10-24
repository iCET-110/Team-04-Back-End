package edu.icet.crm.service.impl;

import edu.icet.crm.entity.ReportEntity;
import edu.icet.crm.repository.ReportRepository;
import edu.icet.crm.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    final ReportRepository reportRepository;

    @Override
    public ReportEntity saveReport(ReportEntity reportEntity) {
        return  reportRepository.save(reportEntity);
    }

    @Override
    public List<ReportEntity> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public Optional<ReportEntity> getReport(long reportId) {
        return reportRepository.findById(reportId);
    }


}
