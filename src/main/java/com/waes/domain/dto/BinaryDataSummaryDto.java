package com.waes.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BinaryDataSummaryDto implements Serializable {

    private static final long serialVersionUID = 7332989460284995050L;

    private String result;

    private Integer difference;

    private Integer leftRecordSize;

    private Integer rightRecordSize;
}
