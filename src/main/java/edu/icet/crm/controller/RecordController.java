package edu.icet.crm.controller;

import edu.icet.crm.dto.Record;
import edu.icet.crm.service.impl.RecordServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class RecordController {

    final RecordServiceImpl recordService;

    @PostMapping("/add-record")
    public ResponseEntity<String> addRecord(@Valid @RequestBody Record record) {
        recordService.addRecord(record);
        return ResponseEntity.ok("Record added successfully");
    }
}
