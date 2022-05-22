package com.ssafy.happyhouse.model.service.interfaces;

import com.ssafy.happyhouse.model.dto.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


public interface HouseService {

    List<SidoGugunCodeDto> getSido() throws Exception;

    List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;

    List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;

    List<HouseInfoDto> getApts(Map<String, Integer> filters) throws Exception;

    List<HouseDealDto> getHouseDealsByAptCode(BigInteger aptCode) throws Exception;

    HouseInfoDto getHouseInfo(BigInteger aptCode) throws Exception;

    List<DongDto> getDongs(String keyword);

    void likeThisApt(BigInteger aptCode, String userId);

    void unlikeThisApt(BigInteger aptCode, String userId);

    List<LikedHouseDto> getLikedApts(String userId);
}
