package edu.icet.crm.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Record {

    private String recordId;
    private String patientID;
    private Date recordDate;
    private String description;
    private List<Report> reportList;
}
