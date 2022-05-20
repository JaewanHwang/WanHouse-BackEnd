package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.model.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@RequestMapping("/board")
@RestController
public class BoardController {

    private BoardService boardService;
    private JwtService jwtService;

    @Autowired
    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    private ResponseEntity<List<BoardDto>> boardList() throws SQLException {
        List<BoardDto> boardList = boardService.selectBoardList();
        if (boardList.size() > 0) {
            return new ResponseEntity<>(boardList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{boardNo}")
    private ResponseEntity<BoardDto> boardDetail(@PathVariable int boardNo) throws SQLException {
        BoardDto boardDto = boardService.selectBoard(boardNo);
        if (boardDto != null) {
            return ResponseEntity.ok().body(boardDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity<?> boardInsert(@RequestBody BoardDto boarDto) throws SQLException {
        boardService.insertBoard(boarDto);
        return ResponseEntity.created(URI.create("/board/" + boarDto.getBoardNo())).build();
    }

    @DeleteMapping("/{boardNo}")
    private ResponseEntity<?> boardDelete(@PathVariable int boardNo, HttpServletRequest request) throws Exception {
        BoardDto registeredBoard = boardService.selectBoard(boardNo);
        if (registeredBoard != null && jwtService.parseJWT(request.getHeader("access-token")).equals(registeredBoard.getUserId())) {
            boardService.deleteBoard(boardNo);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{boardNo}")
    private ResponseEntity<?> boardModify(@PathVariable int boardNo, @RequestBody BoardDto boardDto, HttpServletRequest request) throws Exception {
        BoardDto registeredBoard = boardService.selectBoard(boardNo);
        if (registeredBoard != null && jwtService.parseJWT(request.getHeader("access-token")).equals(registeredBoard.getUserId())) {
            boardService.updateBoard(boardDto);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

