package edu.icet.crm.service;

import edu.icet.crm.dto.Record;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<Record>search(String id);
    List<Record>search(String id, LocalDate date);
    List<Record>search(LocalDate date);

}
