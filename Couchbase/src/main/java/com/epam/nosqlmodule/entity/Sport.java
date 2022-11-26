package com.epam.nosqlmodule.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Sport {

    private final String id;
    private final String sportName;
    private final Proficiency sportProficiency;

}
