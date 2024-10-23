package edu.icet.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Record {

    private String recordId;
    private String patientID;
    private LocalDate recordDate;
    private String description;
    private List<Report> reportList;
}
