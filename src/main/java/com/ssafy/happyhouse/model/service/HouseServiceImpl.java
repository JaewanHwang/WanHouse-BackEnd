package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapper;
import com.ssafy.happyhouse.model.service.interfaces.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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
	public List<HouseInfoDto> getAptInDong(Map<String, Integer> filters) throws Exception {
		return houseMapper.getAptInDong(filters);
	}

	@Override
	public List<HouseDealDto> getHouseDealsByAptCode(BigInteger aptCode) throws Exception{
		return houseMapper.getHouseDealsByAptCode(aptCode);
	}

	@Override
	public HouseInfoDto getHouseInfo(BigInteger aptCode) throws Exception{
		return houseMapper.getHouseInfo(aptCode);
	}
}
