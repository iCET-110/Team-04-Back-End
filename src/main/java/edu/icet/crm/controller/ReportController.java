package edu.icet.crm.controller;

import edu.icet.crm.entity.ReportEntity;
import edu.icet.crm.service.impl.ReportServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/report")
public class ReportController {

    final ReportServiceImpl reportService;
    @Getter
    final String REPORT_UPLOAD_DIR = "src/main/resources/reports/";


    @PostMapping("/add")
    public ResponseEntity<String> addReport(
            @RequestParam String note,
            @RequestParam MultipartFile report) {

        if (report.isEmpty()) {
            // Return a 400 Bad Request with an error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Report file cannot be null or empty");
        }

        String reportName = report.getOriginalFilename();
        String category;
        long patientId;

        // Extracting patient ID and report category from the PDF file name
        try {
            String[] parts = reportName.split(" ");
            category = parts[0]; // "Record"
            String patientIdString = parts[1].replace(".pdf", "");
            patientId = Long.parseLong(patientIdString);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Please follow the correct naming convention");
        }

        // Saving the report PDF
        File directory = new File(REPORT_UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Save the PDF file to the specified path
        Path originalFilePath = Paths.get(REPORT_UPLOAD_DIR, reportName);
        try {
            // Use Files.copy() to save the file
            Files.copy(report.getInputStream(), originalFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save the file.");
        }

        // Create the report entity
        ReportEntity fullReport = new ReportEntity(
                -1L,
                patientId,
                "api/v1/report/download/" + reportName,
                category,
                note,
                null
        );

        // Save the report and retrieve the saved report with its generated ID
        ReportEntity savedReport = reportService.saveReport(fullReport);

        // Construct new filename by appending the saved report's ID
        String newReportName = category + " " + patientId + String.format(" R%04d",savedReport.getReportId()) + ".pdf";
        Path newFilePath = Paths.get(REPORT_UPLOAD_DIR, newReportName);

        try {
            // Rename the file by copying to a new name and deleting the old file
            Files.move(originalFilePath, newFilePath, StandardCopyOption.REPLACE_EXISTING);


            savedReport.setReportLink("api/v1/report/download/" + newReportName);
            reportService.saveReport(savedReport);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to rename the file.");
        }

        return ResponseEntity.ok(report.getOriginalFilename() + " file saved to the database successfully.");
    }



    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadReport(@PathVariable String fileName) {
        File file = new File(REPORT_UPLOAD_DIR, fileName);
        if (!file.exists() || !file.canRead()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // Define the path to the file
        String filePath = REPORT_UPLOAD_DIR + fileName;

        try {
            // Create a Path object for the file
            Path path = Paths.get(filePath);

            // Ensure the file exists
            if (!Files.exists(path)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);  // Return 404 if the file is not found
            }

            // Load the file as a resource
            Resource resource = new UrlResource(path.toUri());

            // Check if resource is readable
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }

            // Determine the file's content type (e.g., PDF)
            String contentType = Files.probeContentType(path);
            if (contentType == null) {
                contentType = "application/octet-stream";  // Default if unable to determine type
            }

            // Return the file with the appropriate headers
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }


    @GetMapping("/all")
    public List<ReportEntity> getAllReports(){
        return reportService.getAllReports();
    }

    @GetMapping("/{reportId}")
    public Optional<ReportEntity> getAllReports(@PathVariable long reportId){
        return reportService.getReport(reportId);
    }

}
