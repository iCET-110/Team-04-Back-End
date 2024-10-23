package edu.icet.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "report")
@Table(name = "report")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;
    @Column(nullable = false)
    private Long patientId;
    @Column(nullable = false)
    private String reportLink;
    @Column(nullable = false)
    private String categoryType;
    @Lob
    private String note;

    @ManyToMany(mappedBy = "reportList", cascade = CascadeType.ALL)
    private List<RecordEntity> recordList;
}
