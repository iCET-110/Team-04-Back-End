package edu.icet.crm.controller;


import edu.icet.crm.model.Report;
import edu.icet.crm.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/report/create")
@RequiredArgsConstructor
public class ReportController {

    final ReportService reportService;

    @PostMapping("create")
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        Report createdReport = reportService.createReport(report);
        return ResponseEntity.ok(createdReport);
    }

}
