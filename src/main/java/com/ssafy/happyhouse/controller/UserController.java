package com.ssafy.happyhouse.controller;


import com.ssafy.happyhouse.model.dto.LoginResponse;
import com.ssafy.happyhouse.model.dto.UserDto;
import com.ssafy.happyhouse.model.service.interfaces.JwtService;
import com.ssafy.happyhouse.model.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

    private UserService userService;
    private JwtService jwtService;

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/check-id")
    private ResponseEntity<?> checkIfUserIdDuplicate(@RequestBody String userId) {
        if (!userService.checkIfUserIdDuplicate(userId)) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody UserDto userDto) throws SQLException {
        UserDto registeredUser = userService.login(userDto);
        if (registeredUser != null) {
            String token = jwtService.createJWT("userId", registeredUser.getUserId(), "access-token");// key, data, subject
            log.debug("로그인 토큰정보 : {}", token);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setType("jwt");
            loginResponse.setToken(token);
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto) throws SQLException {
        log.debug("userDto info : {}", userDto);
        if (userService.getUserInfo(userDto.getUserId()) == null) {
            userService.registerUser(userDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserInfo(
            @PathVariable("userId") String userId) throws SQLException {
        if (jwtService.parseJWT("userId").equals(userId)) {
            UserDto userDto = userService.getUserInfo(userId);
            return ResponseEntity.ok(userDto);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/logout")
    private ResponseEntity<?> logout(HttpServletRequest request, HttpSession session) {
        session.invalidate();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    private ResponseEntity<?> modifyUser(@RequestBody UserDto userDto, @PathVariable String userId, HttpServletRequest request, HttpSession session) throws SQLException {
        if (jwtService.parseJWT(request.getHeader("access-token")).equals(userId)) {
            userService.updateUser(userDto);
            session.setAttribute("user", userDto);
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{userId}")
    private ResponseEntity<?> deleteUser(@PathVariable String userId, HttpServletRequest request, HttpSession session) throws SQLException {
        if (jwtService.parseJWT(request.getHeader("access-token")).equals(userId)) {
            userService.deleteUser(userId);
            session.invalidate();
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
