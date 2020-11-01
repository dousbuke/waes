package com.waes.domain.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinaryDataRequest implements Serializable {

    private static final long serialVersionUID = 6489999819154855502L;

    @NotBlank(message = "Data is mandatory")
    private String binaryData;

    @JsonIgnore
    private String side;
}
