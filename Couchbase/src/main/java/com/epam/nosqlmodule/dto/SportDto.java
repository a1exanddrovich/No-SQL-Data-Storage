package com.epam.nosqlmodule.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class SportDto {

    @Setter
    private String id;
    private final String sportName;
    private final String sportProficiency;

}
