package edu.icet.crm.service.impl;

import edu.icet.crm.dto.Record;
import edu.icet.crm.entity.ReportEntity;
import edu.icet.crm.repository.ReportRepository;
import edu.icet.crm.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    final ReportRepository reportRepository;



    @Override
    public List<Record> search(String id) {

        List<ReportEntity> reportEntities = reportRepository.findAll();
        List<Record> records = reportEntities.stream()
                .map(reportEntity -> new ModelMapper().map(reportEntity, Record.class))
                .collect(Collectors.toList());



        //TODO Create logic for filtering

        // Filter by report ID (partial or full match)
        return records.stream()
                .filter(record -> record.getRecordId().toLowerCase().contains(id.toLowerCase()))
                .collect(Collectors.toList());


    }

    @Override
    public List<Record> search(String id, LocalDate date) {

        List<ReportEntity> reportEntities = reportRepository.findAll();
        List<Record> records = reportEntities.stream()
                .map(reportEntity -> new ModelMapper().map(reportEntity, Record.class))
                .collect(Collectors.toList());

        //TODO Create logic for filtering

        // Filter by both report ID and date
        return records.stream()
                .filter(record -> record.getRecordId().toLowerCase().contains(id.toLowerCase()) && record.getRecordDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Record> search(LocalDate date) {
        List<ReportEntity> reportEntities = reportRepository.findAll();
        List<Record> records = reportEntities.stream()
                .map(reportEntity -> new ModelMapper().map(reportEntity, Record.class))
                .collect(Collectors.toList());

        //TODO Create logic for filtering
        return records.stream()
                .filter(record -> record.getRecordDate().equals(date))
                .collect(Collectors.toList());
    }
}
