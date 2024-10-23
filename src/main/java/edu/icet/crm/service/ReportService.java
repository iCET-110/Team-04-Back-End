package edu.icet.crm.service;

import edu.icet.crm.dto.Report;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<Report>search(String id);
    List<Report>search(String id, LocalDate date);
    List<Report>search(LocalDate date);

}
