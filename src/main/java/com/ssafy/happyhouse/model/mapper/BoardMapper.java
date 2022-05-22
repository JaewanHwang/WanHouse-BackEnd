package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface BoardMapper {

    boolean deleteBoard(int boardNo) throws SQLException;

    boolean insertBoard(BoardDto boardDto) throws SQLException;

    BoardDto selectBoard(int boardNo) throws SQLException;

    List<BoardDto> selectBoardList() throws SQLException;

    boolean updateBoard(BoardDto boardDto) throws SQLException;

    void increaseHit(int boardNo) throws SQLException;

    void insertComment(CommentDto commentDto);

    BoardDto selectBoardWithComments(int boardNo);

    CommentDto selectComment(int commentNo);

    void deleteComment(int commentNo);

    void likeComment(int commentNo);

    void updateComment(CommentDto commentDto);

}

