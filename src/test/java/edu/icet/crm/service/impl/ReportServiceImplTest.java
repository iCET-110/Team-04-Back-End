package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Report;
import edu.icet.crm.entity.ReportEntity;
import edu.icet.crm.repository.ReportRepository;
import edu.icet.crm.service.ReportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ReportServiceImplTest {

    @Autowired
    ReportService reportService;

    @MockBean
    ReportRepository reportRepository;

    @Test
    void testGetAllReport() {
        List<ReportEntity> mockReports = List.of(new ReportEntity(), new ReportEntity());
        when(reportRepository.findAll()).thenReturn(mockReports);

        List<ReportEntity> reports = reportService.getAllReport();

        assertEquals(2, reports.size());

    }


    @Test
    void testGetReportById() {
        String reportId = "B123";
        String reportLink = "www.reportLink";
        LocalDate reportDate = LocalDate.of(2024,10,10);
        String categoryType = "Blood";


        ReportEntity mockReport = new ReportEntity(
                reportId,
                reportLink,
                reportDate,
                categoryType,
                null
        );
        when(reportRepository.findById(reportId)).thenReturn(Optional.of(mockReport));

        Optional<ReportEntity> report = reportService.getReportById(reportId);

        Assertions.assertEquals(Optional.of(mockReport),report);
    }

    @Test
    void testGetReportsByDate() {
        String reportId = "B123";
        String reportLink = "www.reportLink";
        LocalDate reportDate = LocalDate.of(2024,10,10);
        String categoryType = "Blood";


        List<ReportEntity> mockReports = List.of(new ReportEntity(
                reportId,
                reportLink,
                reportDate,
                categoryType,
                null
        ), new ReportEntity(
                reportId,
                reportLink,
                reportDate,
                categoryType,
                null
        ));
        when(reportRepository.findByReportDate(reportDate)).thenReturn(mockReports);

        List<ReportEntity> reports = reportService.getReportsByDate(reportDate);

        assertEquals(2, reports.size());
    }
}