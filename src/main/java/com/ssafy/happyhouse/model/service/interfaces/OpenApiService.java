package com.ssafy.happyhouse.model.service.interfaces;

import com.ssafy.happyhouse.model.dto.HouseDetailInfoDto;

import java.io.IOException;

public interface OpenApiService {
    HouseDetailInfoDto fetchHouseInfo(String kaptCode) throws IOException;
}
