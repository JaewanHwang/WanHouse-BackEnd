package com.ssafy.happyhouse.controller;


import com.ssafy.happyhouse.model.dto.UserDto;
import com.ssafy.happyhouse.model.service.JwtService;
import com.ssafy.happyhouse.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    private UserService userService;
    private JwtService jwtService;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    private ResponseEntity<Map<String, Object>> login(@RequestBody UserDto userDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            UserDto loginUser = userService.login(userDto);
            if (loginUser != null) {
                String token = jwtService.create("userId", loginUser.getUserId(), "access-token");// key, data, subject
                log.debug("로그인 토큰정보 : {}", token);
                resultMap.put("access-token", token);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", FAIL);
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            log.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto) throws SQLException {
        log.debug("userDto info : {}", userDto);
        if (userService.getUserInfo(userDto.getUserId()) == null) {
            userService.registerUser(userDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<Map<String, Object>> getUserInfo(
            @PathVariable("userId") String userid,
            HttpServletRequest request) {
        log.debug("userId : {} ", userid);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            log.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                UserDto userDto = userService.getUserInfo(userid);
                resultMap.put("userInfo", userDto);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                log.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            log.error("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
            status = HttpStatus.NOT_ACCEPTABLE;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
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
