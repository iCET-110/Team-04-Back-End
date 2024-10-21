package edu.icet.crm.service;

import edu.icet.crm.dto.Report;
import edu.icet.crm.entity.ReportEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReportService {
    List<ReportEntity> getAllReport();

    Optional<ReportEntity> getReportById(String reportId);

    List<Report> getReportsByDate(LocalDateTime reportDate);
}