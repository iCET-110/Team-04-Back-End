package edu.icet.crm.controller;

import edu.icet.crm.dto.Report;
import edu.icet.crm.entity.ReportEntity;
import edu.icet.crm.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("api/v1/report")
public class ReportController {

    final ReportService reportService;

    @GetMapping("/all")
    public List<ReportEntity> getAllReport(){
        return reportService.getAllReport();
    }
    @GetMapping("/{reportId}")
    public Optional<ReportEntity> getReportById(@PathVariable String reportId){
        return reportService.getReportById(reportId);
    }
    @GetMapping("/reportDate")
    public List<Report> getReportsById(@PathVariable LocalDate reportDate){
        return reportService.getReportsById(reportDate);
    }
}

