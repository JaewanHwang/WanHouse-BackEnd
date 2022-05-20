package com.ssafy.happyhouse.model.service;

public interface JwtService {

	<T> String create(String key, T data, String subject);
	String parseJWT(String key);
	byte[] generateKey();

	boolean isUsable(String jwt);
	
}
