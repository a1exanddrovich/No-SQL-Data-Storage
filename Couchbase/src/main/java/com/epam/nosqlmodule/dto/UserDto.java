package com.epam.nosqlmodule.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserDto {

    private final String id;
    private final String email;
    private final String fullName;
    private final String birthDate;
    private final String gender;
    private final List<SportDto> sports;

}
