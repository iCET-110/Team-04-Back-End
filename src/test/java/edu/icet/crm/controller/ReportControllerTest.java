package edu.icet.crm.controller;

import edu.icet.crm.model.Report;
import edu.icet.crm.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReportControllerTest {

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @Test
    void testCreateReport() throws Exception {
        Report report = new Report();

        // Mock the service call
        when(reportService.createReport(any(Report.class))).thenReturn(report);

        // Test the controller
        mockMvc.perform(post("/api/v1/report/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"patientId\": \"P023\", \"reportTypeId\":\"BR001\" }"))  // Add sample JSON input
                .andExpect(status().isOk());  // Check for the correct status
    }
}
