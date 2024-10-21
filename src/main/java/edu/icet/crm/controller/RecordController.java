package edu.icet.crm.controller;

import edu.icet.crm.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordService service;

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable String id){
        service.deleteRecord(id);
    }
}