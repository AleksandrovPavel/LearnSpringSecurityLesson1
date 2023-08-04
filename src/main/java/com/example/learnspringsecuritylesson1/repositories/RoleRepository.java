package com.example.learnspringsecuritylesson1.repositories;

import com.example.learnspringsecuritylesson1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>{
    Optional<Role> findByName(String name);

}
