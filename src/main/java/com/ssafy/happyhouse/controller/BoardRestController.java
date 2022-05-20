package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@RequestMapping("/board")
@RestController
public class BoardRestController {

    private BoardService boardService;

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

    @GetMapping("/{no}")
    private ResponseEntity<BoardDto> boardDetail(@PathVariable int no) throws SQLException {
        BoardDto boardDto = boardService.selectBoard(no);
        if (boardDto != null) {
            return ResponseEntity.ok().body(boardDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity<?> boardInsert(@RequestBody BoardDto boarDto) throws SQLException {
        boardService.insertBoard(boarDto);
        return ResponseEntity.created(URI.create("/board/" + boarDto.getNo())).build();
    }

    @DeleteMapping("/{no}")
    private ResponseEntity<?> boardDelete(@PathVariable int no) throws Exception {
        if (boardService.selectBoard(no) != null) {
            boardService.deleteBoard(no);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{no}")
    private ResponseEntity<?> boardModify(@PathVariable int no, @RequestBody BoardDto boardDto) throws Exception {
        if (boardService.selectBoard(no) != null) {
            boardService.updateBoard(boardDto);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

