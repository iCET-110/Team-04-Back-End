package edu.icet.crm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "report_type_id")
    private String reportTypeId;

    @Column(name = "doctor_id")
    private String doctorId;

    @Column(name = "lab_office_id")
    private String labOfficeId;

    @Column(name = "test_date")
    private LocalDate testDate;

    @Column(name = "report_date")
    private LocalDate reportDate;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<ReportComponent> reportComponents;
}

