package edu.icet.crm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ReportEntity {
    @Id
    private String reportId;
    private String reportLink;
    private String categoryType;
    private List<RecordEntity> recordList;
}
