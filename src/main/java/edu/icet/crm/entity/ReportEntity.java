package edu.icet.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportEntity {
    private String reportId;
    private String reportLink;
    private String categoryType;
    private List<RecordEntity> recordList;
}
