package com.user_service_api.services;

import com.user_service_api.models.dtos.AuthDTO;
import com.user_service_api.models.dtos.LoginDTO;
import com.user_service_api.models.dtos.RegisterDTO;


public interface AuthService {

    AuthDTO login(LoginDTO login) throws Exception;

    AuthDTO register(RegisterDTO register) throws Exception;


}
