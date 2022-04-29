package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dao.HouseInfoDAO;
import com.ssafy.happyhouse.model.dto.HouseInfo;

import java.sql.SQLException;
import java.util.List;

public class HouseInfoService {
	HouseInfoDAO dao = new HouseInfoDAO();
	
	public List<HouseInfo> getHouseInfoListByDongCode(String dongCode) throws SQLException {
		return dao.searchHouseInfos(dongCode);
	}
	
	public HouseInfo getHouseInfoByAptCode(int aptCode) throws SQLException {
		return dao.selectHouseInfo(aptCode);
	}
}
