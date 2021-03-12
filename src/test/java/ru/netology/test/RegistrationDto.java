package ru.netology.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor



public class RegistrationDto {
    public RegistrationDto(String user, String password, String status) {
        return ;
    }
}