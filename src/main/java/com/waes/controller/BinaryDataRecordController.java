package com.waes.controller;

import com.waes.domain.api.BinaryDataRequest;
import com.waes.domain.enums.Side;
import com.waes.service.BinaryDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class BinaryDataRecordController {

    private final BinaryDataService binaryDataService;

    @PostMapping("{id}/left")
    public ResponseEntity<Void> recordDataLeft(@PathVariable("id") String id, @Valid @RequestBody BinaryDataRequest binaryDataRequest) {
        binaryDataRequest.setSide(Side.LEFT.name());
        binaryDataService.record(id, binaryDataRequest);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("{id}/right")
    public ResponseEntity<Void> recordDataRight(@PathVariable("id") String id, @Valid @RequestBody BinaryDataRequest binaryDataRequest) {
        binaryDataRequest.setSide(Side.RIGHT.name());
        binaryDataService.record(id, binaryDataRequest);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
