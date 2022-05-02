package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.mapper.HouseMapper;

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
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return houseMapper.getAptInDong(dong);
	}

	@Override
	public List<HouseDealDto> getHouseDealsByAptCode(int aptCode) throws Exception{
		return houseMapper.getHouseDealsByAptCode(aptCode);
	}

	@Override
	public HouseInfoDto getHouseInfo(int aptCode) throws Exception{
		return houseMapper.getHouseInfo(aptCode);
	}

}