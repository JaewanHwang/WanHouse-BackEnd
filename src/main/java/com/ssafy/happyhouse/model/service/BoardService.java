package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.happyhouse.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	BoardMapper boardMapper;

	@Autowired
	public void setBoardMapper(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}


	public boolean insertBoard(BoardDto boardDto) throws SQLException {
		return boardMapper.insertBoard(boardDto);
	}

	public boolean deleteBoard(int boardNo) throws SQLException {
		return boardMapper.deleteBoard(boardNo);
	}

	public boolean updateBoard(BoardDto boardDto) throws SQLException {
		return boardMapper.updateBoard(boardDto);
	}

	public BoardDto selectBoard(int boardNo) throws SQLException {
		return boardMapper.selectBoard(boardNo);
	}

	public List<BoardDto> selectBoardList() throws SQLException {
		return boardMapper.selectBoardList();
	}

}
