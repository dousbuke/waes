package com.waes.repository;

import com.waes.domain.entity.BinaryData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BinaryDataRepository extends JpaRepository<BinaryData, Long> {

    List<BinaryData> findAllByRecordId(String recordId);

    Optional<BinaryData> findByRecordIdAndSide(String recordId, String side);
}
