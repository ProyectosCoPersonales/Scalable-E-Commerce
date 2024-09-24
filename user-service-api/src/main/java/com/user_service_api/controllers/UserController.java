package com.user_service_api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user_service_api.models.entities.User;
import com.user_service_api.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            log.error("ERROR", e);
            ;
            return null;
        }
    }
    

    @GetMapping("/client/user/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            log.error("ERROR", e);
            return null;
        }
    }

    @GetMapping("/admin/verification")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean IsAdmin() {
        return true;
    }

    @GetMapping("/client/verification")
    @PreAuthorize("hasAuthority('CLIENT')")
    public boolean IsClient() {
        return true;
    }

    @GetMapping("/user/verification")
    @PreAuthorize("hasAnyAuthority('CLIENT','ADMIN')")
    public boolean IsUser(){
        return true;
    }
}
