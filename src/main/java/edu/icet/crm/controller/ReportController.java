package edu.icet.crm.controller;

import edu.icet.crm.service.impl.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class ReportController {

    final ReportServiceImpl reportService;
}

