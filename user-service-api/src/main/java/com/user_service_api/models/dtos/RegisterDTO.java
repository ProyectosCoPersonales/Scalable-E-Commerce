package com.user_service_api.models.dtos;

import com.user_service_api.models.enums.UserRole;

import lombok.Data;


@Data
public class RegisterDTO {
    String name;
    String lastName;
    String email;
    String password;
    UserRole role;
    String address;
    String phone;
}
