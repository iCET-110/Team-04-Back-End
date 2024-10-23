package edu.icet.crm.service;

import edu.icet.crm.entity.ReportEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReportService {
    List<ReportEntity> getAllReport();

    Optional<ReportEntity> getReportById(String reportId);

    List<ReportEntity> getReportsByDate(LocalDate reportDate);
}