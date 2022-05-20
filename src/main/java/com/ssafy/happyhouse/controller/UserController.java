package com.ssafy.happyhouse.controller;


import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {

    private MemberService memberService;
    private JwtServiceImpl jwtService;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    public void setJwtService(JwtServiceImpl jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    private ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            MemberDto loginUser = memberService.login(memberDto.getId(), memberDto.getPw());
            if (loginUser != null) {
                String token = jwtService.create("userid", loginUser.getId(), "access-token");// key, data, subject
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
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody MemberDto memberDto) throws SQLException {
        log.debug("memberDto info : {}", memberDto);
        if (memberService.getMember(memberDto.getId()) == null) {
            memberService.register(memberDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<Map<String, Object>> getUserInfo(
            @PathVariable("id") String userid,
            HttpServletRequest request) {
        log.debug("userid : {} ", userid);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            log.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                MemberDto memberDto = memberService.getMember(userid);
                resultMap.put("userInfo", memberDto);
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

    @PutMapping("/{id}")
    private ResponseEntity<?> modifyUser(@RequestBody MemberDto memberDto, @PathVariable String id, HttpServletRequest request, HttpSession session) throws SQLException {
        if (jwtService.parseJWT(request.getHeader("access-token")).equals(id)) {
            memberService.update(memberDto);
            session.setAttribute("member", memberDto);
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteUser(@PathVariable String id, HttpServletRequest request, HttpSession session) throws SQLException {
        if (jwtService.parseJWT(request.getHeader("access-token")).equals(id)) {
            memberService.delete(id);
            session.invalidate();
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
