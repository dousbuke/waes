package com.waes.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "binaryData")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BinaryData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BINARY_DATA_SEQ")
    @SequenceGenerator(name = "BINARY_DATA_SEQ", sequenceName = "BINARY_DATA_SEQ")
    private Long id;

    @Column(name = "recordId", length = 50)
    private String recordId;

    @Column(name = "side", length = 20)
    private String side;

    @Column(name = "record", columnDefinition = "text")
    private String record;

    @Column(name = "createdDate")
    private LocalDateTime createdDate;

}
