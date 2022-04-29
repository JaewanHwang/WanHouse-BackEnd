package com.ssafy.happyhouse.test;
import com.ssafy.happyhouse.model.dao.HouseInfoDAO;
import com.ssafy.happyhouse.model.dto.HouseInfo;

import java.sql.SQLException;
import java.util.List;


public class HouseInfoTest {
	static final String LONGLINE = "-------------------------------------------------------------------------------";

	public static void main(String[] args) throws SQLException {
		HouseInfoDAO dao = new HouseInfoDAO();

		System.out.println("해당 dongCode에 존재하는 모든 아파트 조회");
		List<HouseInfo> searchedHouses = dao.searchHouseInfos("1114016500");
		for (HouseInfo hi : searchedHouses) {
			System.out.println(hi.toString());
		}
		
		System.out.println(LONGLINE);

	}
}
