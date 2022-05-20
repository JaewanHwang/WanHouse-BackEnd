package com.ssafy.happyhouse.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class HouseDetailResponseDto {
    private HouseInfoDto houseInfo;
    private HouseDetailInfoDto houseDetailInfo;
    private List<HouseDealDto> houseDealList;
}
