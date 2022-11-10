package com.epam.nosqlmodule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.result.SearchRow;
import com.epam.nosqlmodule.dto.SportDto;
import com.epam.nosqlmodule.dto.SportDtoMapper;
import com.epam.nosqlmodule.dto.UserDto;
import com.epam.nosqlmodule.dto.UserDtoMapper;
import com.epam.nosqlmodule.entity.User;
import com.epam.nosqlmodule.exception.NoSuchEntityException;
import com.epam.nosqlmodule.repository.UserRepository;

@Service
public class UserService {

    @Value("${fts.index.name}")
    private static String fullTextSearchIndexName;

    private final UserDtoMapper userDtoMapper;
    private final SportDtoMapper sportDtoMapper;
    private final UserRepository repository;
    private final Cluster cluster;

    @Autowired
    public UserService(UserDtoMapper userDtoMapper, SportDtoMapper sportDtoMapper, UserRepository repository,
                       Cluster cluster) {
        this.userDtoMapper = userDtoMapper;
        this.sportDtoMapper = sportDtoMapper;
        this.repository = repository;
        this.cluster = cluster;
    }

    public UserDto findById(String id) {
        User user = repository.findById(id).orElseThrow(NoSuchEntityException::new);
        return userDtoMapper.map(user);
    }

    public UserDto create(UserDto userDto) {
        User user = userDtoMapper.unmap(userDto);
        return userDtoMapper.map(repository.save(user));
    }

    public List<UserDto> findByEmail(String email) {
        return repository
                .findAllByEmail(email)
                .stream()
                .map(userDtoMapper::map)
                .collect(Collectors.toList());
    }

    public UserDto addSportById(String id, SportDto sportDto) {
        User user = repository.findById(id).orElseThrow(NoSuchEntityException::new);
        sportDto.setId(user.getId());
        user.addSport(sportDtoMapper.unmap(sportDto));
        return userDtoMapper.map(repository.save(user));
    }

    public List<UserDto> findBySport(String sportName) {
        return repository
                .findAllUsersBySportName(sportName)
                .stream()
                .map(userDtoMapper::map)
                .collect(Collectors.toList());
    }

    public List<UserDto> performFullTextSearch(String q) {
        return cluster
                .searchQuery(fullTextSearchIndexName, SearchQuery.queryString(q))
                .rows()
                .stream()
                .map(SearchRow::id)
                .map(this::findById)
                .collect(Collectors.toList());
    }
}
