package com.waes.controller;

import com.waes.domain.dto.BinaryDataSummaryDto;
import com.waes.service.BinaryDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryDataSummaryController.class)
public class BinaryDataSummaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BinaryDataService binaryDataService;

    @Test
    public void getSummary() throws Exception {
        BinaryDataSummaryDto binaryDataSummaryDto = BinaryDataSummaryDto.builder()
                .difference(9)
                .result("RIGHT")
                .leftRecordSize(5)
                .rightRecordSize(14)
                .build();

        when(binaryDataService.getSummary("1")).thenReturn(binaryDataSummaryDto);

        ResultActions resultActions = mockMvc.perform(
                get("/1").contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.difference", equalTo(9)))
                .andExpect(jsonPath("$.result", equalTo("RIGHT")))
                .andExpect(jsonPath("$.leftRecordSize", equalTo(5)))
                .andExpect(jsonPath("$.rightRecordSize", equalTo(14)));

        verify(binaryDataService).getSummary("1");
    }
}