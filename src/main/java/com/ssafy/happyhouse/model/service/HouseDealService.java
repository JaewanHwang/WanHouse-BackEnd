package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dao.HouseDealDAO;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class HouseDealService {
	HouseDealDAO dao = new HouseDealDAO();
	
	public List<HouseDeal> getHouseDealList(int aptCode) throws SQLException {
		return dao.selectHouseDealList(aptCode);
	}
}
