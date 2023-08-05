package com.example.learnspringsecuritylesson1.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;



@AllArgsConstructor
@NoArgsConstructor
@ToString (exclude = "persons")
@EqualsAndHashCode(exclude = "persons")
@Setter
@Getter
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Person> persons;

    @Override
    public String getAuthority() {
        return name;
    }
}

