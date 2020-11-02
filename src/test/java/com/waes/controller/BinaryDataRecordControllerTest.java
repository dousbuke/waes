package com.waes.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.waes.domain.api.BinaryDataRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryDataRecordController.class)
public class BinaryDataRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void recordDataLeft() throws Exception {
        BinaryDataRequest binaryDataRequest = BinaryDataRequest.builder().binaryData("vfvdfvdvgbfbdf").build();

        ResultActions resultActions = mockMvc.perform(
                post("/1/left").content(asJsonString(binaryDataRequest))
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isCreated());
    }

    @Test
    public void recordDataRight() throws Exception {
        BinaryDataRequest binaryDataRequest = BinaryDataRequest.builder().binaryData("vfvdfvdvgbfbdfvfvfvf").build();

        ResultActions resultActions = mockMvc.perform(
                post("/1/right").content(asJsonString(binaryDataRequest))
                        .contentType(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isCreated());
    }

}