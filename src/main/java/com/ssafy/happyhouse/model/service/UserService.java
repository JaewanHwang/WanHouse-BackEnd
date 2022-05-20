package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.UserDto;

import java.sql.SQLException;

public interface UserService {


    UserDto login(UserDto userDto) throws SQLException;

    UserDto getUserInfo(String userId) throws SQLException;

    boolean registerUser(UserDto userDto) throws SQLException;

    boolean updateUser(UserDto userDto) throws SQLException;

    boolean deleteUser(String userId) throws SQLException;
}
