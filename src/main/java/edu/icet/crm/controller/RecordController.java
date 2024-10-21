package edu.icet.crm.controller;

import edu.icet.crm.service.impl.RecordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class RecordController {

    final RecordServiceImpl recordService;

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable String id){
        recordService.deleteRecord(id);
    }
}
