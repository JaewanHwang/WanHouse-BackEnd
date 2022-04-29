package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.util.DBUtil;


public class HouseInfoDAO {
	private static DBUtil dbUtil = DBUtil.getUtil();

	public HouseInfo selectHouseInfo(int aptCode) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "select * from houseinfo where aptCode = ?";
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, aptCode); // 1번째 물음표
			rs = stmt.executeQuery();

			if (rs.next()) {
				HouseInfo houseInfo = new HouseInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8), rs.getString(9));
				return houseInfo;
			}

			return null;

		} finally {
			dbUtil.close(rs, stmt, conn);
		}
	}

	public List<HouseInfo> searchHouseInfos(String dongCode) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "select * from houseinfo where dongcode = ?";
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, dongCode); // 1번째 물음표
			rs = stmt.executeQuery();

			List<HouseInfo> selectedHouses = new ArrayList<>();
			while (rs.next()) {
				selectedHouses.add(new HouseInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getDouble(8), rs.getString(9)));
			}

			return selectedHouses;

		} finally {
			dbUtil.close(rs, stmt, conn);
		}
	}

}
