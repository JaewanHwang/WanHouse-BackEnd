package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dao.BoardDAO;
import com.ssafy.happyhouse.model.dto.Board;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	BoardDAO boarddao = new BoardDAO();

	public boolean insertBoard(Board board) throws SQLException {
		return boarddao.insertBoard(board);
	}

	public boolean deleteBoard(int boardNo) throws SQLException {
		return boarddao.deleteBoard(boardNo);
	}

	public boolean updateBoard(Board board) throws SQLException {
		return boarddao.updateBoard(board);
	}

	public Board selectBoard(int boardNo) throws SQLException {
		return boarddao.selectBoard(boardNo);
	}

	public List<Board> selectBoardList() throws SQLException {
		return boarddao.selectBoardList();
	}

}
