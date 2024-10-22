package edu.icet.crm.controller;

import edu.icet.crm.dto.Record;
import edu.icet.crm.service.impl.RecordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class RecordController {

    final RecordServiceImpl recordService;

    @GetMapping("/search")
    public List<Record> searchRecords(@RequestParam(value = "date", required = false) String dateString,
                                      @RequestParam(value = "recordId", required = false) String recordId) {
        if(recordId != null && dateString != null){
            LocalDate date = LocalDate.parse(dateString);
            return recordService.findByRecordIdAndDate(recordId,date);
        } else if (recordId != null) {
            return recordService.findByRecordId(recordId);
        } else if (dateString != null) {
            LocalDate date = LocalDate.parse(dateString);
            return recordService.findByDate(date);
        }
        return Collections.emptyList();
    }

}
