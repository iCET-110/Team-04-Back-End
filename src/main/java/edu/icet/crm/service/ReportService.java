package edu.icet.crm.service;

import edu.icet.crm.entity.ReportEntity;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    ReportEntity saveReport(ReportEntity reportEntity);

    List<ReportEntity> getAllReports();

    Optional<ReportEntity> getReport(long reportId);
}
