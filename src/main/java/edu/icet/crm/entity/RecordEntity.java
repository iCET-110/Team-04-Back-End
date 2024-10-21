package edu.icet.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecordEntity {
    private String recordId;
    private String patientID;
    private Date recordDate;
    private String description;
    private List<ReportEntity> reportList;
}
