package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.config.KeyProperties;
import com.ssafy.happyhouse.exception.UnauthorizedException;
import com.ssafy.happyhouse.model.service.interfaces.JwtService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

//	@Value("${secret-key}")
//	private String SECRET_KEY;
	private static final int EXPIRE_MINUTES = 60;
	private KeyProperties keyProperties;

	@Autowired
	public void setKeyProperties(KeyProperties keyProperties) {
		this.keyProperties = keyProperties;
	}

	@Override
	public <T> String createJWT(String key, T data, String subject) {
		String jwt = Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.setHeaderParam("regDate", System.currentTimeMillis())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES))
				.setSubject(subject)
				.claim(key, data)
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact();
		return jwt;
	}

	@Override
	public byte[] generateKey() {
		byte[] key = null;
		try {
			key = keyProperties.getSecretKey().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (log.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				log.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}

		return key;
	}

//	전달 받은 토큰이 제대로 생성된것인지 확인 하고 문제가 있다면 UnauthorizedException을 발생.
	@Override
	public boolean isValidToken(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			return true;
		} catch (Exception e) {
//			if (log.isInfoEnabled()) {
//				e.printStackTrace();
//			} else {
			log.error(e.getMessage());
//			}
//			throw new UnauthorizedException();
//			개발환경
			return false;
		}
	}

	@Override
	public String parseJWT(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = request.getHeader("access-token");
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(keyProperties.getSecretKey().getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {
//			if (log.isInfoEnabled()) {
//				e.printStackTrace();
//			} else {
			log.error(e.getMessage());
//			}
			throw new UnauthorizedException();
//			개발환경
//			Map<String,Object> testMap = new HashMap<>();
//			testMap.put("userid", userid);
//			return testMap;
		}
		return claims.getBody().get("userId", String.class);
	}


}
