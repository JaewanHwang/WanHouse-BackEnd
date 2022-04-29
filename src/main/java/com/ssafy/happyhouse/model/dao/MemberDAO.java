package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.happyhouse.model.dto.Member;
import com.ssafy.happyhouse.util.DBUtil;


public class MemberDAO {
	private static DBUtil dbUtil = DBUtil.getUtil();

	public Member selectMember(String memberId) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from member where id = ?";
		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}

			return null;

		} finally {
			dbUtil.close(rs, stmt, conn);
		}

	}

	public boolean insertMember(Member mem) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into member values(?, ?, ?, ?, ?)";
		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mem.getId());
			stmt.setString(2, mem.getPw());
			stmt.setString(3, mem.getName());
			stmt.setString(4, mem.getAddr());
			stmt.setString(5, mem.getPhone());
			int rowCount = stmt.executeUpdate();
			return rowCount > 0;
		} finally {
			dbUtil.close(stmt, conn);
		}

	}

	public boolean deleteMember(String memberId) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from member where id = ?";
		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			int rowCount = stmt.executeUpdate();
			return rowCount > 0;
		} finally {
			dbUtil.close(stmt, conn);
		}
	}
	
	public boolean updateMember(Member member) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "update member set pw = ?, name = ?, addr = ?, phone = ? where id = ?";
		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getPw());
			stmt.setString(2, member.getName());
			stmt.setString(3, member.getAddr());
			stmt.setString(4, member.getPhone());
			stmt.setString(5, member.getId());
			int rowCount = stmt.executeUpdate();
			return rowCount > 0;
		}finally {
			dbUtil.close(stmt, conn);
		}
	}
	
	

}