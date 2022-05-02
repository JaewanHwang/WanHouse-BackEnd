package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDealDto;
import com.ssafy.happyhouse.model.dto.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.SidoGugunCodeDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface HouseMapper {

    List<SidoGugunCodeDto> getSido() throws SQLException;

    List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;

    List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;

    List<HouseInfoDto> getAptInDong(String dong) throws SQLException;

    List<HouseDealDto> getHouseDealsByAptCode(int aptCode);

    HouseInfoDto getHouseInfo(int aptCode);
}
