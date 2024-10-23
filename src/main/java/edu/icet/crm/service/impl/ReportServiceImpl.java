package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Report;
import edu.icet.crm.entity.ReportEntity;
import edu.icet.crm.repository.ReportRepository;
import edu.icet.crm.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    final ReportRepository reportRepository;

    @Override
    public List<ReportEntity> getAllReport() {
        return reportRepository.findAll();
    }

    @Override
    public Optional<ReportEntity> getReportById(String reportId) {
        return reportRepository.findById(reportId);
    }

    @Override
    public List<Report> getReportsById(LocalDate reportDate) {
        return reportRepository.findByReportDate(reportDate);
    }

}