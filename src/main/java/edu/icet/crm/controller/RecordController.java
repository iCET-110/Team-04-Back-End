package edu.icet.crm.controller;

import edu.icet.crm.dto.Record;
import edu.icet.crm.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordService service;

    @PostMapping("/add-record")
    public void addRecord(@RequestBody Record record){
        service.addRecord(record);
    }
}
