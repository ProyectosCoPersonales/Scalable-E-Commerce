package com.user_service_api.models.dtos;

import lombok.Data;


@Data
public class LoginDTO {
    String email;
    String password;
}
