package com.trackerapp.expensetracker.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    private String email;

    private String password;
}
