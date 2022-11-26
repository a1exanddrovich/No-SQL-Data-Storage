package com.epam.nosqlmodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.nosqlmodule.dto.SportDto;
import com.epam.nosqlmodule.dto.UserDto;
import com.epam.nosqlmodule.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private static final String ID_PATH_VARIABLE = "id";
    private static final String EMAIL_PATH_VARIABLE = "email";
    private static final String SPORT_NAME_PATH_VARIABLE = "sportName";

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable(ID_PATH_VARIABLE) String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<List<UserDto>> findByEmail(@PathVariable(EMAIL_PATH_VARIABLE) String email) {
        return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/user/sport/{sportName}")
    public ResponseEntity<List<UserDto>> findBySport(@PathVariable(SPORT_NAME_PATH_VARIABLE) String sportName) {
        return new ResponseEntity<>(service.findBySport(sportName), HttpStatus.OK);
    }

    @GetMapping("/search/user")
    public ResponseEntity<List<UserDto>> performFullTextSearch(@RequestParam String q) {
        return new ResponseEntity<>(service.performFullTextSearch(q), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(service.create(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}/sport")
    public ResponseEntity<UserDto> addSport(@PathVariable(ID_PATH_VARIABLE) String id,
                                            @RequestBody SportDto sportDto) {
        return new ResponseEntity<>(service.addSportById(id, sportDto), HttpStatus.OK);
    }




}
