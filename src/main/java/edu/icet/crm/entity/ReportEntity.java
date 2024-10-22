package edu.icet.crm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

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

    @ManyToMany(mappedBy = "reportList",cascade = CascadeType.ALL)
    private List<RecordEntity> recordList;
}
