package com.ssafy.happyhouse.controller;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.CommentDto;
import com.ssafy.happyhouse.model.service.interfaces.BoardService;
import com.ssafy.happyhouse.model.service.interfaces.JwtService;
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
    private ResponseEntity<List<BoardDto>> getBoardList() throws SQLException {
        List<BoardDto> boardList = boardService.selectBoardList();
        if (boardList.size() > 0) {
            return new ResponseEntity<>(boardList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{boardNo}")
    private ResponseEntity<BoardDto> getBoardDetail(@PathVariable int boardNo) throws SQLException {
        BoardDto boardDto = boardService.selectBoardWithComments(boardNo);
        if (boardDto != null) {
            boardService.increaseHit(boardNo);
            return ResponseEntity.ok().body(boardDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity<?> createBoard(@RequestBody BoardDto boarDto) throws SQLException {
        boardService.insertBoard(boarDto);
        return ResponseEntity.created(URI.create("/board/" + boarDto.getBoardNo())).build();
    }

    @DeleteMapping("/{boardNo}")
    private ResponseEntity<?> removeBoard(@PathVariable int boardNo, HttpServletRequest request) throws Exception {
        BoardDto registeredBoard = boardService.selectBoard(boardNo);
        if (registeredBoard != null && jwtService.parseJWT(request.getHeader("access-token")).equals(registeredBoard.getUserId())) {
            boardService.deleteBoard(boardNo);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{boardNo}")
    private ResponseEntity<?> modifyBoard(@PathVariable int boardNo, @RequestBody BoardDto boardDto, HttpServletRequest request) throws Exception {
        BoardDto registeredBoard = boardService.selectBoard(boardNo);
        if (registeredBoard != null && jwtService.parseJWT(request.getHeader("access-token")).equals(registeredBoard.getUserId())) {
            boardService.updateBoard(boardDto);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{boardNo}/comments")
    private ResponseEntity<?> createComment(@PathVariable int boardNo, @RequestBody CommentDto commentDto) throws SQLException {
        if (boardService.selectBoard(boardNo) != null) {
            boardService.insertComment(commentDto);
            return ResponseEntity.created(URI.create("/board/comments/" + commentDto.getBoardNo())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{boardNo}/comments/{commentNo}")
    private ResponseEntity<?> likeComment(@PathVariable int boardNo, @PathVariable int commentNo) throws SQLException {
        if (boardService.selectBoard(boardNo) != null && boardService.selectComment(commentNo) != null) {
            boardService.likeComment(commentNo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{boardNo}/comments/{commentNo}")
    private ResponseEntity<?> removeComment(@PathVariable int boardNo, @PathVariable int commentNo) throws SQLException {
        if (boardService.selectBoard(boardNo) != null && boardService.selectComment(commentNo) != null) {
            boardService.deleteComment(commentNo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

