package com.epam.nosqlmodule.repository;

import java.util.List;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;

import com.epam.nosqlmodule.entity.User;

@Repository
public interface UserRepository extends CouchbaseRepository<User, String> {

    List<User> findAllByEmail(String email);

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND ANY sport IN sports SATISFIES sport.sportName = $1 END")
    List<User> findAllUsersBySportName(String sportName);

}
