package com.ssafy.happyhouse.model.dao;

import com.ssafy.happyhouse.model.dto.Board;
import com.ssafy.happyhouse.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BoardDAO {
	private static DBUtil dbUtil = DBUtil.getUtil();

	public boolean deleteBoard(int boardNo) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from board where no = ?";
		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, boardNo);
			int rowCount = stmt.executeUpdate();
			return rowCount > 0;
		} finally {
			dbUtil.close(stmt, conn);
		}
	}

	public boolean insertBoard(Board board) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into board(title, content, memberId) values(?, ?, ?)";
		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, board.getTitle());
			stmt.setString(2, board.getContent());
			stmt.setString(3, board.getMemberId());

			int rowCount = stmt.executeUpdate();
			return rowCount > 0;

		} finally {
			dbUtil.close(stmt, conn);
		}
	}

	public Board selectBoard(int boardNo) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from board where no = ?";
		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5));
			}

			return null;

		} finally {
			dbUtil.close(rs, stmt, conn);
		}
	}

	public List<Board> selectBoardList() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "select * from board";
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			List<Board> selectedBoards = new ArrayList<>();

			while (rs.next()) {
				selectedBoards.add(
						new Board(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5)));
			}
			return selectedBoards;

		} finally {
			dbUtil.close(rs, stmt, conn);
		}

	}

	public boolean updateBoard(Board board) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update board set title = ?, content = ? where no = ?";

		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, board.getTitle());
			stmt.setString(2, board.getContent());
			stmt.setInt(3, board.getNo());
			int rowCount = stmt.executeUpdate();
			return rowCount > 0;
		} finally {
			dbUtil.close(stmt, conn);
		}
	}

}
