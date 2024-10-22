package edu.icet.crm.controller;

import edu.icet.crm.dto.Record;
import edu.icet.crm.service.impl.RecordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class RecordController {

    final RecordServiceImpl recordService;

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable String id){
        recordService.deleteRecord(id);
    }

    @PutMapping("/{id}")
    public void updateRecord(String id,@RequestBody Record record){
        try {
            recordService.updateRecord(id,record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
