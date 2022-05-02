package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.dto.Board;
import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@RequestMapping("/board")
@Controller
public class BoardController {

    BoardService boardService;
    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board_list")
    private String boardList(Model model) throws SQLException {
        List<Board> boardList = boardService.selectBoardList();
        model.addAttribute("boardList", boardList);
        return "board_list";
    }

    @GetMapping("/board_detail")
    private String boardDetail(int no, Model model) throws SQLException {
        model.addAttribute("board", boardService.selectBoard(no));
        return "board_detail";
    }

    @GetMapping("/board_form")
    private String boardForm(HttpSession session) {
        MemberDto member = (MemberDto)session.getAttribute("member");
        if (member == null) {
            return "redirect:/user/login_form";
        } else {
            return "board_form";
        }
    }

    @PostMapping("/board_insert")
    private String boardInsert(Board board, HttpSession session) throws SQLException {
        MemberDto member = (MemberDto)session.getAttribute("member");
        System.out.println(board);
        if (member == null) {
            return "redirect:/user/login_form";
        } else {
            boardService.insertBoard(board);
            return "redirect:/board/";
        }
    }
}

