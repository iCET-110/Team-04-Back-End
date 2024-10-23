package edu.icet.crm.entity;

import jakarta.persistence.*;
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
@Entity(name = "report")
@Table(name = "report")
public class ReportEntity {
    @Id
    private String reportId;
    private String reportLink;
    private String categoryType;
    private LocalDate reportDate;

    @ManyToMany(mappedBy = "reportList", cascade = CascadeType.ALL)
    private List<RecordEntity> recordList;
}
