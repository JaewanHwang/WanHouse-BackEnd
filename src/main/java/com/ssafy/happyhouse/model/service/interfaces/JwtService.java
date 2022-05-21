package com.ssafy.happyhouse.model.service.interfaces;

public interface JwtService {

	<T> String createJWT(String key, T data, String subject);
	String parseJWT(String key);
	byte[] generateKey();

	boolean isValidToken(String jwt);
	
}
