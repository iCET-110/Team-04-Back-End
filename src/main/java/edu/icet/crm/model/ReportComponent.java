package edu.icet.crm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ReportComponent")
public class ReportComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long componentId;

    @Column(name = "component_key")
    private String componentKey;

    @Column(name = "component_value")
    private Double componentValue;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;
}
