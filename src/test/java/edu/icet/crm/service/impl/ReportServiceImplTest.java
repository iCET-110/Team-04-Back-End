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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
 class ReportServiceImplTest {
    @MockBean
    ReportRepository reportRepository;

    @Autowired
    ReportService reportService;


    @Test
    void testSearchById_WhenBRP001Report() {
        ReportEntity report1 = new ReportEntity(
                "BRP001001",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 22),
                null
        );
        ReportEntity report2 = new ReportEntity(
                "BRP001002",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 23),
                null
        );

        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        List<Report> result = reportService.search("BRP001");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testSearchByIdAndDate_WhenBRP001001_And_Date_20241022() {
        ReportEntity report1 = new ReportEntity(
                "BRP001001",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 22),
                null
        );
        ReportEntity report2 = new ReportEntity(
                "BRP001002",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 23),
                null
        );
        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));
        List<Report> result = reportService.search("BRP001001", LocalDate.of(2024, 10, 22));
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("BRP001001", result.get(0).getReportId());
    }

    @Test
    void testSearchByDate_WhenDate_20241023() {
        ReportEntity report1 = new ReportEntity(
                "BRP001001",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 22),
                null
        );
        ReportEntity report2 = new ReportEntity(
                "BRP001002", null,
                "Blood Report",
                LocalDate.of(2024, 10, 23),
                null
        );
        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));
        List<Report> result = reportService.search(LocalDate.of(2024, 10, 22));
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("BRP001001", result.get(0).getReportId());
    }

    @Test
    void testSearch_WhenIdIsNull() {
        ReportEntity report1 = new ReportEntity(
                "BRP001001",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 22),
                null
        );
        ReportEntity report2 = new ReportEntity(
                "BRP001002",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 23),
                null
        );

        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        List<Report> result = reportService.search((String) null);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("BRP001001", result.get(0).getReportId());
        Assertions.assertEquals("BRP001002", result.get(1).getReportId());
    }

    @Test
    void testSearchByIdAndDate_WhenBothAreNull() {
        ReportEntity report1 = new ReportEntity(
                "BRP001001",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 22),
                null
        );
        ReportEntity report2 = new ReportEntity(
                "BRP001002",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 23),
                null
        );

        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        List<Report> result = reportService.search(null, null);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testSearchByIdAndDate_WhenIdIsNull() {
        ReportEntity report1 = new ReportEntity(
                "BRP001001",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 22),
                null
        );
        ReportEntity report2 = new ReportEntity(
                "BRP001002",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 23),
                null
        );

        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        List<Report> result = reportService.search(null, LocalDate.of(2024, 10, 22));
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("BRP001001", result.get(0).getReportId());
    }

    @Test
    void testSearchByDate_WhenDateIsNull() {
        ReportEntity report1 = new ReportEntity(
                "BRP001001",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 22),
                null
        );
        ReportEntity report2 = new ReportEntity(
                "BRP001002",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 23),
                null
        );

        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        List<Report> result = reportService.search((LocalDate) null);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testSearchByDate_WhenDateIsNotNull() {
        ReportEntity report1 = new ReportEntity(
                "BRP001001",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 22),
                null
        );
        ReportEntity report2 = new ReportEntity(
                "BRP001002",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 23),
                null
        );

        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        List<Report> result = reportService.search(LocalDate.of(2024, 10, 23));
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("BRP001002", result.get(0).getReportId());
    }



    @Test
    void testSearchByIdAndDate() {

        ReportEntity report1 = new ReportEntity(
                "BRP001001",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 22),
                null
        );
        ReportEntity report2 = new ReportEntity(
                "BRP001002",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 23),
                null
        );
        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        List<Report> result = reportService.search("BRP001", LocalDate.of(2024, 10, 22));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("BRP001001", result.get(0).getReportId());
    }

    @Test
    void testSearchByIdAndDate_NoMatch() {

        ReportEntity report1 = new ReportEntity(
                "BRP001001",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 22),
                null
        );
        ReportEntity report2 = new ReportEntity(
                "BRP001002",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 23),
                null
        );

        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        List<Report> result = reportService.search("BRP002", LocalDate.of(2024, 10, 22));

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testSearchById_OnlyIdProvided() {
        ReportEntity report1 = new ReportEntity(
                "BRP001001",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 22),
                null
        );
        ReportEntity report2 = new ReportEntity(
                "BRP001002",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 23),
                null
        );

        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        List<Report> result = reportService.search("BRP001", null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }
    @Test
    void testSearchByDate_OnlyDateProvided() {
        ReportEntity report1 = new ReportEntity(
                "BRP001001",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 22),
                null
        );
        ReportEntity report2 = new ReportEntity(
                "BRP001002",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 23),
                null
        );

        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        List<Report> result = reportService.search(null, LocalDate.of(2024, 10, 22));

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("BRP001001", result.get(0).getReportId());
    }

    @Test
    void testSearch_BothIdAndDateNull() {

        ReportEntity report1 = new ReportEntity(
                "BRP001001",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 22),
                null
        );
        ReportEntity report2 = new ReportEntity(
                "BRP001002",
                null,
                "Blood Report",
                LocalDate.of(2024, 10, 23),
                null
        );

        when(reportRepository.findAll()).thenReturn(Arrays.asList(report1, report2));

        List<Report> result = reportService.search(null, null);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

}
