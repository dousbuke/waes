package com.waes.controller;

import com.waes.domain.dto.BinaryDataSummaryDto;
import com.waes.service.BinaryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/diff")
public class BinaryDataSummaryController {

    private final BinaryDataService binaryDataService;

    @GetMapping("{id}")
    public ResponseEntity<BinaryDataSummaryDto> getRecord(@PathVariable("id") String recordId) {
        return new ResponseEntity<>(binaryDataService.getSummary(recordId), HttpStatus.OK);
    }

}
