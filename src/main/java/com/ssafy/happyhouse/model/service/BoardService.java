package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

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
