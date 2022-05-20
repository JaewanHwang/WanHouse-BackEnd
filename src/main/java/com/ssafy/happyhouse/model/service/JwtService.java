package com.ssafy.happyhouse.model.service;

import java.util.Map;

public interface JwtService {

	<T> String create(String key, T data, String subject);
	Map<String, Object> parseJWT(String key);
	boolean isUsable(String jwt);
	
}
