package com.hari.NTAT_TASK.repository;

import com.hari.NTAT_TASK.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {
    Optional<Users> findByusername(String username);

    @Query(value="{username : {$ne : ?0}}", fields="{password : 0,upi_pin : 0}")
    List<Users> findByUsernameNot(String username);
}
