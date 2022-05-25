package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.dto.*;
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

    List<HouseDealDto> getHouseDealsByAptCode(BigInteger aptCode);

    HouseInfoDto getHouseInfo(BigInteger aptCode);

    List<HouseInfoDto> getApts(Map<String, Integer> filters);

    List<DongDto> getDongs(String keyword);

    void likeThisApt(Map<String, Object> parameterMap);

    void unlikeThisApt(Map<String, Object> parameterMap);

    List<LikedHouseDto> selectLikedApts(String userId);

    List<AvgPricePerYear> getAvgPricesByAptCode(BigInteger aptCode);

    BigInteger getLikeThisApt(Map<String, Object> aptCode);
}
