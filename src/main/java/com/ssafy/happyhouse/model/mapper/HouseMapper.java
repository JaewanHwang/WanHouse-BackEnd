package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@Mapper
public interface HouseMapper {

    List<SidoGugunCodeDto> getSido() throws SQLException;

    List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;

    List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;

    List<HouseInfoDto> getAptInDong(Map<String, Integer> filters) throws SQLException;

    List<HouseDealDto> getHouseDealsByAptCode(BigInteger aptCode);

    HouseInfoDto getHouseInfo(BigInteger aptCode);
}
