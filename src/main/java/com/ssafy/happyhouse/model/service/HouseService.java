package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;

import java.sql.SQLException;
import java.util.List;


public interface HouseService {

    List<SidoGugunCodeDto> getSido() throws Exception;

    List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;

    List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;

    List<HouseInfoDto> getAptInDong(String dong) throws Exception;

    List<HouseDealDto> getHouseDealsByAptCode(int aptCode) throws Exception;

    HouseInfoDto getHouseInfo(int aptCode) throws Exception;
}
