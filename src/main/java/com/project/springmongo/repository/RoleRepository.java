package com.project.springmongo.repository;

import com.project.springmongo.model.ERole;
import com.project.springmongo.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
