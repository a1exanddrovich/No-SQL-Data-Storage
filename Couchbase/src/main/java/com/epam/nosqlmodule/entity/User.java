package com.epam.nosqlmodule.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.index.QueryIndexed;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

@Builder
@Getter
@Document
public class User {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private final String id;
    @Field
    @QueryIndexed
    private final String email;
    @Field
    private final String fullName;
    @Field
    private final LocalDate birthDate;
    @Field
    private final Gender gender;
    @Field
    private List<Sport> sports;

    public void addSport(Sport sport) {
        if (Objects.isNull(sports)) {
            sports = new ArrayList<>();
            sports.add(sport);
        }
        this.sports.add(sport);
    }

}
