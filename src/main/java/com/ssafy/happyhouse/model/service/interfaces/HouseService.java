package com.ssafy.happyhouse.model.service.interfaces;

import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


public interface HouseService {

    List<SidoGugunCodeDto> getSido() throws Exception;

    List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;

    List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;

    List<HouseInfoDto> getAptInDong(Map<String, Integer> filters) throws Exception;

    List<HouseDealDto> getHouseDealsByAptCode(BigInteger aptCode) throws Exception;

    HouseInfoDto getHouseInfo(BigInteger aptCode) throws Exception;
}
