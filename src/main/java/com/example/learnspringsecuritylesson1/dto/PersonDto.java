package com.example.learnspringsecuritylesson1.dto;

import com.example.learnspringsecuritylesson1.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PersonDto {

    @NotEmpty(message = "Должно быть заполнено")
    private String firstName;

    @NotEmpty(message = "Должно быть заполнено")
    private String lastName;

    @Email(message = "Введите корректный email")
    private String username;

    @Min(4)
    private String password;

    private Set<Role> roles;
}
