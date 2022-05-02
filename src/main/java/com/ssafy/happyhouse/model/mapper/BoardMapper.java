package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BoardMapper {

    boolean deleteBoard(int boardNo) throws SQLException;

    boolean insertBoard(BoardDto board) throws SQLException;

    BoardDto selectBoard(int boardNo) throws SQLException;

    List<BoardDto> selectBoardList() throws SQLException;

    boolean updateBoard(BoardDto board) throws SQLException;
}
