package edu.icet.crm.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Record {
    @Id

    private String recordId;
    private String reportId;
    private String recordType;
    private Date recordDate;
    private String reportStatus;

}
