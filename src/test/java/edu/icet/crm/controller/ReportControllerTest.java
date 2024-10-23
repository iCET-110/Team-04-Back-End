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

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        // Act
        ResponseEntity<String> response = reportController.addReport(note, mockFile);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Record 12345.pdf File saved to the database successfully..", response.getBody());

        // Cleanup
        Files.deleteIfExists(Paths.get(testReportDir + reportName));
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
        assertEquals("please follow the correct naming Convention", response.getBody());
    }

    @Test
    void testGetReport_FileExists() throws IOException {
        // Arrange
        String reportName = "BloodReport 01.pdf";
        MockMultipartFile mockFile = new MockMultipartFile("report", reportName, "application/pdf", "PDF content".getBytes());
        reportController.addReport("Test note", mockFile);

        // Act
        ResponseEntity<Resource> response = reportController.getReport(reportName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("attachment; filename=\"BloodReport 01.pdf\"", response.getHeaders().getFirst("Content-Disposition"));

        // Cleanup
        Files.deleteIfExists(Paths.get(testReportDir + reportName));
    }

    @Test
    void testGetReport_FileDoesNotExist() {
        // Act
        ResponseEntity<Resource> response = reportController.getReport("nonexistent.pdf");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetReport_FileNotReadable() {
        // Simulate a file that is not readable
        String fileName = "unreadable_report.pdf";
        ResponseEntity<Resource> response = reportController.getReport(fileName);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }



}
