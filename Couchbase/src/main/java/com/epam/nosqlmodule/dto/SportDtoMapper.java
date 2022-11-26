package com.epam.nosqlmodule.dto;

import org.springframework.stereotype.Component;

import com.epam.nosqlmodule.entity.Proficiency;
import com.epam.nosqlmodule.entity.Sport;

@Component
public class SportDtoMapper {

    public SportDto map(Sport sport) {
        return SportDto.builder()
                .id(sport.getId())
                .sportName(sport.getSportName())
                .sportProficiency(sport.getSportProficiency().name())
                .build();
    }

    public Sport unmap(SportDto sportDto) {
        return Sport.builder()
                .id(sportDto.getId())
                .sportProficiency(Proficiency.valueOf(sportDto.getSportProficiency()))
                .sportName(sportDto.getSportName())
                .build();
    }

}
