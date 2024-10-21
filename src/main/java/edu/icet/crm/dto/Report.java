package edu.icet.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Report {
    private String reportId;
    private String reportLink;
    private LocalDateTime reportDate;
    private String categoryType;
    private List<Record> recordList;
}
