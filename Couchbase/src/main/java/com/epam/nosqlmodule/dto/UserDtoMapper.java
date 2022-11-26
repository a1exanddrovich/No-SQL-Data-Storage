package com.epam.nosqlmodule.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.epam.nosqlmodule.entity.Gender;
import com.epam.nosqlmodule.entity.User;

@Component
public class UserDtoMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final SportDtoMapper sportDtoMapper;

    public UserDtoMapper(SportDtoMapper sportDtoMapper) {
        this.sportDtoMapper = sportDtoMapper;
    }

    public UserDto map(User user) {
        return UserDto.builder()
                .id(user.getId())
                .birthDate(user.getBirthDate().format(FORMATTER))
                .email(user.getEmail())
                .fullName(user.getFullName())
                .gender(user.getGender().name())
                .sports(user.getSports().stream().map(sportDtoMapper::map).collect(Collectors.toList()))
                .build();
    }

    public User unmap(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .fullName(userDto.getFullName())
                .gender(Gender.valueOf(userDto.getGender()))
                .birthDate(LocalDate.parse(userDto.getBirthDate(), FORMATTER))
                .sports(userDto.getSports().stream().map(sportDtoMapper::unmap).collect(Collectors.toList()))
                .build();
    }

}
