package com.ssafy.happyhouse.model.dto;

import lombok.Data;

@Data
public class UserDto {
	private String userId;
	private String password;
	private String userName;
	private String userAddress;
	private String userPhoneNumber;
}
