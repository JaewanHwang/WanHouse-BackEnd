package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.UserDto;
import com.ssafy.happyhouse.model.mapper.UserMapper;
import com.ssafy.happyhouse.model.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService {
	private UserMapper userMapper;

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public UserDto login(UserDto userDto) throws SQLException {
		UserDto registeredUser = getUserInfo(userDto.getUserId());
		if (registeredUser != null && userDto.getPassword().equals(registeredUser.getPassword())) {
			return registeredUser;
		}
		return null;
	}

	@Override
	public UserDto getUserInfo(String userId) throws SQLException {
		return userMapper.selectUser(userId);
	}

	@Override
	public boolean registerUser(UserDto userDto) throws SQLException {
		if (getUserInfo(userDto.getUserId()) != null) {
			return false;
		}
		return userMapper.insertUser(userDto);
	}

	@Override
	public boolean updateUser(UserDto userDto) throws SQLException {
		return userMapper.updateUser(userDto);
	}

	@Override
	public boolean deleteUser(String userId) throws SQLException {
		return userMapper.deleteUser(userId);
	}

	@Override
	public boolean checkIfUserIdDuplicate(String userId) {
		if(userMapper.selectUserId(userId) != null) {
			return true;
		} else {
			return false;
		}
	}
}
