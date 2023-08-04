package com.example.learnspringsecuritylesson1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class PersonDto {

    @NotEmpty(message = "Должно быть заполнено")
    private String firstName;

    @NotEmpty(message = "Должно быть заполнено")
    private String lastName;

    @Email(message = "Введите корректный email")
    private String username;

    @NotEmpty(message = "Должно быть заполнено")
    private String password;

    private List<String> roleNames;
}
