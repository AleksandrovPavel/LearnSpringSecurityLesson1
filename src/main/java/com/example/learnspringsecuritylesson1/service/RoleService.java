package com.example.learnspringsecuritylesson1.service;

import com.example.learnspringsecuritylesson1.model.Person;
import com.example.learnspringsecuritylesson1.model.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();
}
