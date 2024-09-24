package com.user_service_api.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user_service_api.models.entities.User;
import com.user_service_api.repositories.UserRepository;
import com.user_service_api.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) throws Exception{
        try {
            return userRepository.findById(id).get();
        } catch (Exception e) {
            log.error("----------USER NOT FOUND----------", e);
            return null;
        }
        
    }

    @Override
    public List<User> getAllUsers() throws Exception{
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            log.error("-----------ERROR NOT USERS-----------", e);
            return null;
        }
        
    }

    
}
