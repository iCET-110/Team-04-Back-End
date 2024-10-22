package edu.icet.crm.service;

import edu.icet.crm.dto.Record;

public interface RecordService {

    void deleteRecord(String id);

    void updateRecord(String id,Record record) throws Exception;


}
