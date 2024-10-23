package edu.icet.crm.controller;

import edu.icet.crm.entity.ReportEntity;
import edu.icet.crm.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ReportController.class)
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    @Test
    void testGetAllReport() throws Exception {

        List<ReportEntity> mockReports = List.of(new ReportEntity(), new ReportEntity());
        when(reportService.getAllReport()).thenReturn(mockReports);

        mockMvc.perform(get("/api/v1/report/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetReportById() throws Exception {
        String reportId = "123";
        ReportEntity mockReport = new ReportEntity();
        when(reportService.getReportById(reportId)).thenReturn(Optional.of(mockReport));

        mockMvc.perform(get("/api/v1/report/{reportId}", reportId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(5));
    }

    @Test
    void testGetReportsByDate() throws Exception {
        LocalDate reportDate = LocalDate.of(2023, 10, 10);
        List<ReportEntity> mockReports = List.of(new ReportEntity(), new ReportEntity());
        when(reportService.getReportsByDate(reportDate)).thenReturn(mockReports);
    }
}