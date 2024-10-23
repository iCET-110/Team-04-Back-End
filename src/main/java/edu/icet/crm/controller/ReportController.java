package edu.icet.crm.controller;


import edu.icet.crm.dto.Report;
import edu.icet.crm.service.impl.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class ReportController {

    final ReportServiceImpl reportService;


    @GetMapping("/searchById/{id}")
    public List<Report> search(@PathVariable String id) {
        return reportService.search(id);
    }


    @GetMapping("/searchByIdAndDate")
    public List<Report> search(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return reportService.search(id, date);
    }



    @GetMapping("/searchByDate")
    public List<Report> search(@RequestParam(value = "date", required = false)
                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return reportService.search(date);
    }

}

