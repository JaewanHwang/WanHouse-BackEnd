package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;
import com.ssafy.happyhouse.model.service.interfaces.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
	private BoardMapper boardMapper;

	@Autowired
	public void setBoardMapper(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public boolean insertBoard(BoardDto boardDto) throws SQLException {
		return boardMapper.insertBoard(boardDto);
	}

	@Override
	public boolean deleteBoard(int boardNo) throws SQLException {
		return boardMapper.deleteBoard(boardNo);
	}

	@Override
	public boolean updateBoard(BoardDto boardDto) throws SQLException {
		return boardMapper.updateBoard(boardDto);
	}

	@Override
	public BoardDto selectBoard(int boardNo) throws SQLException {
		return boardMapper.selectBoard(boardNo);
	}

	@Override
	public List<BoardDto> selectBoardList() throws SQLException {
		return boardMapper.selectBoardList();
	}

}
