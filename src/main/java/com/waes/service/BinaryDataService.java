package com.waes.service;

import com.waes.domain.api.BinaryDataRequest;
import com.waes.domain.dto.BinaryDataSummaryDto;
import com.waes.domain.entity.BinaryData;
import com.waes.domain.enums.Side;
import com.waes.error.RecordAlreadyExistException;
import com.waes.error.RecordMissingException;
import com.waes.repository.BinaryDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BinaryDataService {

    private final BinaryDataRepository binaryDataRepository;

    public BinaryData record(String id, BinaryDataRequest binaryDataRequest) {
        Optional<BinaryData> record = getAllByRecordIdAndSide(id, binaryDataRequest.getSide());

        if(record.isPresent())
            throw new RecordAlreadyExistException("Record already exist for side: " + binaryDataRequest.getSide() + " and id: " + id);

        BinaryData binaryData = BinaryData.builder()
                .recordId(String.valueOf(id))
                .side(binaryDataRequest.getSide())
                .record(binaryDataRequest.getBinaryData())
                .createdDate(LocalDateTime.now())
                .build();

        return save(binaryData);
    }

    public BinaryData save(BinaryData binaryData) {
        log.info("binary data to be saved id: {}", binaryData.getRecordId());

        return binaryDataRepository.save(binaryData);
    }

    public BinaryDataSummaryDto getSummary(String recordId) {
        List<BinaryData> records = getAllByRecordId(recordId);

        if(records.isEmpty())
            return BinaryDataSummaryDto.builder()
                    .leftRecordSize(0)
                    .rightRecordSize(0)
                    .result(null)
                    .difference(0)
                    .build();

        if(records.size() == 1)
            throw new RecordMissingException("Record is missing for id: " + recordId + " to calculate");

        Integer record1Size = records.get(0).getRecord().length();
        String record1Side = records.get(0).getSide();

        Integer record2Size = records.get(1).getRecord().length();
        String record2Side = records.get(1).getSide();

        Integer result = record1Size - record2Size;
        String finalResult = calculate(result, record1Side, record2Side);

        return BinaryDataSummaryDto.builder()
                .leftRecordSize(record1Side.equals(Side.LEFT.name()) ? record1Size : record2Size)
                .rightRecordSize(record1Side.equals(Side.LEFT.name()) ? record2Size : record1Size)
                .result(finalResult)
                .difference(Math.abs(result))
                .build();
    }

    private String calculate(Integer result, String record1Side, String record2Side) {
        if(result == 0)
            return "Equal";
        else if(result > 0)
            return record1Side;
        else
            return record2Side;

    }

    public Optional<BinaryData> getAllByRecordIdAndSide(String recordId, String side) {
        return binaryDataRepository.findByRecordIdAndSide(recordId, side);
    }

    public List<BinaryData> getAllByRecordId(String recordId) {
        return binaryDataRepository.findAllByRecordId(recordId);
    }

}
