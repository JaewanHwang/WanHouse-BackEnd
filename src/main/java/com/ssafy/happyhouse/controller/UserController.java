package com.ssafy.happyhouse.controller;


import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {

    MemberService memberService;
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    private String login(@RequestParam Map<String, String> map, Model model, HttpSession session, HttpServletResponse response) throws SQLException {
        log.debug("[login] id: {},  pw: {}, remember-me: {}", map.get("id"), map.get("pw"), map.get("idsave"));
        MemberDto member = memberService.login(map.get("id"), map.get("pw"));
        if (member != null) {
            session.setAttribute("member", member);
            Cookie cookie = new Cookie("user_id", map.get("id"));
            cookie.setPath("/");
            if ("remember-me".equals(map.get("idsave"))) {
                cookie.setMaxAge(60*60*24*365);
            } else {
                cookie.setMaxAge(0);
            }
            response.addCookie(cookie);
            return "redirect:/";
        } else {
            model.addAttribute("errorMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login_form";
        }
    }

    @PostMapping("/register")
    private String register(@ModelAttribute MemberDto member, Model model) throws SQLException {
        log.debug("[register] id: {}, pw: {}, name: {}, addr: {}, phone: {}",
                member.getId(), member.getPw(), member.getName(), member.getAddr(), member.getPhone());
        if(memberService.register(member)) {
            return "redirect:login_form";
        } else {
            model.addAttribute("errorMsg", "이미 등록된 id 입니다.");
            return "register_form";
        }
    }

    @GetMapping("/logout")
    private String logout(HttpSession session) {
        MemberDto member = (MemberDto)session.getAttribute("member");
        log.debug("[logout] id: {}, pw: {}", member.getId(), member.getPw());
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/member_modify")
    private String memberModify(@ModelAttribute MemberDto member, Model model, HttpSession session) throws SQLException {
        memberService.update(member);
        session.setAttribute("member", member);
        return "redirect:/";
    }

    @GetMapping("/member_delete")
    private String memberDelete(@ModelAttribute MemberDto member, HttpSession session) throws SQLException {
        memberService.delete(member.getId());
        session.invalidate();
        return "redirect:/";
    }

}
