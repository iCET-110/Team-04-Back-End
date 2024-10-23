package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Report;
import edu.icet.crm.entity.ReportEntity;
import edu.icet.crm.repository.ReportRepository;
import edu.icet.crm.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
    final ReportRepository reportRepository;

    @Override
    public List<Report> search(String id) {
        List<ReportEntity> reportEntities = reportRepository.findAll();

        if (id == null) {
            return reportEntities.stream()
                    .map(reportEntity -> new ModelMapper().map(reportEntity, Report.class))
                    .collect(Collectors.toList());
        }

        List<Report> reports = reportEntities.stream()
                .map(reportEntity -> new ModelMapper().map(reportEntity, Report.class))
                .collect(Collectors.toList());

        return reports.stream()
                .filter(report -> report.getReportId() != null && report.getReportId().toLowerCase().contains(id.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Report> search(String id, LocalDate date) {
        List<ReportEntity> reportEntities = reportRepository.findAll();

        // If both id and date are null, return all reports
        if (id == null && date == null) {
            return reportEntities.stream()
                    .map(reportEntity -> new ModelMapper().map(reportEntity, Report.class))
                    .collect(Collectors.toList());
        }

        // Filter reports based on the provided id and date
        return reportEntities.stream()
                .map(reportEntity -> new ModelMapper().map(reportEntity, Report.class))
                .filter(report -> (id == null || (report.getReportId() != null && report.getReportId().toLowerCase().contains(id.toLowerCase()))) &&
                        (date == null || (report.getReportDate() != null && report.getReportDate().equals(date))))
                .collect(Collectors.toList());
    }

    @Override
    public List<Report> search(LocalDate date) {
        List<ReportEntity> reportEntities = reportRepository.findAll();

        if (date == null) {
            return reportEntities.stream()
                    .map(reportEntity -> new ModelMapper().map(reportEntity, Report.class))
                    .collect(Collectors.toList());
        }

        List<Report> reports = reportEntities.stream()
                .map(reportEntity -> new ModelMapper().map(reportEntity, Report.class))
                .collect(Collectors.toList());

        return reports.stream()
                .filter(report -> report.getReportDate() != null && report.getReportDate().equals(date))
                .collect(Collectors.toList());
    }
}
