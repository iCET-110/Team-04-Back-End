package edu.icet.crm.controller;

import edu.icet.crm.dto.Report;
import edu.icet.crm.entity.ReportEntity;
import edu.icet.crm.service.impl.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("api/v1/report")
public class ReportController {

    final ReportServiceImpl reportService;

    @GetMapping("/all")
    public List<ReportEntity> getAllReport(){
        return reportService.getAllReport();
    }
    @GetMapping("/{reportId}")
    public Optional<ReportEntity> getReportById(@PathVariable String reportId){
        return reportService.getReportById(reportId);
    }
    @GetMapping("/{reportDate}")
    public List<Report> getReportsByDate(@PathVariable LocalDateTime reportDate){
        return reportService.getReportsByDate(reportDate);
    }
}

