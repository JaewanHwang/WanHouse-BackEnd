package com.ssafy.happyhouse.model.service.interfaces;

import com.ssafy.happyhouse.model.dto.BoardDto;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {

    boolean insertBoard(BoardDto boardDto) throws SQLException;

    boolean deleteBoard(int boardNo) throws SQLException;

    boolean updateBoard(BoardDto boardDto) throws SQLException;

    BoardDto selectBoard(int boardNo) throws SQLException;

    List<BoardDto> selectBoardList() throws SQLException;
}
