package com.ssafy.happyhouse.test;

import com.ssafy.happyhouse.model.dao.HouseDealDAO;
import com.ssafy.happyhouse.model.dto.HouseDeal;

import java.sql.SQLException;
import java.util.List;


public class HouseDealTest {
	static final String LONGLINE = "-------------------------------------------------------------------------------";

	public static void main(String[] args) throws SQLException {
		HouseDealDAO dao = new HouseDealDAO();

		System.out.println("해당 aptCode인 모든 아파트 조회");
		List<HouseDeal> searchedHouses = dao.selectHouseDealList(104);
		for (HouseDeal hd : searchedHouses) {
			System.out.println(hd.toString());
		}
		
		System.out.println(LONGLINE);

	}
}
