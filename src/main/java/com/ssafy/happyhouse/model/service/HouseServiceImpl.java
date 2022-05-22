package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.*;
import com.ssafy.happyhouse.model.mapper.HouseMapper;
import com.ssafy.happyhouse.model.service.interfaces.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseMapper houseMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return houseMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getApts(Map<String, Integer> filters) throws Exception {
		return houseMapper.getApts(filters);
	}

	@Override
	public List<HouseDealDto> getHouseDealsByAptCode(BigInteger aptCode) throws Exception{
		return houseMapper.getHouseDealsByAptCode(aptCode);
	}

	@Override
	public HouseInfoDto getHouseInfo(BigInteger aptCode) throws Exception{
		return houseMapper.getHouseInfo(aptCode);
	}

	@Override
	public List<DongDto> getDongs(String keyword) {
		return houseMapper.getDongs(keyword);
	}

	@Override
	public void likeThisApt(BigInteger aptCode, String userId) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("aptCode", aptCode);
		parameterMap.put("userId", userId);
		houseMapper.likeThisApt(parameterMap);
	}

	@Override
	public void unlikeThisApt(BigInteger aptCode, String userId) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("aptCode", aptCode);
		parameterMap.put("userId", userId);
		houseMapper.unlikeThisApt(parameterMap);
	}

	@Override
	public List<LikedHouseDto> getLikedApts(String userId) {
		return houseMapper.selectLikedApts(userId);
	}
}
