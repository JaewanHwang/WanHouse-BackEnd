package com.ssafy.happyhouse.model.dao;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HouseDealDAO {
	private static DBUtil dbUtil = DBUtil.getUtil();

	public List<HouseDeal> selectHouseDealList(int aptCode) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from housedeal where aptCode = ?";
		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, aptCode);
			rs = stmt.executeQuery();

			List<HouseDeal> selectedDeals = new ArrayList<>();
			while (rs.next()) {
				HouseDeal hd = new HouseDeal(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));

				selectedDeals.add(hd);
			}
			
			return selectedDeals;

		} finally {
			dbUtil.close(rs, stmt, conn);
		}
	}
}
