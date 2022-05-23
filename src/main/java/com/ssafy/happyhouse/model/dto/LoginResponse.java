package com.ssafy.happyhouse.model.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String type;
    private String token;
}
