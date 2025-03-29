package com.trackerapp.expensetracker.controller;

import com.trackerapp.expensetracker.dto.LoginRequestDTO;
import com.trackerapp.expensetracker.dto.LoginResponseDTO;
import com.trackerapp.expensetracker.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password){
        return authenticationService.login(email, password);
    }
}
