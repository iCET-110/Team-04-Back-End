package edu.icet.crm.service.impl;

import edu.icet.crm.model.Report;
import edu.icet.crm.model.ReportComponent;
import edu.icet.crm.repository.ReportComponentRepository;
import edu.icet.crm.repository.ReportRepository;
import edu.icet.crm.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportComponentRepository reportComponentRepository;

    @Override
    public Report createReport(Report report) {
        // Save the report first
        Report savedReport = reportRepository.save(report);

        // Extract components from the report
        List<ReportComponent> reportComponents = report.getReportComponents();

        // Set the report reference in each component
        for (ReportComponent component : reportComponents) {
            component.setReport(savedReport);
        }

        // Save all components in one batch
        reportComponentRepository.saveAll(reportComponents);

        return savedReport;
    }
}
