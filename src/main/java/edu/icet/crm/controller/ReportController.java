package edu.icet.crm.controller;

import edu.icet.crm.dto.Record;
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
    public List<Record> search(@PathVariable String id) {
        System.out.println(id);
        return reportService.search(id);
    }


    @GetMapping("/searchByIdAndDate")
    public List<Record> search( @RequestParam String id, @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        System.out.println(id+" "+date);
        return reportService.search(id, date);
    }


    @GetMapping("/searchByDate/{date}")
    public List<Record> search( @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        System.out.println("----------> "+date);
        return reportService.search(date);
    }

}

