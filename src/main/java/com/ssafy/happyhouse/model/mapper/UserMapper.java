package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;


@Mapper
public interface UserMapper {

	UserDto selectUser(String userId) throws SQLException;
	boolean insertUser(UserDto userDto) throws SQLException;
	boolean deleteUser(String userId) throws SQLException;
	
	boolean updateUser(UserDto userDto) throws SQLException;

    String selectUserId(String userId);
}