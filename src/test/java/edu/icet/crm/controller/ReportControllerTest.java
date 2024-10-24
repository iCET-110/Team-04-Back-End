package edu.icet.crm.controller;

import edu.icet.crm.entity.ReportEntity;
import edu.icet.crm.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportControllerTest {

    @InjectMocks
    private ReportController reportController;

    @Mock
    private ReportServiceImpl reportService;

    private final String testReportDir = "src/main/resources/reports/";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create test directory
        File dir = new File(testReportDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }


    @Test
    void testAddReport_Success() throws IOException {
        // Arrange
        String reportName = "Record 12345.pdf";
        MockMultipartFile mockFile = new MockMultipartFile("report", reportName, "application/pdf", "PDF content".getBytes());
        String note = "Test report note";

        // Simulate saving the report and returning a mock report entity
        ReportEntity mockSavedReport = new ReportEntity(1L, 12345L, "", "Record", note, null);
        when(reportService.saveReport(any(ReportEntity.class))).thenReturn(mockSavedReport);

        // Act
        ResponseEntity<String> response = reportController.addReport(note, mockFile);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reportName + " file saved to the database successfully.", response.getBody());

        // Cleanup
        Files.deleteIfExists(Paths.get(reportController.getREPORT_UPLOAD_DIR(), reportName));
    }


    @Test
    void testAddReport_EmptyFile() {
        // Arrange
        MockMultipartFile mockFile = new MockMultipartFile("report", "", "application/pdf", new byte[0]);
        String note = "Test report note";

        // Act
        ResponseEntity<String> response = reportController.addReport(note, mockFile);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Report file cannot be null or empty", response.getBody());
    }

    @Test
    void testAddReport_InvalidNamingConvention() {
        // Arrange
        MockMultipartFile mockFile = new MockMultipartFile("report", "InvalidFileName.pdf", "application/pdf", "PDF content".getBytes());
        String note = "Test report note";

        // Act
        ResponseEntity<String> response = reportController.addReport(note, mockFile);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Please follow the correct naming convention", response.getBody());
    }

    @Test
    public void testAddReport_ValidReport() throws IOException {
        // Given a valid report file
        MockMultipartFile mockFile = new MockMultipartFile(
                "report",
                "Record 12345.pdf",
                "application/pdf",
                "This is a test file".getBytes()
        );

        // Simulate saving the report and returning a mock report entity with ID 2
        ReportEntity mockSavedReport = new ReportEntity(2L, 12345L, "", "Record", "Test note", null);
        when(reportService.saveReport(any(ReportEntity.class))).thenReturn(mockSavedReport);

        // When calling the addReport method
        ResponseEntity<String> response = reportController.addReport("Test note", mockFile);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Validate the response message
        assertEquals("Record 12345.pdf file saved to the database successfully.", response.getBody());

        // Validate the renaming pattern
        // New report name will be something like "Record 12345 R0002.pdf"
        String reportLink = mockSavedReport.getReportLink(); // e.g., "api/v1/report/download/Record 12345 R0002.pdf"
        assertNotNull(reportLink);

        // Extract the filename from the reportLink
        String newFileName = reportLink.substring(reportLink.lastIndexOf("/") + 1);

        // Pattern to match "Record 12345 RXXXX.pdf", where XXXX is a 4-digit number
        String pattern = "^Record 12345 R\\d{4}\\.pdf$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(newFileName);

        assertTrue(m.matches(), "The file name should follow the pattern: Record 12345 RXXXX.pdf");
    }

    @Test
    void testGetReport_FileDoesNotExist() {
        // Act
        ResponseEntity<Resource> response = reportController.downloadReport("nonexistent.pdf");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetReport_FileNotReadable() {
        // Simulate a file that is not readable
        String fileName = "unreadable_report.pdf";
        ResponseEntity<Resource> response = reportController.downloadReport(fileName);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    void testGetAllReports() {
        // Arrange
        List<ReportEntity> mockReports = Arrays.asList(
                new ReportEntity(1L, 12345L, "api/v1/report/download/Record 12345 R0001.pdf", "Record", "Test report 1", null),
                new ReportEntity(2L, 12346L, "api/v1/report/download/Record 12346 R0002.pdf", "Record", "Test report 2", null)
        );

        when(reportService.getAllReports()).thenReturn(mockReports);

        // Act
        List<ReportEntity> reports = reportController.getAllReports();

        // Assert
        assertNotNull(reports);
        assertEquals(2, reports.size());
        assertEquals(12345, reports.get(0).getPatientId());
        assertEquals("Test report 1", reports.get(0).getNote());
    }

    @Test
    void testGetReportById() {
        // Arrange
        long reportId = 1L;
        ReportEntity mockReport = new ReportEntity(reportId, 12345L, "api/v1/report/download/Record 12345 R0001.pdf", "Record", "Test report 1", null);

        when(reportService.getReport(reportId)).thenReturn(Optional.of(mockReport));

        // Act
        Optional<ReportEntity> report = reportController.getAllReports(reportId);

        // Assert
        assertTrue(report.isPresent());
        assertEquals(reportId, report.get().getReportId());
        assertEquals(12345, report.get().getPatientId());
        assertEquals("Test report 1", report.get().getNote());
    }



}
