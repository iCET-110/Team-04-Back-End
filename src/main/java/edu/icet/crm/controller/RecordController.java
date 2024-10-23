package edu.icet.crm.controller;

import edu.icet.crm.service.impl.RecordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/record")
public class RecordController {

    final RecordServiceImpl recordService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable String id) {
        try {
            recordService.deleteRecord(id);
            return new ResponseEntity<>("Record deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
        }
    }
}
