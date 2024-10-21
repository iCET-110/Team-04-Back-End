package edu.icet.crm.service.impl;

import edu.icet.crm.model.Report;
import edu.icet.crm.model.ReportComponent;
import edu.icet.crm.repository.ReportComponentRepository;
import edu.icet.crm.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class ReportServiceImplTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private ReportComponentRepository reportComponentRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    private Report report;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize mock data
        report = new Report();
        report.setReportComponents(Arrays.asList(
                new ReportComponent(1L, "WhiteBloodCellsCount", 7.5, report),
                new ReportComponent(2L, "RedBloodCellsCount", 4.8, report)
        ));
    }

    @Test
    void testCreateReport() {
        // Mock the repository calls
        when(reportRepository.save(report)).thenReturn(report);

        // Call the service method
        reportService.createReport(report);

        // Verify that the report and components are saved
        verify(reportRepository, times(1)).save(report);
        verify(reportComponentRepository, times(2)).save(any(ReportComponent.class));  // Verify that each component is saved
    }
}
