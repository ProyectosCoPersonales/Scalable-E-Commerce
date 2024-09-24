package com.user_service_api.services;

import java.util.List;
import com.user_service_api.models.entities.User;

public interface UserService {

    User getUserById(Long id) throws Exception;
    List<User> getAllUsers() throws Exception;
}
